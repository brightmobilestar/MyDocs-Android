package com.example.aaa;

import jane.data.AppointmentRecord;
import jane.data.UIUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NotifyActivityAppoinment extends Activity {
	
	private boolean flag_Reminder;
	private int flag_time;
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.notify_appointment);
	    
	    String defaultTitle="";
	    String defaultDescription ="";
	    int defaultID = 0;
	    Bundle extras = getIntent().getExtras();
	    
	    if (extras!=null)
	    {
	    	defaultID = extras.getInt("ALERT_ID");
	    	defaultTitle = extras.getString("ALERT_TITLE");
	    	defaultDescription = extras.getString("ALERT_DESCRIPTION");
	    }
	    
	    AppointmentRecord record = MainActivity.aModel.list_appts.get(defaultID);
	    
		flag_Reminder = (record.reminder_check.equals("1"))?true:false;
		
		if(flag_Reminder)
		{
			((Button) findViewById(R.id.chk_Reminder_Yes)).setBackgroundResource(R.drawable.checkbox_checked);
			((Button) findViewById(R.id.chk_Reminder_No)).setBackgroundResource(R.drawable.checkbox);
		}
		
		flag_time = Integer.valueOf(record.reminder_time);
		
		switch(flag_time) {
			case 0:
				((Button) findViewById(R.id.chk_8am)).setBackgroundResource(R.drawable.checkbox_checked_1);
				break;
				
			case 1:
				((Button) findViewById(R.id.chk_12pm)).setBackgroundResource(R.drawable.checkbox_checked_1);
				break;
				
			case 2:
				((Button) findViewById(R.id.chk_8pm)).setBackgroundResource(R.drawable.checkbox_checked_1);
				break;
				
				default:
					break;
		}
				
			((TextView)findViewById(R.id.txtDate)).setText(Decode_string(record.date));
			((TextView)findViewById(R.id.txtTime)).setText(Decode_string(record.time));
			((TextView)findViewById(R.id.medication1what)).setText(Decode_string(record.doctor));
			((TextView)findViewById(R.id.txtAddress)).setText(Decode_string(record.address));
			((TextView)findViewById(R.id.medication1capsule)).setText(Decode_string(record.city));
			((TextView)findViewById(R.id.editMedicationCount)).setText(Decode_string(record.state));
			((TextView)findViewById(R.id.editOtherAllergies)).setText(Decode_string(record.zip));
			((TextView)findViewById(R.id.editMedicineAllergies)).setText(Decode_string(record.phone));
			((TextView)findViewById(R.id.editBirthday)).setText(Decode_string(record.mobile));
			((TextView)findViewById(R.id.txtEmail)).setText(Decode_string(record.email));
			
			((Button)findViewById(R.id.btnHome)).setOnClickListener(new OnClickListener() {
				
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(NotifyActivityAppoinment.this, MainActivity.class);
	        		startActivity(intent);
	        		finish();
				}
			});
			
			((Button) findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener(){
				public void onClick(View v) {
					
					Intent intent = new Intent(NotifyActivityAppoinment.this, ControlActivity.class);
	        		startActivity(intent);
					
					finish();
				}
			 });
			
			
	  }
	
	private String Decode_string(String str)
	{
		if(str.equals("NULL")) return "";
		return str;
	}
}
