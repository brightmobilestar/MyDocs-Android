package com.example.aaa;

import jane.data.AppointmentRecord;
import jane.data.UIUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

//import android.annotation.SuppressLint;
//import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

//@TargetApi(3)
public class CalendarActivity extends Activity implements OnClickListener {
	private static final String tag = "CalendarActivity";
	static public int nMode;
	
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

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.my_calendar_view);
		
		ViewGroup vg = (ViewGroup)findViewById(R.id.cal_ViewGroup);

		UIUtils.setFontForAll(vg);
		
		_calendar = Calendar.getInstance(Locale.getDefault());
		month = _calendar.get(Calendar.MONTH) + 1;
		year = _calendar.get(Calendar.YEAR);
		Log.d(tag, "Calendar Instance:= " + "Month: " + month + " " + "Year: "
				+ year);

//		selectedDayMonthYearButton = (Button) this
//				.findViewById(R.id.selectedDayMonthYear);
//		selectedDayMonthYearButton.setText("Selected: ");
		
		prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
		prevMonth.setOnClickListener(this);
		
		currentMonth = (TextView) this.findViewById(R.id.currentMonth);
		currentMonth.setText(DateFormat.format(dateTemplate,
				_calendar.getTime()));
		
		nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
		nextMonth.setOnClickListener(this);
		
		calendarView = (GridView) this.findViewById(R.id.calendar);
		
		// Initialised
		adapter = new GridCellAdapter(getApplicationContext(),
				R.id.calendar_day_gridcell, month, year);
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
		
		setClickListener();
	}
	
	private void setClickListener()
	{
//		((Button)this.findViewById(R.id.settings)).setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent i = new Intent(CalendarActivity.this, TableListActivity.class);
//				startActivity(i);
//				finish();
//			}
//		});
//		
//		((Button)this.findViewById(R.id.addEvent)).setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				AppointmentsActivity.nMode = 0;
//				AppointmentsActivity.flagCalendar = true;
//				Intent intent = new Intent(CalendarActivity.this, AppointmentsActivity.class);
//        		startActivity(intent);
//        		finish();
//			}
//		});
	}

	/**
	 * 
	 * @param month
	 * @param year
	 */
	private void setGridCellAdapterToDate(int month, int year) {
		adapter = new GridCellAdapter(getApplicationContext(),
				R.id.calendar_day_gridcell, month, year);
		_calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
		currentMonth.setText(DateFormat.format(dateTemplate,
				_calendar.getTime()));
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
	}
	
	public void onClick(View v) {
		if (v == prevMonth) {
			if (month <= 1) {
				month = 12;
				year--;
			} else {
				month--;
			}
			Log.d(tag, "Setting Prev Month in GridCellAdapter: " + "Month: "
					+ month + " Year: " + year);
			setGridCellAdapterToDate(month, year);
		}
		if (v == nextMonth) {
			if (month > 11) {
				month = 1;
				year++;
			} else {
				month++;
			}
			Log.d(tag, "Setting Next Month in GridCellAdapter: " + "Month: "
					+ month + " Year: " + year);
			setGridCellAdapterToDate(month, year);
		}

	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		setGridCellAdapterToDate(month, year);
		super.onResume();
	}

	@Override
	public void onDestroy() {
		Log.d(tag, "Destroying View ...");
		super.onDestroy();
	}

	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent i = new Intent(CalendarActivity.this, TableListActivity.class);
		startActivity(i);
		super.onBackPressed();
	}


	// Inner Class
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
			
			String[] ele = date_month_year.split("-");
			int theday = Integer.valueOf(ele[0]);
			
			if(flag[theday] >= 0) 
			{
				AppointmentsActivity.nMode = nMode;
				AppointmentsActivity.nIdx = flag[theday];
				AppointmentsActivity.flagCalendar = true;
				Intent intent = new Intent(CalendarActivity.this, AppointmentsActivity.class);
        		startActivity(intent);
			}
			
			Log.e("Selected date", date_month_year);
			try {
				Date parsedDate = dateFormatter.parse(date_month_year);
				Log.d(tag, "Parsed Date: " + parsedDate.toString());

			} catch (ParseException e) {
				e.printStackTrace();
			}
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
}

