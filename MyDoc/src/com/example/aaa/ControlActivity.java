package com.example.aaa;

import jane.data.UIUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ControlActivity extends Activity {
	static public int nCategory;
	String categoryName[] = {"Insurance", "Primary[Dr]", "Specialist[Dr]", "Dentist", "Optometrist", "Pharmacy", "Hospital", "Appointments", "Appointments", "Midecine History", "Midecine History", "Midecine Log"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.control);
		
		((TextView)findViewById(R.id.txtCategory)).setText(categoryName[nCategory]);
		
		ViewGroup vg = (ViewGroup)findViewById(R.id.ctrl_ViewGroup);
		
		UIUtils.setFontForAll(vg);
		setClickListener();		
	}
	
	private void setControl(int nMode)
	{
		Intent intent;
		switch (nCategory) {
		case 0:
			if(nMode > 0)
			{
				TableListActivity.nMode = nMode;
				TableListActivity.nCategory = nCategory;
				intent = new Intent(ControlActivity.this, TableListActivity.class);
				startActivity(intent);
			}
			else
			{
				InsuranceActivity.nMode = nMode;
				intent = new Intent(ControlActivity.this, InsuranceActivity.class);
				startActivity(intent);
			}
			break;
		
		case 1:
			PrimaryActivity.nMode = nMode;
			intent = new Intent(ControlActivity.this, PrimaryActivity.class);
			startActivity(intent);
			break;
		
		case 2:
			if(nMode > 0)
			{
				TableListActivity.nMode = nMode;
				TableListActivity.nCategory = nCategory;
				intent = new Intent(ControlActivity.this, TableListActivity.class);
				startActivity(intent);
			}
			else
			{
				SpecialistActivity.nMode = nMode;
				intent = new Intent(ControlActivity.this, SpecialistActivity.class);
				startActivity(intent);
			}
			break;
		
		case 3:
			DentistActivity.nMode = nMode;
			intent = new Intent(ControlActivity.this, DentistActivity.class);
			startActivity(intent);
			break;
		
		case 4:
			OptometristActivity.nMode = nMode;
			intent = new Intent(ControlActivity.this, OptometristActivity.class);
			startActivity(intent);
			break;
		
		case 5:
			if(nMode > 0)
			{
				TableListActivity.nMode = nMode;
				TableListActivity.nCategory = nCategory;
				intent = new Intent(ControlActivity.this, TableListActivity.class);
				startActivity(intent);
			}
			else
			{
				PharmacyActivity.nMode = nMode;
				intent = new Intent(ControlActivity.this, PharmacyActivity.class);
				startActivity(intent);
			}
			break;
		
		case 6:
			HospitalActivity.nMode = nMode;
			intent = new Intent(ControlActivity.this, HospitalActivity.class);
			startActivity(intent);
			break;

		case 10:
			HistoryActivity.nMode = nMode;
			intent = new Intent(ControlActivity.this, HistoryActivity.class);
			startActivity(intent);
			break;

		case 11:
			LogActivity.nMode = nMode;
			intent = new Intent(ControlActivity.this, LogActivity.class);
			startActivity(intent);
			break;
			
		default:
			if(nMode > 0)
			{
				TableListActivity.nMode = nMode;
				TableListActivity.nCategory = nCategory;
				intent = new Intent(ControlActivity.this, TableListActivity.class);
				startActivity(intent);
			}
			else
			{
				AppointmentsActivity.nMode = nMode;
				intent = new Intent(ControlActivity.this, AppointmentsActivity.class);
				startActivity(intent);
			}
			
		}
		
		finish();
	}
	
	private void setClickListener(){
		((Button) findViewById(R.id.btnAdd)).setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				setControl(0);
			}
		});

		((Button)findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ControlActivity.this, MainActivity.class);  
				startActivity(i);
				finish();
			}
		});

		((Button) findViewById(R.id.btnEdit)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				setControl(1);
			}
		});
		
		((Button) findViewById(R.id.btnView)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				setControl(2);
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent i = new Intent(ControlActivity.this, MainActivity.class);
		startActivity(i);
		super.onBackPressed();
	}

}
