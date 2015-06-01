package com.example.aaa;

import java.util.Calendar;

import jane.data.AppointmentRecord;
import jane.data.DocRecord;
import jane.data.UIUtils;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class AppointmentsActivity extends Activity {
	static public int nMode;
	static public int nIdx;
	static public boolean flagCalendar;
	private boolean flag_Reminder;
	private int flag_time;
	private int year, month, day;
	private int hour, minute;
	private Context mContext;
	private Calendar cal;

	int request_Code = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		mContext = this;

		ViewGroup vg;

		if(nMode < 2)
		{
			setContentView(R.layout.new_appointment);
			vg = (ViewGroup)findViewById(R.id.apt_new_root_layout);

			EditText edt_clientphone = (EditText) findViewById(R.id.txtPhone);
			InputFilter filter = new InputFilter() {

				@Override
				public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
					if (source.length() > 0) {

						if (!Character.isDigit(source.charAt(0))) {
							//return "";
						}
						else {
							if (dstart == 3) {
								return source + ") ";
							} else if (dstart == 0) {
								return "(" + source;
							} else if ((dstart == 5) || (dstart == 9))
								return "-" + source;
							else if (dstart >= 14)
								return "";
						}

					} else {

					}

					return null;

				}
			};

			edt_clientphone.setFilters(new InputFilter[] { filter });			
		}
		else
		{
			setContentView(R.layout.appointment);
			vg = (ViewGroup)findViewById(R.id.apt_root_layout);
		}

		UIUtils.setFontForAll(vg);
		init();
		setClickListener(); 
	}


	private void init()
	{		

		flag_Reminder = false;
		flag_time = -1;

		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH) + 1;
		year = cal.get(Calendar.YEAR);

		hour = cal.get(Calendar.HOUR_OF_DAY);
		minute = cal.get(Calendar.MINUTE);

		String strToday = month + "-" + day + "-" + year;

		((TextView)findViewById(R.id.txtDate_title)).setText(strToday);

		if(nMode == 0)
		{
			//			((EditText)findViewById(R.id.txtDate)).setText(strToday);
			flag_Reminder = true;
			((Button) findViewById(R.id.chk_Reminder_Yes)).setBackgroundResource(R.drawable.checkbox_checked);
			((Button) findViewById(R.id.chk_Reminder_No)).setBackgroundResource(R.drawable.checkbox);
			flag_time = 1;
			((Button) findViewById(R.id.chk_12pm)).setBackgroundResource(R.drawable.checkbox_checked);
			return;
		}

		AppointmentRecord record = MainActivity.aModel.list_appts.get(nIdx);

		flag_Reminder = (record.reminder_check.equals("1"))?true:false;

		if(flag_Reminder)
		{
			((Button) findViewById(R.id.chk_Reminder_Yes)).setBackgroundResource(R.drawable.checkbox_checked);
			((Button) findViewById(R.id.chk_Reminder_No)).setBackgroundResource(R.drawable.checkbox);
		}

		flag_time = Integer.valueOf(record.reminder_time);


		if(flag_time == 0) ((Button) findViewById(R.id.chk_8am)).setBackgroundResource(R.drawable.checkbox_checked);
		if(flag_time == 1) ((Button) findViewById(R.id.chk_12pm)).setBackgroundResource(R.drawable.checkbox_checked);
		if(flag_time == 2) ((Button) findViewById(R.id.chk_8pm)).setBackgroundResource(R.drawable.checkbox_checked);

		if(nMode == 1)
		{
			((EditText)findViewById(R.id.txtDate)).setText(Decode_string(record.date));
			((EditText)findViewById(R.id.txtTime)).setText(Decode_string(record.time));
			((EditText)findViewById(R.id.txtDoctor)).setText(Decode_string(record.doctor));
			((EditText)findViewById(R.id.txtAddress)).setText(Decode_string(record.address));
			((EditText)findViewById(R.id.txtCity)).setText(Decode_string(record.city));
			((EditText)findViewById(R.id.txtState)).setText(Decode_string(record.state));
			((EditText)findViewById(R.id.txtZip)).setText(Decode_string(record.zip));
			((EditText)findViewById(R.id.txtPhone)).setText(record.phone);
			((EditText)findViewById(R.id.txtMobile)).setText(Decode_string(record.mobile));
			((EditText)findViewById(R.id.txtEmail)).setText(Decode_string(record.email));
		}
		else
		{
			((TextView)this.findViewById(R.id.txtDate)).setText(Decode_string(record.date));
			((TextView)findViewById(R.id.txtTime)).setText(Decode_string(record.time));
			((TextView)findViewById(R.id.txtDoctor)).setText(Decode_string(record.doctor));
			System.out.println("ADDRESS"+record.address);
			((TextView)findViewById(R.id.txtAddress)).setText(Decode_string(record.address));
			((TextView)findViewById(R.id.txtCity)).setText(Decode_string(record.city));
			((TextView)findViewById(R.id.txtState)).setText(Decode_string(record.state));
			((TextView)findViewById(R.id.txtZip)).setText(Decode_string(record.zip));
			((TextView)findViewById(R.id.txtPhone)).setText(record.phone);
			((TextView)findViewById(R.id.txtMobile)).setText(Decode_string(record.mobile));
			((TextView)findViewById(R.id.txtEmail)).setText(Decode_string(record.email));

			if(strToday.equals(record.date))
			{
				((Button) findViewById(R.id.chk_today)).setBackgroundResource(R.drawable.checkbox_checked);
			}
		}



		if(nMode == 2)
		{
			((Button)findViewById(R.id.chk_Reminder_Yes)).setEnabled(false);
			((Button)findViewById(R.id.chk_Reminder_No)).setEnabled(false);

			((Button)findViewById(R.id.chk_8am)).setEnabled(false);
			((Button)findViewById(R.id.chk_12pm)).setEnabled(false);
			((Button)findViewById(R.id.chk_8pm)).setEnabled(false);

		}


	}

	private String TimeConverter(String oldTime)
	{
		String newTime = "";
		String[] ele = {"", "", ""};
		String[] ele2 = oldTime.split(":");

		String[] ele1 = ele2[1].split(" ");
		ele[0] = ele2[0];
		ele[1] = ele1[0];
		ele[2] = ele1[1];

		int hh = Integer.valueOf(ele[0]);
		int mm = Integer.valueOf(ele[1]);

		if(ele[2].equals("PM"))
		{
			if(hh < 12) hh += 12;
		}

		newTime = hh + "-" + mm;
		return newTime;
	}

	private void AlertView(String strMessage)
	{
		Builder dlg = new AlertDialog.Builder(AppointmentsActivity.this);
		dlg.setMessage(strMessage);

		dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		dlg.show();
	}

	private String Decode_string(String str)
	{
		if(str.equals("NULL")) return "";
		return str;
	}

	private String Code_string(String str)
	{
		if(str.equals("")) return "NULL";
		return str;			
	}

	private void setClickListener()
	{

		((Button)findViewById(R.id.btnHome)).setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AppointmentsActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		((Button) findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {

				switch (nMode) {
				case 0:
					if(flagCalendar)
					{
						Intent intent = new Intent(AppointmentsActivity.this, CalendarActivity.class);
						startActivity(intent);
						flagCalendar = false;
					}
					else
					{
						Intent intent = new Intent(AppointmentsActivity.this, ControlActivity.class);
						startActivity(intent);
					}

					break;
				default:
					if(flagCalendar)
					{
						Intent intent = new Intent(AppointmentsActivity.this, CalendarActivity.class);
						startActivity(intent);
						flagCalendar = false;
					}
					else
					{
						Intent i = new Intent(AppointmentsActivity.this, TableListActivity.class);
						startActivity(i);
					}

					break;
				}


				finish();
			}
		});

		((Button) findViewById(R.id.chk_Reminder_Yes)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				if(!flag_Reminder)
				{
					flag_Reminder = true;
					((Button) findViewById(R.id.chk_Reminder_Yes)).setBackgroundResource(R.drawable.checkbox_checked);
					((Button) findViewById(R.id.chk_Reminder_No)).setBackgroundResource(R.drawable.checkbox);
				}
			}
		});

		((Button) findViewById(R.id.chk_Reminder_No)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				if(flag_Reminder)
				{
					flag_Reminder = false;
					((Button) findViewById(R.id.chk_Reminder_No)).setBackgroundResource(R.drawable.checkbox_checked);
					((Button) findViewById(R.id.chk_Reminder_Yes)).setBackgroundResource(R.drawable.checkbox);
				}
			}
		});

		((Button) findViewById(R.id.chk_8am)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				if(flag_time != 0)
				{
					flag_time = 0;
					((Button) findViewById(R.id.chk_8am)).setBackgroundResource(R.drawable.checkbox_checked);
					((Button) findViewById(R.id.chk_12pm)).setBackgroundResource(R.drawable.checkbox);
					((Button) findViewById(R.id.chk_8pm)).setBackgroundResource(R.drawable.checkbox);
				}
			}
		});

		((Button) findViewById(R.id.chk_12pm)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				if(flag_time != 1)
				{
					flag_time = 1;
					((Button) findViewById(R.id.chk_8am)).setBackgroundResource(R.drawable.checkbox);
					((Button) findViewById(R.id.chk_12pm)).setBackgroundResource(R.drawable.checkbox_checked);
					((Button) findViewById(R.id.chk_8pm)).setBackgroundResource(R.drawable.checkbox);
				}
			}
		});

		((Button) findViewById(R.id.chk_8pm)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				if(flag_time != 2)
				{
					flag_time = 2;
					((Button) findViewById(R.id.chk_8am)).setBackgroundResource(R.drawable.checkbox);
					((Button) findViewById(R.id.chk_12pm)).setBackgroundResource(R.drawable.checkbox);
					((Button) findViewById(R.id.chk_8pm)).setBackgroundResource(R.drawable.checkbox_checked);
				}
			}
		});


		if(nMode < 2)
		{
			((Button) findViewById(R.id.btnAdd)).setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					startActivityForResult(new Intent(AppointmentsActivity.this, DotorsListActivity.class), request_Code);
				}
			});

			((Button) findViewById(R.id.btnSave)).setOnClickListener(new OnClickListener(){
				public void onClick(View v) {
					String strDate = ((EditText)findViewById(R.id.txtDate)).getText().toString();
					String strTime = ((EditText)findViewById(R.id.txtTime)).getText().toString();
					String strDoctor = ((EditText)findViewById(R.id.txtDoctor)).getText().toString();
					String strAddress = ((EditText)findViewById(R.id.txtAddress)).getText().toString();
					String strCity = ((EditText)findViewById(R.id.txtCity)).getText().toString();
					String strState = ((EditText)findViewById(R.id.txtState)).getText().toString();
					String strZip = ((EditText)findViewById(R.id.txtZip)).getText().toString();
					String strPhone = ((EditText)findViewById(R.id.txtPhone)).getText().toString();
					String strMobile = ((EditText)findViewById(R.id.txtMobile)).getText().toString();
					String strEmail = ((EditText)findViewById(R.id.txtEmail)).getText().toString();

					if(strDate == null || strDate.equals(""))
					{
						AlertView("Input the Date");
						return;
					}

					if(strTime == null || strTime.equals(""))
					{
						AlertView("Inpute the Time");
						return;
					}

					if(strDoctor == null || strDoctor.equals(""))
					{
						AlertView("Input the Doctor name");
						return;
					}

					//					if(strAddress == null || strAddress.equals(""))
					//					{
					//						AlertView("Input the address");
					//						return;
					//					}
					//					
					//					if(strCity == null || strCity.equals(""))
					//					{
					//						AlertView("Input the city");
					//						return;
					//					}
					//					
					//					if(strState == null || strState.equals(""))
					//					{
					//						AlertView("Input the state");
					//						return;
					//					}
					//					
					//					if(strZip == null || strZip.equals(""))
					//					{
					//						AlertView("Input the zip code");
					//						return;
					//					}
					//					
					//					if(strPhone == null || strPhone.equals(""))
					//					{
					//						AlertView("Input the phone");
					//						return;
					//					}
					//					
					//					if(strMobile == null || strMobile.equals(""))
					//					{
					//						AlertView("Input the mobile number");
					//						return;
					//					}
					//					
					//					if(strEmail == null || strEmail.equals(""))
					//					{
					//						AlertView("Input the email");
					//						return;
					//					}

					if(flag_time == -1)
					{
						AlertView("choose the reminder time");
						return;
					}

					AppointmentRecord record = new AppointmentRecord();

					record.date = Code_string(strDate);
					record.time = Code_string(strTime);
					record.doctor = Code_string(strDoctor);
					record.address = Code_string(strAddress);
					record.city = Code_string(strCity);
					record.state = Code_string(strState);
					record.zip = Code_string(strZip);
					record.phone = Code_string(strPhone);
					record.mobile = Code_string(strMobile);
					record.email = Code_string(strEmail);

					record.reminder_check = (flag_Reminder)? "1":"0";

					record.reminder_time =String.valueOf(flag_time);

					if(nMode == 0)
					{
						MainActivity.aModel.maxIdx ++;

						int curIdx =  MainActivity.aModel.maxIdx;

						record.idex = String.valueOf(curIdx);
						MainActivity.aModel.list_appts.add(record);
						MainActivity.aModel.updateDB();

						if(flag_Reminder)
						{
							String strTime_total = strDate + "-" + TimeConverter(strTime);

							String[] ele = strTime_total.split("-");

							int nMonth = Integer.valueOf(ele[0]) - 1;
							int nDay = Integer.valueOf(ele[1]);
							int nYear = Integer.valueOf(ele[2]);
							int nHour = Integer.valueOf(ele[3]);
							int nMinute = 0; //Integer.valueOf(ele[4]);

							if(flag_time == 0) nHour = 8;
							if(flag_time == 1) nHour = 12;
							if(flag_time == 2) nHour = 20;

							AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);

							Calendar dt = Calendar.getInstance();

							dt.set(nYear, nMonth, nDay, nHour, nMinute);

							Calendar dn = Calendar.getInstance();

							int dnDay = dn.get(Calendar.DAY_OF_MONTH);
							int dnMonth = dn.get(Calendar.MONTH);
							int dnYear = dn.get(Calendar.YEAR);

							dnDay = dnDay + dnMonth * 30 + (dnYear - 2010) * 500;
							int dtDay = nDay + nMonth * 30 + (nYear - 2010) * 500;

							int nDays = dtDay - dnDay;

							//dn.add(Calendar.SECOND, 30);

							if(nDays > 0)
							{
								//								dt.add(Calendar.DAY_OF_YEAR, -1);
								dt.set(nYear, nMonth, nDay - 1, nHour, 0);

								Intent intent = new Intent(mContext, ReminderAlarm.class);

								intent.putExtra("ALERT_TITLE", record.doctor);
								intent.putExtra("ALERT_ID", record.idex);
								intent.putExtra("MOBILE_NO", record.phone);
								intent.putExtra("ALERT_DATE", record.date);
								intent.putExtra("ALERT_TIME", record.time);
								intent.putExtra("ALERT_DESCRIPTION", "Your appointment is on tomorrow");
								PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 3 * curIdx, intent, PendingIntent.FLAG_CANCEL_CURRENT);

								alarmManager.set(AlarmManager.RTC_WAKEUP, dt.getTimeInMillis(), pendingIntent);
							}

							if(nDays > 6)
							{

								//								dt.add(Calendar.DAY_OF_YEAR, -6);
								dt.set(nYear, nMonth,  - 7, nHour, 0);

								Intent intent = new Intent(mContext, ReminderAlarm.class);
								intent.putExtra("ALERT_TITLE", record.doctor);
								intent.putExtra("ALERT_ID", record.idex);
								intent.putExtra("MOBILE_NO", record.phone);
								intent.putExtra("ALERT_DATE", record.date);
								intent.putExtra("ALERT_TIME", record.time);
								intent.putExtra("ALERT_DESCRIPTION", "Your appointment is in one week");
								PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 3 * curIdx + 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);

								alarmManager.set(AlarmManager.RTC_WAKEUP, dt.getTimeInMillis(), pendingIntent);
							}

							if(nDays > 29)
							{
								//								dt.add(Calendar.DAY_OF_YEAR, -23);
								dt.set(nYear, nMonth, nDay - 23, nHour, 0);

								Intent intent = new Intent(mContext, ReminderAlarm.class);

								intent.putExtra("ALERT_TITLE", record.doctor);
								intent.putExtra("ALERT_ID", record.idex);
								intent.putExtra("MOBILE_NO", record.phone);
								intent.putExtra("ALERT_DATE", record.date);
								intent.putExtra("ALERT_TIME", record.time);
								intent.putExtra("ALERT_DESCRIPTION", "Your appointment is in one month");
								PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 3 * curIdx + 2, intent, PendingIntent.FLAG_CANCEL_CURRENT);

								alarmManager.set(AlarmManager.RTC_WAKEUP, dt.getTimeInMillis(), pendingIntent);
							}
						}			
					}
					else
					{
						record.idex = MainActivity.aModel.list_appts.get(nIdx).idex;
						MainActivity.aModel.updateArray(record);
						MainActivity.aModel.updateDB();

						int curIdx = Integer.valueOf(record.idex);
						AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);

						Intent intent3 = new Intent(mContext, ReminderAlarm.class);

						intent3.putExtra("ALERT_TITLE", record.doctor);
						
						intent3.putExtra("ALERT_DESCRIPTION", "Your appointment is in one month");
						PendingIntent pendingIntent3 = PendingIntent.getBroadcast(mContext, 3 * curIdx + 2, intent3, PendingIntent.FLAG_CANCEL_CURRENT);
						alarmManager.cancel(pendingIntent3);

						Intent intent1 = new Intent(mContext, ReminderAlarm.class);

						intent1.putExtra("ALERT_TITLE", record.doctor);
						intent1.putExtra("ALERT_DESCRIPTION", "Your appointment is in one week");
						PendingIntent pendingIntent1 = PendingIntent.getBroadcast(mContext, 3 * curIdx + 1, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
						alarmManager.cancel(pendingIntent1);

						Intent intent2 = new Intent(mContext, ReminderAlarm.class);

						intent2.putExtra("ALERT_TITLE", record.doctor);
						intent2.putExtra("ALERT_DESCRIPTION", "Your appointment is on tomorrow");
						PendingIntent pendingIntent2 = PendingIntent.getBroadcast(mContext, 3 * curIdx, intent2, PendingIntent.FLAG_CANCEL_CURRENT);
						alarmManager.cancel(pendingIntent2);

						if(flag_Reminder)
						{
							String strTime_total = strDate + "-" + TimeConverter(strTime);

							String[] ele = strTime_total.split("-");

							int nMonth = Integer.valueOf(ele[0]) - 1;
							int nDay = Integer.valueOf(ele[1]);
							int nYear = Integer.valueOf(ele[2]);
							int nHour = Integer.valueOf(ele[3]);
							//int nMinute = Integer.valueOf(ele[4]);

							Calendar dt = Calendar.getInstance();

							//int nnMinute = nMinute + 1;
							//flag_time = flag_time;
							int nMin = 0;
							if(flag_time == 0) 
							{
								nHour = 8;
								nMin = 0;
							}
							if(flag_time == 1) 
							{
								nHour = 12;
								nMin = 0;
							}
							if(flag_time == 2) nHour = 20;

							int milSec = (int) dt.getTimeInMillis();
							//dt.set(nYear, nMonth, nDay, 18, nnMinute);
							Calendar dn = Calendar.getInstance();

							int milSec1 = (int) dn.getTimeInMillis();
							//							
							int dnDay = dn.get(Calendar.DAY_OF_MONTH);
							int dnMonth = dn.get(Calendar.MONTH);
							int dnYear = dn.get(Calendar.YEAR);

							dnDay = dnDay + dnMonth * 30 + (dnYear - 2010) * 500;
							int dtDay = nDay + nMonth * 30 + (nYear - 2010) * 500;
							//							
							//							int nDays = dt.get(Calendar.DAY_OF_YEAR) - dn.get(Calendar.DAY_OF_YEAR);
							int nDays = dtDay - dnDay;

							if(nDays > 0)
							{
								//								dt.add(Calendar.DAY_OF_YEAR, -1);
								dt.set(nYear, nMonth, nDay - 1, nHour, nMin);
								//int ss = (int) dt.getTimeInMillis();

								Intent intent = new Intent(mContext, ReminderAlarm.class);

								intent.putExtra("ALERT_TITLE", record.doctor);
								intent.putExtra("ALERT_ID", record.idex);
								intent.putExtra("MOBILE_NO", record.phone);
								intent.putExtra("ALERT_DATE", record.date);
								intent.putExtra("ALERT_TIME", record.time);
								intent.putExtra("ALERT_DESCRIPTION", "Your appointment is on tomorrow");
								PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 3 * curIdx, intent, PendingIntent.FLAG_CANCEL_CURRENT);

								alarmManager.set(AlarmManager.RTC_WAKEUP, dt.getTimeInMillis(), pendingIntent);
							}

							if(nDays > 6)
							{
								//								dt.add(Calendar.DAY_OF_YEAR, -6);
								dt.set(nYear, nMonth, nDay - 6, nHour, 0);

								Intent intent = new Intent(mContext, ReminderAlarm.class);

								intent.putExtra("ALERT_TITLE", record.doctor);
								intent.putExtra("ALERT_ID", record.idex);
								intent.putExtra("MOBILE_NO", record.phone);
								intent.putExtra("ALERT_DATE", record.date);
								intent.putExtra("ALERT_TIME", record.time);
								intent.putExtra("ALERT_DESCRIPTION", "Your appointment is in one week");
								PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 3 * curIdx + 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);

								alarmManager.set(AlarmManager.RTC_WAKEUP, dt.getTimeInMillis(), pendingIntent);
							}

							if(nDays > 29)
							{
								//								dt.add(Calendar.DAY_OF_YEAR, -23);
								dt.set(nYear, nMonth, nDay - 23, nHour, 0);

								Intent intent = new Intent(mContext, ReminderAlarm.class);

								intent.putExtra("ALERT_TITLE", record.doctor);
								intent.putExtra("ALERT_ID", record.idex);
								intent.putExtra("MOBILE_NO", record.phone);
								intent.putExtra("ALERT_DATE", record.date);
								intent.putExtra("ALERT_TIME", record.time);
								intent.putExtra("ALERT_DESCRIPTION", "Your appointment is in one month");
								PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 3 * curIdx + 2, intent, PendingIntent.FLAG_CANCEL_CURRENT);

								alarmManager.set(AlarmManager.RTC_WAKEUP, dt.getTimeInMillis(), pendingIntent);
							}
						}			
					}

					Intent intent = new Intent(AppointmentsActivity.this, SuccessWarningActivity.class);
					startActivity(intent);

					finish();
				}
			});

			((EditText)this.findViewById(R.id.txtDate)).setOnFocusChangeListener(new View.OnFocusChangeListener() {

				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					if(hasFocus)
					{
						DateDialog();
						((EditText)findViewById(R.id.txtDoctor)).requestFocus();
					}
				}
			});

			((EditText)this.findViewById(R.id.txtTime)).setOnFocusChangeListener(new View.OnFocusChangeListener() {

				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					if(hasFocus)
					{
						TimeDialog();
						((EditText)findViewById(R.id.txtDoctor)).requestFocus();
					}
				}
			});
		}
		else
		{
			((Button)findViewById(R.id.btnPhone)).setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					String strPhone = ((TextView)findViewById(R.id.txtPhone)).getText().toString();

					if(strPhone.equals("")) return;

					Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:" + strPhone));
					startActivity(i);
				}
			});

			((Button)findViewById(R.id.btnMobile)).setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					String strMobile = ((TextView)findViewById(R.id.txtMobile)).getText().toString();

					if(strMobile.equals("")) return;

					Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:" + strMobile));
					startActivity(i);
				}
			});
		}
	}

	//	public void SetAlarm(Context context)
	//    {
	//        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
	//        Intent i = new Intent(context, Alarm.class);
	//        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
	//        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 10, pi); // Millisec * Second * Minute
	//    }
	//	

	private void DateDialog()
	{
		OnDateSetListener listener=new OnDateSetListener() {

			public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth)
			{
				((EditText)findViewById(R.id.txtDate)).setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + year);

			}};

			DatePickerDialog dpDialog=new DatePickerDialog(this, listener, year, month - 1, day);
			dpDialog.show();
	}

	private void TimeDialog()
	{
		OnTimeSetListener listener = new OnTimeSetListener() {

			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				String strAPM = (hourOfDay > 11)?"PM":"AM";
				if(hourOfDay > 12) hourOfDay -= 12;
				
				
				((EditText)findViewById(R.id.txtTime)).setText(String.format("%02d",hourOfDay) + ":" +String.format("%02d",minute) + " " + strAPM);
			}
		};

		TimePickerDialog tpDialog = new TimePickerDialog(this, listener, hour, minute, false);
		tpDialog.show();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		switch (nMode) {
		case 0:
			//			if(flagCalendar)
			//			{
			//				Intent intent = new Intent(AppointmentsActivity.this, CalendarActivity.class);
			//        		startActivity(intent);
			//        		flagCalendar = false;
			//			}
			//			else
			//			{
			Intent intent = new Intent(AppointmentsActivity.this, ControlActivity.class);
			startActivity(intent);
			//			}

			break;
		default:
			//			if(flagCalendar)
			//			{
			//				Intent intent = new Intent(AppointmentsActivity.this, CalendarActivity.class);
			//        		startActivity(intent);
			//        		flagCalendar = false;
			//			}
			//			else
			//			{
			Intent i = new Intent(AppointmentsActivity.this, TableListActivity.class);
			startActivity(i);
			//			}

			break;
		}

		finish();
		super.onBackPressed();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == request_Code) {
			if (resultCode == RESULT_OK) {
				int nidx = Integer.valueOf(data.getData().toString());
				if(nidx == -1) return;

				DocRecord record = MainActivity.docModel.list_appts.get(nidx);

				((EditText)findViewById(R.id.txtDoctor)).setText(Decode_string(record.doctor));
				((EditText)findViewById(R.id.txtAddress)).setText(Decode_string(record.address));
				((EditText)findViewById(R.id.txtCity)).setText(Decode_string(record.city));
				((EditText)findViewById(R.id.txtState)).setText(Decode_string(record.state));
				((EditText)findViewById(R.id.txtZip)).setText(Decode_string(record.zip));
				((EditText)findViewById(R.id.txtPhone)).setText(record.phone);
				((EditText)findViewById(R.id.txtMobile)).setText(Decode_string(record.mobile));
				((EditText)findViewById(R.id.txtEmail)).setText(Decode_string(record.email));

			}
		}
	}
}
