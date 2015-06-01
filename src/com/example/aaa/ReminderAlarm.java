package com.example.aaa;

import jane.data.AppointmentModel;
import jane.data.AppointmentRecord;
import jane.data.SQLHelper;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.gsm.SmsManager;
import android.widget.Toast;

/**
 * Trigger Status bar Notification alert at particular date and time 
 * 
 * @author Kumaresan Palanisamy
 */
public class ReminderAlarm  extends BroadcastReceiver{
	private NotificationManager mNotificationManager;
	private String prefName = "blogInfo";
	private static final int NOTIFY_ME_ID=1337;
	private static int count = 0;
	Context mContext;
	SharedPreferences sharedPreferences;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub       
		mContext = context;
		sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
		if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()))
		{



			AppointmentModel appointmentModel =  new AppointmentModel();
			SQLHelper sqlHelper = new SQLHelper(context);
			appointmentModel.init(sqlHelper);
			ArrayList<AppointmentRecord> list_appts = appointmentModel.list_appts;
			boolean flag_Reminder;
			String strDate,strTime;
			int flag_time,curIdx;
			AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
			for(AppointmentRecord record:list_appts)
			{
				curIdx = Integer.valueOf(record.idex);
				flag_Reminder = (record.reminder_check.equals("1"))?true:false;
				flag_time = Integer.valueOf(record.reminder_time);
				strDate=record.date;
				strTime=record.time;
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

						Intent intent2 = new Intent(mContext, ReminderAlarm.class);

						intent2.putExtra("ALERT_TITLE", record.doctor);
						intent2.putExtra("ALERT_ID", record.idex);
						intent2.putExtra("MOBILE_NO", record.phone);
						intent2.putExtra("ALERT_DATE", record.date);
						intent2.putExtra("ALERT_TIME", record.time);
						intent2.putExtra("ALERT_DESCRIPTION", "Your appointment is on tomorrow");
						PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 3 * curIdx, intent2, PendingIntent.FLAG_CANCEL_CURRENT);

						alarmManager.set(AlarmManager.RTC_WAKEUP, dt.getTimeInMillis(), pendingIntent);
					}

					if(nDays > 6)
					{
						//								dt.add(Calendar.DAY_OF_YEAR, -6);
						dt.set(nYear, nMonth, nDay - 6, nHour, 0);

						Intent intent2 = new Intent(mContext, ReminderAlarm.class);
						intent2.putExtra("ALERT_ID", record.idex);
						intent2.putExtra("MOBILE_NO", record.phone);
						intent2.putExtra("ALERT_TITLE", record.doctor);
						intent2.putExtra("ALERT_DATE", record.date);
						intent2.putExtra("ALERT_TIME", record.time);
						intent2.putExtra("ALERT_DESCRIPTION", "Your appointment is in one week");
						PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 3 * curIdx + 1, intent2, PendingIntent.FLAG_CANCEL_CURRENT);

						alarmManager.set(AlarmManager.RTC_WAKEUP, dt.getTimeInMillis(), pendingIntent);
					}

					if(nDays > 29)
					{
						//								dt.add(Calendar.DAY_OF_YEAR, -23);
						dt.set(nYear, nMonth, nDay - 23, nHour, 0);

						Intent intent2 = new Intent(mContext, ReminderAlarm.class);
						intent2.putExtra("ALERT_ID", record.idex);
						intent2.putExtra("MOBILE_NO", record.phone);
						intent2.putExtra("ALERT_TITLE", record.doctor);
						intent2.putExtra("ALERT_DATE", record.date);
						intent2.putExtra("ALERT_TIME", record.time);
						intent2.putExtra("ALERT_DESCRIPTION", "Your appointment is in one month");
						PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 3 * curIdx + 2, intent2, PendingIntent.FLAG_CANCEL_CURRENT);

						alarmManager.set(AlarmManager.RTC_WAKEUP, dt.getTimeInMillis(), pendingIntent);
					}
				}	
			}




		}
		else
		{
			mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			CharSequence doctorName = intent.getStringExtra("ALERT_TITLE");
			CharSequence message = intent.getStringExtra("ALERT_DESCRIPTION");
			String mobileNumber = intent.getStringExtra("MOBILE_NO");
			String date = intent.getStringExtra("ALERT_DATE");
			String time = intent.getStringExtra("ALERT_TIME");
			sendSms(doctorName,mobileNumber,message,date,time);


			Notification notifyObj=new Notification(R.drawable.icon,  "Notification message!",     System.currentTimeMillis());

			//	      Intent i = new Intent(context, NotifyActivity.class);
			Intent i = new Intent(context, NotifyActivityAppoinment.class);

			i.putExtra("ALERT_TITLE", doctorName);
			i.putExtra("ALERT_DESCRIPTION", message);

			PendingIntent pi=PendingIntent.getActivity(context, 0, i, 0);

			notifyObj.setLatestEventInfo(context, doctorName, message, pi);

			notifyObj.number= count ++;
			notifyObj.defaults |= Notification.DEFAULT_VIBRATE;
			notifyObj.defaults |= Notification.DEFAULT_SOUND;
			notifyObj.flags|=Notification.FLAG_AUTO_CANCEL;    

			mNotificationManager.notify(NOTIFY_ME_ID, notifyObj);

			Toast.makeText(context, "New Notification Received", Toast.LENGTH_LONG).show(); 
		}



	}
	@SuppressWarnings("deprecation")
	private void sendSms(CharSequence doctorName,String docMobileNumber, CharSequence message , String date ,String time) {

		String userMobileNumber = sharedPreferences.getString("PHONE", null);
		
		String messageTosend = message + " with "+doctorName+" On "+date+" "+time+" Contact: "+docMobileNumber;
		SmsManager smsManager = SmsManager.getDefault();
		if(userMobileNumber != null)
		{
			/*mobileNumber=mobileNumber.replaceAll(" ", "");
			mobileNumber=mobileNumber.replaceAll("\\(", "");
			mobileNumber=mobileNumber.replaceAll("\\)", "");
			mobileNumber=mobileNumber.replaceAll("\\-", "");*/
			
		}
		smsManager.sendTextMessage(userMobileNumber, null, messageTosend, null, null);
		//System.out.println("mobileNumber-->>"+mobileNumber);
		// TODO Auto-generated method stub

	}
	private String Code_string(String str)
	{
		if(str.equals("")) return "NULL";
		return str;			
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
}
