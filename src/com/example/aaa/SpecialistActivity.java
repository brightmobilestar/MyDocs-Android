package com.example.aaa;

import java.util.Calendar;

import jane.data.DocModel;
import jane.data.DocRecord;
import jane.data.SpecialistRecord;
import jane.data.UIUtils;
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

public class SpecialistActivity extends Activity {
	static public int nMode;
	static public int nIdx;
	SharedPreferences prefs;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		ViewGroup vg;
		
		if(nMode < 2) {
			setContentView(R.layout.new_specialist);
			vg = (ViewGroup)findViewById(R.id.spec_new_ViewGroup);
			
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
	        
	        
	        EditText edt_clientphone1 = (EditText) findViewById(R.id.txtMobile);
	        InputFilter filter1 = new InputFilter() {

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

	        edt_clientphone1.setFilters(new InputFilter[] { filter1 });
		}
		else {
			setContentView(R.layout.specialist);
			vg = (ViewGroup)findViewById(R.id.spec_ViewGroup);
		}
		
		UIUtils.setFontForAll(vg);
		
		init();
		setClickListener(); 
	}
	
	private String Decode_string(String str) {
		if(str.equals("NULL")) return "";
		return str;
	}
	
	private String Code_string(String str) {
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
		
		if (nMode == 0)
		{
			return;
		}
		
		SpecialistRecord record = MainActivity.specModel.list_appts.get(nIdx);

		if(nMode == 1)
		{			
			((EditText)findViewById(R.id.txtDoctor)).setText(Decode_string(record.Doctor));
			((EditText)findViewById(R.id.txtSpecialist)).setText(Decode_string(record.Specialist));
			((EditText)findViewById(R.id.txtAddress)).setText(Decode_string(record.address));
			((EditText)findViewById(R.id.txtCity)).setText(Decode_string(record.city));
			((EditText)findViewById(R.id.txtState)).setText(Decode_string(record.state));
			((EditText)findViewById(R.id.txtZip)).setText(Decode_string(record.zip));
			((EditText)findViewById(R.id.txtPhone)).setText(Decode_string(record.phone));
			((EditText)findViewById(R.id.txtMobile)).setText(Decode_string(record.mobile));
			((EditText)findViewById(R.id.txtEmail)).setText(Decode_string(record.Email));
		}
		else 
		{
			((TextView)findViewById(R.id.txtDoctor)).setText(Decode_string(record.Doctor));
			((TextView)findViewById(R.id.txtSpecialist)).setText(Decode_string(record.Specialist));
			((TextView)findViewById(R.id.txtAddress)).setText(Decode_string(record.address));
			((TextView)findViewById(R.id.txtCity)).setText(Decode_string(record.city));
			((TextView)findViewById(R.id.txtState)).setText(Decode_string(record.state));
			((TextView)findViewById(R.id.txtZip)).setText(Decode_string(record.zip));
			((TextView)findViewById(R.id.txtPhone)).setText(Decode_string(record.phone));
			((TextView)findViewById(R.id.txtMobile)).setText(Decode_string(record.mobile));
			((TextView)findViewById(R.id.txtEmail)).setText(Decode_string(record.Email));
		}		
	}
	
	private void setClickListener()
	{
		((Button) findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				switch (nMode) {
				case 0:
					Intent intent = new Intent(SpecialistActivity.this, ControlActivity.class);
					startActivity(intent);
					
					break;
				default:
					Intent i = new Intent(SpecialistActivity.this, TableListActivity.class);
					startActivity(i);
					
					break;
				}

				finish();
			}
		 });
		
		((Button)findViewById(R.id.btnHome)).setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SpecialistActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		if(nMode < 2)
		{
			((Button) findViewById(R.id.btnSave)).setOnClickListener(new OnClickListener(){
				public void onClick(View v) {
					String strDoctor = ((EditText)findViewById(R.id.txtDoctor)).getText().toString();
					String strSpecialist = ((EditText)findViewById(R.id.txtSpecialist)).getText().toString();
					String strAddress = ((EditText)findViewById(R.id.txtAddress)).getText().toString();
					String strCity = ((EditText)findViewById(R.id.txtCity)).getText().toString();
					String strState = ((EditText)findViewById(R.id.txtState)).getText().toString();
					String strZip = ((EditText)findViewById(R.id.txtZip)).getText().toString();
					String strPhone = ((EditText)findViewById(R.id.txtPhone)).getText().toString();
					String strMobile = ((EditText)findViewById(R.id.txtMobile)).getText().toString();
					String strEmail = ((EditText)findViewById(R.id.txtEmail)).getText().toString();
					
					if(strDoctor == null || strDoctor.equals(""))
					{
						AlertView("Input the doctor name");
						return;
					}
					
					if(strSpecialist == null || strSpecialist.equals(""))
					{
						AlertView("Input the specialist");
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
					
					SpecialistRecord record = new SpecialistRecord();

					record.Doctor = Code_string(strDoctor);
					record.Specialist = Code_string(strSpecialist);
					record.address = Code_string(strAddress);
					record.city = Code_string(strCity);
					record.state = Code_string(strState);
					record.zip = Code_string(strZip);
					record.phone = Code_string(strPhone);
					record.mobile = Code_string(strMobile);
					record.Email = Code_string(strEmail);
					
					DocRecord other_record = new DocRecord();
					
					other_record.doctor = record.Doctor;
					other_record.address = record.address;
					other_record.city = record.city;
					other_record.state = record.state;
					other_record.zip = record.zip;
					other_record.phone = record.phone;
					other_record.mobile = record.mobile;
					other_record.email = record.Email;

					if(nMode == 0)
					{
						MainActivity.docModel.maxIdx ++;
						
						int other_curIdx = MainActivity.docModel.maxIdx;
						
						other_record.idx = String.valueOf(other_curIdx);
						MainActivity.docModel.list_appts.add(other_record);
						MainActivity.docModel.updateDB();
						
						record.total_index = String.valueOf(other_curIdx);
						
						MainActivity.specModel.maxIdx ++;

						int curIdx =  MainActivity.specModel.maxIdx;

						record.idex = String.valueOf(curIdx);
						MainActivity.specModel.list_appts.add(record);
						MainActivity.specModel.updateDB();
						
					}
					else
					{
						record.idex = MainActivity.specModel.list_appts.get(nIdx).idex;
						record.total_index = MainActivity.specModel.list_appts.get(nIdx).total_index;
						
						MainActivity.specModel.updateArray(record);
						MainActivity.specModel.updateDB();
						
						other_record.idx = record.total_index;
						
						MainActivity.docModel.updateArray(other_record);
						MainActivity.docModel.updateDB();
					}
					
					Intent intent = new Intent(SpecialistActivity.this, SuccessWarningActivity.class);
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
	
	private void AlertView(String strMessage)
	{
		Builder dlg = new AlertDialog.Builder(SpecialistActivity.this);
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
		switch (nMode) {
		case 0:
			Intent intent = new Intent(SpecialistActivity.this, ControlActivity.class);
			startActivity(intent);

			break;
		default:
			Intent i = new Intent(SpecialistActivity.this, TableListActivity.class);
			startActivity(i);

			break;
		}

		finish();
		super.onBackPressed();
	}
}
