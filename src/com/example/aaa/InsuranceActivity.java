package com.example.aaa;

import java.util.Calendar;

import jane.data.InsuranceRecord;
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

public class InsuranceActivity extends Activity {
	static public int nMode;
	static public int nIdx;
	SharedPreferences prefs;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		ViewGroup vg;
		
		if(nMode < 2)
		{
			setContentView(R.layout.new_insurance);
			vg = (ViewGroup)findViewById(R.id.ins_new_ViewGroup);
			
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
			setContentView(R.layout.insurance);
			vg = (ViewGroup)findViewById(R.id.ins_ViewGroup);
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
		
		if(nMode == 0)
		{
			return;
		}

		InsuranceRecord record = MainActivity.insModel.list_appts.get(nIdx);

		if(nMode == 1)
		{			
			((EditText)findViewById(R.id.txtMember)).setText(Decode_string(record.Member));
			((EditText)findViewById(R.id.txtCompany)).setText(Decode_string(record.Company));
			((EditText)findViewById(R.id.txtAddress)).setText(Decode_string(record.address));
			((EditText)findViewById(R.id.txtCity)).setText(Decode_string(record.city));
			((EditText)findViewById(R.id.txtState)).setText(Decode_string(record.state));
			((EditText)findViewById(R.id.txtZip)).setText(Decode_string(record.zip));
			((EditText)findViewById(R.id.txtPhone)).setText(Decode_string(record.phone));
			((EditText)findViewById(R.id.txtPolicy)).setText(Decode_string(record.PolicyNumber));
			((EditText)findViewById(R.id.txtID)).setText(Decode_string(record.MemberID));
			((EditText)findViewById(R.id.txtGroup)).setText(Decode_string(record.Group));
			((EditText)findViewById(R.id.txtBin)).setText(Decode_string(record.BIN));
		}
		else
		{
			((TextView)findViewById(R.id.txtMember)).setText(Decode_string(record.Member));
			((TextView)findViewById(R.id.txtCompany)).setText(Decode_string(record.Company));
			((TextView)findViewById(R.id.txtAddress)).setText(Decode_string(record.address));
			((TextView)findViewById(R.id.txtCity)).setText(Decode_string(record.city));
			((TextView)findViewById(R.id.txtState)).setText(Decode_string(record.state));
			((TextView)findViewById(R.id.txtZip)).setText(Decode_string(record.zip));
			((TextView)findViewById(R.id.txtPhone)).setText(Decode_string(record.phone));
			((TextView)findViewById(R.id.txtPolicy)).setText(Decode_string(record.PolicyNumber));
			((TextView)findViewById(R.id.txtID)).setText(Decode_string(record.MemberID));
			((TextView)findViewById(R.id.txtGroup)).setText(Decode_string(record.Group));
			((TextView)findViewById(R.id.txtBin)).setText(Decode_string(record.BIN));
		}
	}
	
		
	private void setClickListener()
	{
		((Button) findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				switch (nMode) {
				case 0:
					Intent intent = new Intent(InsuranceActivity.this, ControlActivity.class);
					startActivity(intent);

					break;
				default:
					Intent i = new Intent(InsuranceActivity.this, TableListActivity.class);
					startActivity(i);

					break;
				}

				finish();
			}
		});
		
		((Button)findViewById(R.id.btnHome)).setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(InsuranceActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		if(nMode < 2)
		{
			((Button) findViewById(R.id.btnSave)).setOnClickListener(new OnClickListener(){
				public void onClick(View v) {
					String strMember = ((EditText)findViewById(R.id.txtMember)).getText().toString();
					String strCompany = ((EditText)findViewById(R.id.txtCompany)).getText().toString();
					String strAddress = ((EditText)findViewById(R.id.txtAddress)).getText().toString();
					String strCity = ((EditText)findViewById(R.id.txtCity)).getText().toString();
					String strState = ((EditText)findViewById(R.id.txtState)).getText().toString();
					String strZip = ((EditText)findViewById(R.id.txtZip)).getText().toString();
					String strPhone = ((EditText)findViewById(R.id.txtPhone)).getText().toString();
					String strPolicy = ((EditText)findViewById(R.id.txtPolicy)).getText().toString();
					String strID = ((EditText)findViewById(R.id.txtID)).getText().toString();
					String strGroup = ((EditText)findViewById(R.id.txtGroup)).getText().toString();
					String strBin = ((EditText)findViewById(R.id.txtBin)).getText().toString();
					
					if(strMember == null || strMember.equals(""))
					{
						AlertView("Input the member name");
						return;
					}
					
					if(strCompany == null || strCompany.equals(""))
					{
						AlertView("Input the insurance company name");
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
//					if(strPolicy == null || strPolicy.equals(""))
//					{
//						AlertView("Input the policy number");
//						return;
//					}
//					
//					if(strID == null || strID.equals(""))
//					{
//						AlertView("Input the member ID");
//						return;
//					}
//					
//					if(strGroup == null || strGroup.equals(""))
//					{
//						AlertView("Input the group");
//						return;
//					}
//					
//					if(strBin == null || strBin.equals(""))
//					{
//						AlertView("Input the bin");
//						return;
//					}
					
					InsuranceRecord record = new InsuranceRecord();
					
					record.Member = Code_string(strMember);
					record.Company = Code_string(strCompany);
					record.address = Code_string(strAddress);
					record.city = Code_string(strCity);
					record.state = Code_string(strState);
					record.zip = Code_string(strZip);
					record.phone = Code_string(strPhone);
					record.PolicyNumber = Code_string(strPolicy);
					record.MemberID = Code_string(strID);
					record.Group = Code_string(strGroup);
					record.BIN = Code_string(strBin);
					
					if(nMode == 0)
					{
						MainActivity.insModel.maxIdx ++;
						
						int curIdx =  MainActivity.aModel.maxIdx;
						
						record.idex = String.valueOf(curIdx);
						MainActivity.insModel.list_appts.add(record);
						MainActivity.insModel.updateDB();
					}
					else
					{
						record.idex = MainActivity.insModel.list_appts.get(nIdx).idex;
						MainActivity.insModel.updateArray(record);
						MainActivity.insModel.updateDB();
					}
					
					Intent intent = new Intent(InsuranceActivity.this, SuccessWarningActivity.class);
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
		}
	}
	
	private void AlertView(String strMessage)
	{
		Builder dlg = new AlertDialog.Builder(InsuranceActivity.this);
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
			Intent intent = new Intent(InsuranceActivity.this, ControlActivity.class);
			startActivity(intent);

			break;
		default:
			Intent i = new Intent(InsuranceActivity.this, TableListActivity.class);
			startActivity(i);

			break;
		}

		finish();
		super.onBackPressed();
	}
}
