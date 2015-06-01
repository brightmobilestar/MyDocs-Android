package com.example.aaa;

import jane.data.UIUtils;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HospitalActivity extends Activity {
	static public int nMode;
	SharedPreferences prefs;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		prefs = getPreferences(MODE_PRIVATE);   
		String strMark = prefs.getString("Mark", "");
		
		if(nMode > 0 && strMark.equals(""))
		{
			Builder dlg = new AlertDialog.Builder(HospitalActivity.this);
			dlg.setMessage("There is no data about the Hospital");
			
			dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent i = new Intent(HospitalActivity.this, ControlActivity.class);
					startActivity(i);
					finish();
				}
			});
			
			dlg.show();
		}
		
		ViewGroup vg;
		
		if(nMode < 2)
		{
			setContentView(R.layout.new_hospital);
			vg = (ViewGroup)findViewById(R.id.hosp_new_ViewGroup);
			
			EditText edt_clientphone = (EditText) findViewById(R.id.editMedicineAllergies);
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
			setContentView(R.layout.hospital);
			vg = (ViewGroup)findViewById(R.id.hosp_ViewGroup);
		}	
		
		UIUtils.setFontForAll(vg);
		
		init();
		setClickListener(); 
		
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
	
	private void init()
	{
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		
		String strToday = month + "-" + day + "-" + year;
		((TextView)findViewById(R.id.txtDate_title)).setText(strToday);
		
		prefs = getPreferences(MODE_PRIVATE);
		
		String strHospital = Decode_string(prefs.getString("Hospital", ""));
		String strAddress = Decode_string(prefs.getString("Address", ""));
		String strCity = Decode_string(prefs.getString("City", ""));
		String strState = Decode_string(prefs.getString("State", ""));
		String strZip = Decode_string(prefs.getString("Zip", ""));
		String strPhone = Decode_string(prefs.getString("Phone", ""));
		
		if(nMode == 2)
		{
			((TextView)findViewById(R.id.txtHospital)).setText(strHospital);
			((TextView)findViewById(R.id.txtAddress)).setText(strAddress);
			((TextView)findViewById(R.id.medication1capsule)).setText(strCity);
			((TextView)findViewById(R.id.editMedicationCount)).setText(strState);
			((TextView)findViewById(R.id.editOtherAllergies)).setText(strZip);
			((TextView)findViewById(R.id.editMedicineAllergies)).setText(strPhone);
		}
		else
		{
			((EditText)findViewById(R.id.txtHospital)).setText(strHospital);
			((EditText)findViewById(R.id.txtAddress)).setText(strAddress);
			((EditText)findViewById(R.id.medication1capsule)).setText(strCity);
			((EditText)findViewById(R.id.editMedicationCount)).setText(strState);
			((EditText)findViewById(R.id.editOtherAllergies)).setText(strZip);
			((EditText)findViewById(R.id.editMedicineAllergies)).setText(strPhone);
		}
		
	}
	
	private void setClickListener()
	{
		((Button) findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(HospitalActivity.this, ControlActivity.class);
				startActivity(i);
				finish();
			}
		 });
		
		((Button)findViewById(R.id.btnHome)).setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HospitalActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		if(nMode < 2)
		{
			((Button) findViewById(R.id.btnSave)).setOnClickListener(new OnClickListener(){
				public void onClick(View v) {
					String strHospital = ((EditText)findViewById(R.id.txtHospital)).getText().toString();
					String strAddress = ((EditText)findViewById(R.id.txtAddress)).getText().toString();
					String strCity = ((EditText)findViewById(R.id.medication1capsule)).getText().toString();
					String strState = ((EditText)findViewById(R.id.editMedicationCount)).getText().toString();
					String strZip = ((EditText)findViewById(R.id.editOtherAllergies)).getText().toString();
					String strPhone = ((EditText)findViewById(R.id.editMedicineAllergies)).getText().toString();
					
					if(strHospital == null || strHospital.equals(""))
					{
						AlertView("Input the Hospital name");
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
					
					prefs = getPreferences(MODE_PRIVATE);
					SharedPreferences.Editor editor = prefs.edit();
					
					editor.putString("Mark", "True");
					editor.putString("Hospital", Code_string(strHospital));
					editor.putString("Address", Code_string(strAddress));
					editor.putString("City", Code_string(strCity));
					editor.putString("State", Code_string(strState));
					editor.putString("Zip", Code_string(strZip));
					editor.putString("Phone", Code_string(strPhone));
					
					editor.commit();
					
					Intent intent = new Intent(HospitalActivity.this, SuccessWarningActivity.class);
	        		startActivity(intent);
	        		
					finish();
				}
			 });
		}
		else
		{
			((Button)findViewById(R.id.btnPhone)).setOnClickListener(new OnClickListener() {
				
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					String strPhone = ((TextView)findViewById(R.id.editMedicineAllergies)).getText().toString();
					
					if(strPhone.equals("")) return;
					
					Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:" + strPhone));
					startActivity(i);
				}
			});
		}
	}
	
	private void AlertView(String strMessage)
	{
		Builder dlg = new AlertDialog.Builder(HospitalActivity.this);
		dlg.setMessage(strMessage);
		
		dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		
		dlg.show();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent i = new Intent(HospitalActivity.this, ControlActivity.class);
		startActivity(i);
		finish();
		super.onBackPressed();
	}
}
