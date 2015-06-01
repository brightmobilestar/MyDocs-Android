package com.example.aaa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import jane.data.AppointmentRecord;
import jane.data.InsuranceRecord;
import jane.data.PharmacyRecord;
import jane.data.SpecialistRecord;
import jane.data.UIUtils;
import com.example.aaa.CalendarActivity.GridCellAdapter;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TableListActivity extends Activity {
	static public int nMode;
	static public int nCategory;
	LinearLayout apptListLayout;
	LinearLayout layout_calendar;
	private Context mContext;
	
	private TextView currentMonth;
	private Button selectedDayMonthYearButton;
	private ImageView prevMonth;
	private ImageView nextMonth;
	private GridView calendarView;
	private GridCellAdapter adapter;
	private Calendar _calendar;
//	@SuppressLint("NewApi")
	private int month, year;
//	@SuppressWarnings("unused")
//	@SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi" })

	private static final String dateTemplate = "MMMM yyyy";
	
	
	
	String categoryName[] = {"Insurance", "Primary[Dr]", "Specialist[Dr]", "Dentist", "Optometrist", "Pharmacy", "Hospital", "Appointments"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.table_list);
		
		ViewGroup vg = (ViewGroup)findViewById(R.id.tbl_ViewGroup);
		
		UIUtils.setFontForAll(vg);
		
		Calendar cal = Calendar.getInstance();
		int day_loc = cal.get(Calendar.DAY_OF_MONTH);
		int month_loc = cal.get(Calendar.MONTH) + 1;
		int year_loc = cal.get(Calendar.YEAR);
		
		 String strToday = month_loc + "-" + day_loc + "-" + year_loc;
		 
		 ((TextView)findViewById(R.id.txtDate_title)).setText(strToday);
		 ((TextView)findViewById(R.id.txtTitle)).setText(categoryName[nCategory]);
		 
		mContext = this;
		
		apptListLayout = (LinearLayout) findViewById(R.id.layout_appointments);
		
//		((Button)findViewById(R.id.btn_Calendar)).setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				CalendarActivity.nMode = nMode;
//				Intent intent = new Intent(TableListActivity.this, CalendarActivity.class);
//        		startActivity(intent);
//			}
//		});
		
		((Button)findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(TableListActivity.this, ControlActivity.class);
				startActivity(i);
				finish();
			}
		});
		
//		if(nCategory < 7) ((Button)findViewById(R.id.btn_Calendar)).setVisibility(View.INVISIBLE);
		
//		layout_calendar = ((LinearLayout)findViewById(R.id.layout_calendar)).setVisibility(View.INVISIBLE);
	}
	
	private String Decode_string(String str)
	{
		if(str.equals("NULL")) return "";
		return str;
	}
	
//	private String Code_string(String str)
//	{
//		if(str.equals("")) return "NULL";
//		return str;			
//	}
//	
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		if(nCategory == 7) load_appointment_list("");
		if(nCategory == 0) load_Insurance_list();
		if(nCategory == 2) load_Specialist_list();
		if(nCategory == 5) load_Pharmacy_list();
		
	}
	
	private void load_Pharmacy_list()
	{
		apptListLayout.removeAllViews();

		if(MainActivity.phaModel.list_appts.size() == 0)
		{
			Builder dlg = new AlertDialog.Builder(TableListActivity.this);
			dlg.setMessage("There is no data");

			dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(TableListActivity.this, ControlActivity.class);
	        		startActivity(intent);
					finish();
				}
			});

			dlg.show();
			return;
		}

		for(int i = 0; i < MainActivity.phaModel.list_appts.size(); i ++)
		{
			appendData_Pharmacy(i);
		}
	}
	
	private void load_appointment_list(String strDate)
	{
		apptListLayout.removeAllViews();
		
		if(MainActivity.aModel.list_appts.size() == 0)
		{
			Builder dlg = new AlertDialog.Builder(TableListActivity.this);
			dlg.setMessage("There is no data");
			
			dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(TableListActivity.this, ControlActivity.class);
	        		startActivity(intent);
					finish();
				}
			});
			
			dlg.show();
//			finish();
			return;
		}
		
		for(int i = 0; i < MainActivity.aModel.list_appts.size(); i ++)
		{
			AppointmentRecord record = MainActivity.aModel.list_appts.get(i);
			if (record.date.equals(strDate) || strDate.equals("")) {
				appendData_Appointment(i);
			}
		}
		
		layout_calendar = (LinearLayout)(View.inflate(this, R.layout.my_calendar_view, null));
		apptListLayout.addView(layout_calendar);
		
		calendar_init();
		
	}
	
	private void calendar_init()
	{
		_calendar = Calendar.getInstance(Locale.getDefault());
		month = _calendar.get(Calendar.MONTH) + 1;
		year = _calendar.get(Calendar.YEAR);

//		prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
//		prevMonth.setOnClickListener(this);

		currentMonth = (TextView) layout_calendar.findViewById(R.id.currentMonth);
		currentMonth.setText(DateFormat.format(dateTemplate,
				_calendar.getTime()));

//		nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
//		nextMonth.setOnClickListener(this);

		calendarView = (GridView) layout_calendar.findViewById(R.id.calendar);

		// Initialised
		adapter = new GridCellAdapter(getApplicationContext(),
				R.id.calendar_day_gridcell, month, year);
		
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
		
		((ImageView)layout_calendar.findViewById(R.id.prevMonth)).setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (month <= 1) {
				month = 12;
				year--;
			} else {
				month--;
			}
			
			setGridCellAdapterToDate(month, year);
		}
	});
	
	((ImageView)layout_calendar.findViewById(R.id.nextMonth)).setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (month > 11) {
				month = 1;
				year++;
			} else {
				month++;
			}
			
			setGridCellAdapterToDate(month, year);
		}
	});
		
		setGridCellAdapterToDate(month, year);
	}
	
	private void setGridCellAdapterToDate(int month, int year) {
		adapter = new GridCellAdapter(getApplicationContext(),
				R.id.calendar_day_gridcell, month, year);
		_calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
		currentMonth.setText(DateFormat.format(dateTemplate,
				_calendar.getTime()));
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
	}
	
	
	
	private void load_Insurance_list()
	{
		apptListLayout.removeAllViews();
		
		if(MainActivity.insModel.list_appts.size() == 0)
		{
			Builder dlg = new AlertDialog.Builder(TableListActivity.this);
			dlg.setMessage("There is no data");
			
			dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(TableListActivity.this, ControlActivity.class);
	        		startActivity(intent);
					finish();
				}
			});
			
			dlg.show();
//			finish();
			return;
		}
		
		for(int i = 0; i < MainActivity.insModel.list_appts.size(); i ++)
		{
			appendData_Insurance(i);
		}
		
	}
	
	private void load_Specialist_list()
	{
		apptListLayout.removeAllViews();

		if(MainActivity.specModel.list_appts.size() == 0)
		{
			Builder dlg = new AlertDialog.Builder(TableListActivity.this);
			dlg.setMessage("There is no data");

			dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(TableListActivity.this, ControlActivity.class);
	        		startActivity(intent);
					finish();
				}
			});

			dlg.show();
			//			finish();
			return;
		}

		for(int i = 0; i < MainActivity.specModel.list_appts.size(); i ++)
		{
			appendData_Specialist(i);
		}
	}
	
	private void appendData_Specialist(int idx)
	{
		final LinearLayout newCell = (LinearLayout)(View.inflate(this, R.layout.table_cell, null));

		SpecialistRecord record = MainActivity.specModel.list_appts.get(idx);
		
		UIUtils.setFontForAll((ViewGroup)newCell);

		((TextView)(newCell.findViewById(R.id.txtDoctor))).setText(Decode_string(record.Doctor));
		((TextView)(newCell.findViewById(R.id.txtDate))).setText(Decode_string(record.Specialist));
		((TextView)(newCell.findViewById(R.id.txtTime))).setText(Decode_string(record.Email));

		newCell.setTag(idx);

		final int nidx = idx;

		apptListLayout.addView(newCell);

		newCell.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				SpecialistActivity.nMode = nMode;
				SpecialistActivity.nIdx = (Integer)arg0.getTag();
				Intent intent = new Intent(TableListActivity.this, SpecialistActivity.class);
				startActivity(intent);
				finish();
			}
		});

		((Button)newCell.findViewById(R.id.btnDel)).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Builder dlg = new AlertDialog.Builder(TableListActivity.this);
				dlg.setMessage("Would you like to delete this specialist?");

				dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
						int other_nidx = Integer.valueOf(MainActivity.specModel.list_appts.get(nidx).total_index); 
						
						MainActivity.docModel.list_appts.remove(other_nidx);
						MainActivity.docModel.updateDB();
						
						MainActivity.specModel.list_appts.remove(nidx);
						MainActivity.specModel.updateDB();
						load_Specialist_list();
					}
				});

				dlg.setNegativeButton("Cancel",null);

				dlg.show();
			}
		});
	}
	
	private void appendData_Pharmacy(int idx)
	{
		final LinearLayout newCell = (LinearLayout)(View.inflate(this, R.layout.table_cell, null));
		
		UIUtils.setFontForAll((ViewGroup)newCell);

		PharmacyRecord record = MainActivity.phaModel.list_appts.get(idx);

		((TextView)(newCell.findViewById(R.id.txtDoctor))).setText(Decode_string(record.Pharmacy));
		((TextView)(newCell.findViewById(R.id.txtDate))).setText(Decode_string(record.city));
		((TextView)(newCell.findViewById(R.id.txtTime))).setText(Decode_string(record.phone));

		newCell.setTag(idx);

		final int nidx = idx;

		apptListLayout.addView(newCell);

		newCell.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				PharmacyActivity.nMode = nMode;
				PharmacyActivity.nIdx = (Integer)arg0.getTag();
				Intent intent = new Intent(TableListActivity.this, PharmacyActivity.class);
				startActivity(intent);
				finish();
			}
		});

		((Button)newCell.findViewById(R.id.btnDel)).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Builder dlg = new AlertDialog.Builder(TableListActivity.this);
				dlg.setMessage("Would you like to delete this pharmacy?");

				dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						MainActivity.phaModel.list_appts.remove(nidx);
						MainActivity.phaModel.updateDB();
						load_Pharmacy_list();
					}
				});

				dlg.setNegativeButton("Cancel",null);

				dlg.show();
			}
		});
	}
	
	private void appendData_Appointment(int idx)
	{
		
		
		final LinearLayout newCell = (LinearLayout)(View.inflate(this, R.layout.table_cell, null));
		
		UIUtils.setFontForAll((ViewGroup)newCell);
		
		AppointmentRecord record = MainActivity.aModel.list_appts.get(idx);
		
		((TextView)(newCell.findViewById(R.id.txtDoctor))).setText(Decode_string(record.doctor));
		((TextView)(newCell.findViewById(R.id.txtDate))).setText(Decode_string(record.date));
		((TextView)(newCell.findViewById(R.id.txtTime))).setText(Decode_string(record.time));
		
		newCell.setTag(idx);
		
		final int nidx = idx;
		
		apptListLayout.addView(newCell);
		
		newCell.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				AppointmentsActivity.nMode = nMode;
				AppointmentsActivity.nIdx = (Integer)arg0.getTag();
				Intent intent = new Intent(TableListActivity.this, AppointmentsActivity.class);
	    		startActivity(intent);
	    		finish();
			}
		});
		
		((Button)newCell.findViewById(R.id.btnDel)).setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Builder dlg = new AlertDialog.Builder(TableListActivity.this);
				dlg.setMessage("Would you like to delete this appointment?");
				
				dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
						int curIdx = Integer.valueOf(MainActivity.aModel.list_appts.get(nidx).idex);
						String stDoctor = Decode_string(MainActivity.aModel.list_appts.get(nidx).doctor);
						
						MainActivity.aModel.list_appts.remove(nidx);
						MainActivity.aModel.updateDB();
						load_appointment_list("");
						
						AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
						
						Intent intent3 = new Intent(mContext, ReminderAlarm.class);
						
						intent3.putExtra("ALERT_TITLE",stDoctor);
						intent3.putExtra("ALERT_DESCRIPTION", "Your appointment is in one month");

						PendingIntent pendingIntent3 = PendingIntent.getBroadcast(mContext, 3 * curIdx + 2, intent3, PendingIntent.FLAG_CANCEL_CURRENT);
						alarmManager.cancel(pendingIntent3);
						
						Intent intent1 = new Intent(mContext, ReminderAlarm.class);
						
						intent1.putExtra("ALERT_TITLE", stDoctor);
						intent1.putExtra("ALERT_DESCRIPTION", "Your appointment is in one week");

						PendingIntent pendingIntent1 = PendingIntent.getBroadcast(mContext, 3 * curIdx + 1, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
						alarmManager.cancel(pendingIntent1);
						
						Intent intent2 = new Intent(mContext, ReminderAlarm.class);
						
						intent2.putExtra("ALERT_TITLE", stDoctor);
						intent2.putExtra("ALERT_DESCRIPTION", "Your appointment is on tomorrow");

						PendingIntent pendingIntent2 = PendingIntent.getBroadcast(mContext, 3 * curIdx, intent2, PendingIntent.FLAG_CANCEL_CURRENT);
						alarmManager.cancel(pendingIntent2);
					}
				});
				
				dlg.setNegativeButton("Cancel",null);
				
				dlg.show();
				
				
				
			}
		});
		
	}
	
	private void appendData_Insurance(int idx)
	{
		
		
		final LinearLayout newCell = (LinearLayout)(View.inflate(this, R.layout.table_cell, null));
		
		UIUtils.setFontForAll((ViewGroup)newCell);
		
		InsuranceRecord record = MainActivity.insModel.list_appts.get(idx);
		
		((TextView)(newCell.findViewById(R.id.txtDoctor))).setText(Decode_string(record.Company));
		((TextView)(newCell.findViewById(R.id.txtDate))).setText(Decode_string(record.MemberID));
		((TextView)(newCell.findViewById(R.id.txtTime))).setText(Decode_string(record.Group));
		
		newCell.setTag(idx);
		
		final int nidx = idx;
		
		apptListLayout.addView(newCell);
		
		newCell.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				InsuranceActivity.nMode = nMode;
				InsuranceActivity.nIdx = (Integer)arg0.getTag();
				Intent intent = new Intent(TableListActivity.this, InsuranceActivity.class);
	    		startActivity(intent);
	    		finish();
			}
		});
		
		((Button)newCell.findViewById(R.id.btnDel)).setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Builder dlg = new AlertDialog.Builder(TableListActivity.this);
				dlg.setMessage("Would you like to delete this insurance?");
				
				dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
						MainActivity.insModel.list_appts.remove(nidx);
						MainActivity.insModel.updateDB();
						load_Insurance_list();
					}
				});
				
				dlg.setNegativeButton("Cancel",null);
				
				dlg.show();
			}
		});
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent i = new Intent(TableListActivity.this, ControlActivity.class);
		startActivity(i);
	}
	
	public class GridCellAdapter extends BaseAdapter implements OnClickListener {
		private static final String tag = "GridCellAdapter";
		private final Context _context;

		private final List<String> list;
		private static final int DAY_OFFSET = 1;
		private final String[] weekdays = new String[] { "Sun", "Mon", "Tue",
				"Wed", "Thu", "Fri", "Sat" };
		private final String[] months = { "January", "February", "March",
				"April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		private final int[] daysOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30,
				31, 30, 31 };
		private int daysInMonth;
		private int currentDayOfMonth;
		private int currentWeekDay;
		private int real_current_Month;
		private Button gridcell;
//		private final HashMap<String, Integer> eventsPerMonthMap;
		private int[] flag;
		
		private final SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"dd-MMM-yyyy");

		// Days in Current Month
		public GridCellAdapter(Context context, int textViewResourceId,
				int month, int year) {
			super();
			this._context = context;
			this.list = new ArrayList<String>();
			Log.d(tag, "==> Passed in Date FOR Month: " + month + " "
					+ "Year: " + year);
			Calendar calendar = Calendar.getInstance();
			setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
			setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
			setCurrentMonth(calendar.get(Calendar.MONTH));
			Log.d(tag, "New Calendar:= " + calendar.getTime().toString());
			Log.d(tag, "CurrentDayOfWeek :" + getCurrentWeekDay());
			Log.d(tag, "CurrentDayOfMonth :" + getCurrentDayOfMonth());
			
			flag = new int[32];
			
			// Print Month
			printMonth(month, year);
			
			

			// Find Number of Events
//			eventsPerMonthMap = findNumberOfEventsPerMonth(year, month);
		}
		
		private void findEventPair(int m_year, int m_month)
		{
			int i = 0;
			
			for(i = 0; i < 32; i ++)
			{
				flag[i] = -1;
			}
			
			for(i = 0; i < MainActivity.aModel.list_appts.size(); i ++)
			{
				AppointmentRecord record = MainActivity.aModel.list_appts.get(i);
				
//				String[] day_color = list.get(position).split("-");
				String[] ele = record.date.split("-");
				int month = Integer.valueOf(ele[0]);
				int day = Integer.valueOf(ele[1]);
				int year = Integer.valueOf(ele[2]);
				
				if(year != m_year || month != m_month) continue;
				flag[day] = i;
			}
		}

		private String getMonthAsString(int i) {
			return months[i];
		}

		private String getWeekDayAsString(int i) {
			return weekdays[i];
		}

		private int getNumberOfDaysOfMonth(int i) {
			return daysOfMonth[i];
		}

		public String getItem(int position) {
			return list.get(position);
		}

		public int getCount() {
			return list.size();
		}

		/**
		 * Prints Month
		 * 
		 * @param mm
		 * @param yy
		 */
		private void printMonth(int mm, int yy) {
			Log.d(tag, "==> printMonth: mm: " + mm + " " + "yy: " + yy);
			int trailingSpaces = 0;
			int daysInPrevMonth = 0;
			int prevMonth = 0;
			int prevYear = 0;
			int nextMonth = 0;
			int nextYear = 0;

			int currentMonth = mm - 1;
			
			findEventPair(year, month);
			
			String currentMonthName = getMonthAsString(currentMonth);
			daysInMonth = getNumberOfDaysOfMonth(currentMonth);

			Log.d(tag, "Current Month: " + " " + currentMonthName + " having "
					+ daysInMonth + " days.");

			GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);
			Log.d(tag, "Gregorian Calendar:= " + cal.getTime().toString());

			if (currentMonth == 11) {
				prevMonth = currentMonth - 1;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				nextMonth = 0;
				prevYear = yy;
				nextYear = yy + 1;
				Log.d(tag, "*->PrevYear: " + prevYear + " PrevMonth:"
						+ prevMonth + " NextMonth: " + nextMonth
						+ " NextYear: " + nextYear);
			} else if (currentMonth == 0) {
				prevMonth = 11;
				prevYear = yy - 1;
				nextYear = yy;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				nextMonth = 1;
				Log.d(tag, "**--> PrevYear: " + prevYear + " PrevMonth:"
						+ prevMonth + " NextMonth: " + nextMonth
						+ " NextYear: " + nextYear);
			} else {
				prevMonth = currentMonth - 1;
				nextMonth = currentMonth + 1;
				nextYear = yy;
				prevYear = yy;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				Log.d(tag, "***---> PrevYear: " + prevYear + " PrevMonth:"
						+ prevMonth + " NextMonth: " + nextMonth
						+ " NextYear: " + nextYear);
			}

			int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
			trailingSpaces = currentWeekDay;

			Log.d(tag, "Week Day:" + currentWeekDay + " is "
					+ getWeekDayAsString(currentWeekDay));
			Log.d(tag, "No. Trailing space to Add: " + trailingSpaces);
			Log.d(tag, "No. of Days in Previous Month: " + daysInPrevMonth);

			if (cal.isLeapYear(cal.get(Calendar.YEAR)))
				if (mm == 2)
					++daysInMonth;
				else if (mm == 3)
					++daysInPrevMonth;

			// Trailing Month days
			for (int i = 0; i < trailingSpaces; i++) {
				Log.d(tag,
						"PREV MONTH:= "
								+ prevMonth
								+ " => "
								+ getMonthAsString(prevMonth)
								+ " "
								+ String.valueOf((daysInPrevMonth
										- trailingSpaces + DAY_OFFSET)
										+ i));
				list.add(String
						.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET)
								+ i)
						+ "-GREY"
						+ "-"
						+ getMonthAsString(prevMonth)
						+ "-"
						+ prevYear);
			}

			// Current Month Days
			for (int i = 1; i <= daysInMonth; i++) {
				Log.d(currentMonthName, String.valueOf(i) + " "
						+ getMonthAsString(currentMonth) + " " + yy);
				if (flag[i] >= 0) {
					list.add(String.valueOf(i) + "-BLUE" + "-"
							+ getMonthAsString(currentMonth) + "-" + yy);
//				}else if(i == 21){
//					list.add(String.valueOf(i) + "-BLUE" + "-"
//							+ getMonthAsString(currentMonth) + "-" + yy);
				}
				else {
					list.add(String.valueOf(i) + "-WHITE" + "-"
							+ getMonthAsString(currentMonth) + "-" + yy);
				}
			}

			// Leading Month days
			for (int i = 0; i < list.size() % 7; i++) {
				Log.d(tag, "NEXT MONTH:= " + getMonthAsString(nextMonth));
				list.add(String.valueOf(i + 1) + "-GREY" + "-"
						+ getMonthAsString(nextMonth) + "-" + nextYear);
			}
		}

		/**
		 * NOTE: YOU NEED TO IMPLEMENT THIS PART Given the YEAR, MONTH, retrieve
		 * ALL entries from a SQLite database for that month. Iterate over the
		 * List of All entries, and get the dateCreated, which is converted into
		 * day.
		 * 
		 * @param year
		 * @param month
		 * @return
		 */
//		private HashMap<String, Integer> findNumberOfEventsPerMonth(int year,
//				int month) {
//			HashMap<String, Integer> map = new HashMap<String, Integer>();
//			
//			for(int i = 0; i < MainActivity.aModel.list_appts.size(); i ++)
//			{
//				
//			}
//			map.put(key, value)
//			return map;
//		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			if (row == null) {
				LayoutInflater inflater = (LayoutInflater) _context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(R.layout.screen_gridcell, parent, false);
			}

			// Get a reference to the Day gridcell
			gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);
			gridcell.setOnClickListener(this);

			// ACCOUNT FOR SPACING

			Log.d(tag, "Current Day: " + getCurrentDayOfMonth());
			String[] day_color = list.get(position).split("-");
			String theday = day_color[0];
			String themonth = day_color[2];
			String theyear = day_color[3];
//			if ((!eventsPerMonthMap.isEmpty()) && (eventsPerMonthMap != null)) {
//				if (eventsPerMonthMap.containsKey(theday)) {
//					num_events_per_day = (TextView) row
//							.findViewById(R.id.num_events_per_day);
//					Integer numEvents = (Integer) eventsPerMonthMap.get(theday);
//					num_events_per_day.setText(numEvents.toString());
//				}
//			}

			// Set the Day GridCell
			gridcell.setText(theday);
			gridcell.setTag(theday + "-" + themonth + "-" + theyear);
			Log.d(tag, "Setting GridCell " + theday + "-" + themonth + "-"
					+ theyear);

			if (day_color[1].equals("GREY")) {
				gridcell.setTextColor(getResources()
						.getColor(R.color.lightgray));
			}
			if (day_color[1].equals("WHITE")) {
				gridcell.setTextColor(getResources().getColor(
						R.color.black));
			}
			if (day_color[1].equals("BLUE")) {
				gridcell.setTextColor(getResources().getColor(R.color.orrange));
			}
			return row;
		}

		public void onClick(View view) {
			String date_month_year = (String) view.getTag();
//			selectedDayMonthYearButton.setText("Selected: " + date_month_year);
			
			String[] months = { "January", "February", "March",
					"April", "May", "June", "July", "August", "September",
					"October", "November", "December" };
			
			String[] ele = date_month_year.split("-");
			int theDay = Integer.valueOf(ele[0]);
			int theMonth = 0;
			
			for (int i = 0; i < 12; i++) {
				if (ele[1].equals(months[i])) {
					theMonth = i + 1;
					break;
				}
			}
			
//			theMonth = months[3];
			int theYear = Integer.valueOf(ele[2]);
			
			String strDate = theMonth + "-" + theDay + "-" + theYear;
			
//			if(flag[theday] >= 0) 
//			{
//				AppointmentsActivity.nMode = nMode;
//				AppointmentsActivity.nIdx = flag[theday];
//				AppointmentsActivity.flagCalendar = false;
//				Intent intent = new Intent(TableListActivity.this, AppointmentsActivity.class);
//        		startActivity(intent);
//			}
//			
//			Log.e("Selected date", date_month_year);
//			try {
//				Date parsedDate = dateFormatter.parse(date_month_year);
//				Log.d(tag, "Parsed Date: " + parsedDate.toString());
//			
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
			
			load_appointment_list(strDate);
		}

		public int getCurrentDayOfMonth() {
			return currentDayOfMonth;
		}

		private void setCurrentDayOfMonth(int currentDayOfMonth) {
			this.currentDayOfMonth = currentDayOfMonth;
		}

		public void setCurrentWeekDay(int currentWeekDay) {
			this.currentWeekDay = currentWeekDay;
		}
		
		public void setCurrentMonth(int current_Month){
			this.real_current_Month = current_Month;
		}
		
		public int getCurrentMonth()
		{
			return real_current_Month;
		}

		public int getCurrentWeekDay() {
			return currentWeekDay;
		}
	}
	
//	private void AlertView(String strMessage)
//	{
//		Builder dlg = new AlertDialog.Builder(TableListActivity.this);
//		dlg.setMessage(strMessage);
//		
//		dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int which) {
//			}
//		});
//		
//		dlg.show();
//	}
	

}
