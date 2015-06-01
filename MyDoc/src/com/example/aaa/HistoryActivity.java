package com.example.aaa;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HistoryActivity extends Activity {
	static public int nMode;
	SharedPreferences prefs;
	SharedPreferences.Editor editor;
	
	private String prefName = "HistoryInfo";
	LinearLayout linearActionsheet;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		prefs = getSharedPreferences(prefName, MODE_PRIVATE);
		editor = prefs.edit();
		
		if (nMode == 0) {
			setContentView(R.layout.new_history);
			
		} else if (nMode == 1) {
			setContentView(R.layout.new_history);
			this.setData();
			
		} else {
			setContentView(R.layout.history);
			this.setViewData();
		}
		
		if (nMode != 2) {
			linearActionsheet = (LinearLayout)this.findViewById(R.id.blinearAddtionalDisable);
			
			this.setClickListener();
		}
	}
	
	private void setClickListener(){
		((Button) findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				dismissActivity();
			}
		});
		((Button) findViewById(R.id.btnSave)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				saveData();
				dismissActivity();
			}
		});
		((Button) findViewById(R.id.btnMedicineAllergies)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				((LinearLayout)findViewById(R.id.blinearAddtionalDisable)).setVisibility(View.VISIBLE);
			}
		});
		((Button) findViewById(R.id.btnOtherAllergies)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				((LinearLayout)findViewById(R.id.actionsheet2)).setVisibility(View.VISIBLE);
			}
		});
		((Button) findViewById(R.id.btnMedicineCondition)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				((LinearLayout)findViewById(R.id.actionsheet3)).setVisibility(View.VISIBLE);
			}
		});
		
		btnYesNo();
		btnCancel();
		
		((EditText)this.findViewById(R.id.editBirthday)).setOnFocusChangeListener(new View.OnFocusChangeListener() {

			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus)
				{
					DateDialog();
					((EditText)findViewById(R.id.editFullname)).requestFocus();
				}
			}
		});
	}
	
	private void btnYesNo()
	{
		((Button) findViewById(R.id.actsheet_item_11)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				((Button) findViewById(R.id.btnMedicineAllergies)).setText("YES");
				((LinearLayout)findViewById(R.id.linear1)).setVisibility(View.VISIBLE);
				((LinearLayout)findViewById(R.id.blinearAddtionalDisable)).setVisibility(View.GONE);
			}
		});
		((Button) findViewById(R.id.actsheet_item_21)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				((Button) findViewById(R.id.btnOtherAllergies)).setText("YES");
				((LinearLayout)findViewById(R.id.linear2)).setVisibility(View.VISIBLE);
				((LinearLayout)findViewById(R.id.actionsheet2)).setVisibility(View.GONE);
			}
		});
		((Button) findViewById(R.id.actsheet_item_31)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				((Button) findViewById(R.id.btnMedicineCondition)).setText("Good");
				((LinearLayout)findViewById(R.id.linear3)).setVisibility(View.VISIBLE);
				((LinearLayout)findViewById(R.id.actionsheet3)).setVisibility(View.GONE);
			}
		});
		
		((Button) findViewById(R.id.actsheet_item_12)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				((Button) findViewById(R.id.btnMedicineAllergies)).setText("NO");
				((LinearLayout)findViewById(R.id.linear1)).setVisibility(View.GONE);
				((LinearLayout)findViewById(R.id.blinearAddtionalDisable)).setVisibility(View.GONE);
			}
		});
		((Button) findViewById(R.id.actsheet_item_22)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				((Button) findViewById(R.id.btnOtherAllergies)).setText("NO");
				((LinearLayout)findViewById(R.id.linear2)).setVisibility(View.GONE);
				((LinearLayout)findViewById(R.id.actionsheet2)).setVisibility(View.GONE);
			}
		});
		((Button) findViewById(R.id.actsheet_item_32)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				((Button) findViewById(R.id.btnMedicineCondition)).setText("Have Medical Problems");
				((LinearLayout)findViewById(R.id.linear3)).setVisibility(View.VISIBLE);
				((LinearLayout)findViewById(R.id.actionsheet3)).setVisibility(View.GONE);
			}
		});
	}
	
	private void btnCancel()
	{
		((Button) findViewById(R.id.btnCancel1)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				((LinearLayout)findViewById(R.id.blinearAddtionalDisable)).setVisibility(View.GONE);
			}
		});
		((Button) findViewById(R.id.btnCancel2)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				((LinearLayout)findViewById(R.id.actionsheet2)).setVisibility(View.GONE);
			}
		});
		((Button) findViewById(R.id.btnCancel3)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				((LinearLayout)findViewById(R.id.actionsheet3)).setVisibility(View.GONE);
			}
		});
	}
	
	private void DateDialog()
	{
		OnDateSetListener listener=new OnDateSetListener() {

			public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth)
			{
				((EditText)findViewById(R.id.editBirthday)).setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + year);

			}};

			DatePickerDialog dpDialog=new DatePickerDialog(this, listener, 2015, 1, 1);
			dpDialog.show();
	}
	
	private void dismissActivity()
	{
		Intent intent = new Intent( HistoryActivity.this,ControlActivity.class);
		startActivity(intent);
		finish();
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
	
	private void saveData()
	{
		String _strTemp = ((Button)findViewById(R.id.btnMedicineAllergies)).getText().toString();
		editor.putString("medicalAllergies", _strTemp);
		 _strTemp = ((Button)findViewById(R.id.btnOtherAllergies)).getText().toString();
		 editor.putString("otherAllergies", _strTemp);
		 _strTemp = ((Button)findViewById(R.id.btnMedicineCondition)).getText().toString();
		 editor.putString("medicalCondition", _strTemp);
		 
		 Calendar cal = Calendar.getInstance();
		 Integer	day = cal.get(Calendar.DAY_OF_MONTH);
		 Integer	month = cal.get(Calendar.MONTH) + 1;
		 Integer	year = cal.get(Calendar.YEAR);
		 
		 editor.putString("updatedDate", month.toString()+"-"+day.toString()+"-"+year.toString());
		 
		 _strTemp = ((EditText)findViewById(R.id.editFullname)).getText().toString();
		 editor.putString("fullname", _strTemp);
		 _strTemp = ((EditText)findViewById(R.id.editBirthday)).getText().toString();
		 editor.putString("birthday", _strTemp);
		
		 _strTemp = ((EditText)findViewById(R.id.editMedicineAllergies)).getText().toString();
		 editor.putString("medicalAllergiesVal", _strTemp);
		
		 _strTemp = ((EditText)findViewById(R.id.editOtherAllergies)).getText().toString();
		 editor.putString("otherAllergiesVal", _strTemp);
		 
		 _strTemp = ((EditText)findViewById(R.id.editMedicationCount)).getText().toString();
		 editor.putString("medicalConditionVal", _strTemp);
		 
		 _strTemp = ((EditText)findViewById(R.id.medication1when)).getText().toString();
		 editor.putString("contact1Fullname", _strTemp);
		 _strTemp = ((EditText)findViewById(R.id.medication1capsule)).getText().toString();
		 editor.putString("contact1Relationship", _strTemp);
		 _strTemp = ((EditText)findViewById(R.id.medication1what)).getText().toString();
		 editor.putString("contact1Phonenumber", _strTemp);
		
		 _strTemp = ((EditText)findViewById(R.id.editContact2Name)).getText().toString();
		 editor.putString("contact2Fullname", _strTemp);
		 _strTemp = ((EditText)findViewById(R.id.editContact2Relationship)).getText().toString();
		 editor.putString("contact2Relationship", _strTemp);
		 _strTemp = ((EditText)findViewById(R.id.editContact2Phonenumber)).getText().toString();
		 editor.putString("contact2Phonenumber", _strTemp);
		
		editor.commit();
	}
	
	private void setData()
	{		
		String strFullname = Decode_string( prefs.getString("fullname", ""));
		String strBirthday = Decode_string( prefs.getString("birthday", ""));
		
		String strMedicalAllergies = Decode_string(prefs.getString("medicalAllergies", "")) ;
		String strMedicalAllergiesVal = Decode_string(prefs.getString("medicalAllergiesVal", ""));
		
		String strOtherAllergies = Decode_string(prefs.getString("otherAllergies", "")) ;
		String strOtherAllergiesVal = Decode_string(prefs.getString("otherAllergiesVal", ""));
		
		String strMedicalCondition = Decode_string(prefs.getString("medicalCondition", "")) ;
		String strMedicalConditionVal = Decode_string(prefs.getString("medicalConditionVal", ""));
		
		String strContact1Fullname = Decode_string(prefs.getString("contact1Fullname", ""));
		String strContact1Relationship = Decode_string(prefs.getString("contact1Relationship", ""));
		String strContact1Phonenumber = Decode_string(prefs.getString("contact1Phonenumber", ""));
		
		String strContact2Fullname = Decode_string(prefs.getString("contact2Fullname", ""));
		String strContact2Relationship = Decode_string(prefs.getString("contact2Relationship", ""));
		String strContact2Phonenumber = Decode_string(prefs.getString("contact2Phonenumber", ""));
		
			if (!strMedicalAllergies.equals("Medicine Allergies")) {
				((Button)findViewById(R.id.btnMedicineAllergies)).setText(strMedicalAllergies);
				((LinearLayout)findViewById(R.id.linear1)).setVisibility(View.VISIBLE);
			}
			if (!strOtherAllergies.equals("Other Allergies")) {
				((Button)findViewById(R.id.btnOtherAllergies)).setText(strOtherAllergies);
				((LinearLayout)findViewById(R.id.linear2)).setVisibility(View.VISIBLE);
			}
			if (!strMedicalCondition.equals("Medical Condition")) {
				((Button)findViewById(R.id.btnMedicineCondition)).setText(strMedicalCondition);
				((LinearLayout)findViewById(R.id.linear3)).setVisibility(View.VISIBLE);
			}
			
			((EditText)findViewById(R.id.editFullname)).setText(strFullname);
			((EditText)findViewById(R.id.editBirthday)).setText(strBirthday);
			
			((EditText)findViewById(R.id.editMedicineAllergies)).setText(strMedicalAllergiesVal);
			
			((EditText)findViewById(R.id.editOtherAllergies)).setText(strOtherAllergiesVal);
			
			((EditText)findViewById(R.id.editMedicationCount)).setText(strMedicalConditionVal);

			((EditText)findViewById(R.id.medication1when)).setText(strContact1Fullname);
			((EditText)findViewById(R.id.medication1capsule)).setText(strContact1Relationship);
			((EditText)findViewById(R.id.medication1what)).setText(strContact1Phonenumber);
			
			((EditText)findViewById(R.id.editContact2Name)).setText(strContact2Fullname);
			((EditText)findViewById(R.id.editContact2Relationship)).setText(strContact2Relationship);
			((EditText)findViewById(R.id.editContact2Phonenumber)).setText(strContact2Phonenumber);
	}
	
	private void setViewData()
	{		
		String strFullname = Decode_string( prefs.getString("fullname", ""));
		String strBirthday = Decode_string( prefs.getString("birthday", ""));
		String strUpdatedDate = Decode_string( prefs.getString("updatedDate", ""));
		
		String strMedicalAllergies = Decode_string(prefs.getString("medicalAllergies", "")) ;
		String strMedicalAllergiesVal = Decode_string(prefs.getString("medicalAllergiesVal", ""));
		
		String strOtherAllergies = Decode_string(prefs.getString("otherAllergies", "")) ;
		String strOtherAllergiesVal = Decode_string(prefs.getString("otherAllergiesVal", ""));
		
		String strMedicalCondition = Decode_string(prefs.getString("medicalCondition", "")) ;
		String strMedicalConditionVal = Decode_string(prefs.getString("medicalConditionVal", ""));
		
		String strContact1Fullname = Decode_string(prefs.getString("contact1Fullname", ""));
		String strContact1Relationship = Decode_string(prefs.getString("contact1Relationship", ""));
		String strContact1Phonenumber = Decode_string(prefs.getString("contact1Phonenumber", ""));
		
		String strContact2Fullname = Decode_string(prefs.getString("contact2Fullname", ""));
		String strContact2Relationship = Decode_string(prefs.getString("contact2Relationship", ""));
		String strContact2Phonenumber = Decode_string(prefs.getString("contact2Phonenumber", ""));
		
			if (!strMedicalAllergies.equals("Medicine Allergies")) {
				((Button)findViewById(R.id.btnMedicineAllergies)).setText(strMedicalAllergies);
				((LinearLayout)findViewById(R.id.linear1)).setVisibility(View.VISIBLE);
			}
			if (!strOtherAllergies.equals("Other Allergies")) {
				((Button)findViewById(R.id.btnOtherAllergies)).setText(strOtherAllergies);
				((LinearLayout)findViewById(R.id.linear2)).setVisibility(View.VISIBLE);
			}
			if (!strMedicalCondition.equals("Medical Condition")) {
				((Button)findViewById(R.id.btnMedicineCondition)).setText(strMedicalCondition);
				((LinearLayout)findViewById(R.id.linear3)).setVisibility(View.VISIBLE);
			}
			
			((TextView)findViewById(R.id.editFullname)).setText(strFullname);
			((TextView)findViewById(R.id.editBirthday)).setText(strBirthday);
			((TextView)this.findViewById(R.id.editUpdatedDate)).setText(strUpdatedDate);
			
			String[] ele = strBirthday.split("-");
			
			int nYear;
			
			if (ele.length > 1) {
				nYear = Integer.valueOf(ele[2]);
				int nMonth = Integer.valueOf(ele[0]);
				if (nMonth > 4) {
					((TextView)this.findViewById(R.id.btnAge)).setText(Integer.toString(2015 - nYear - 1));
				} else {
					((TextView)this.findViewById(R.id.btnAge)).setText(Integer.toString(2015 - nYear));
				}
			}
			
			((TextView)findViewById(R.id.editMedicineAllergies)).setText(strMedicalAllergiesVal);
			
			((TextView)findViewById(R.id.editOtherAllergies)).setText(strOtherAllergiesVal);
			
			((TextView)findViewById(R.id.editMedicationCount)).setText(strMedicalConditionVal);
			
			((TextView)findViewById(R.id.medication1when)).setText(strContact1Fullname);
			((TextView)findViewById(R.id.medication1capsule)).setText(strContact1Relationship);
			((TextView)findViewById(R.id.medication1what)).setText(strContact1Phonenumber);
			
			((TextView)findViewById(R.id.editContact2Name)).setText(strContact2Fullname);
			((TextView)findViewById(R.id.editContact2Relationship)).setText(strContact2Relationship);
			((TextView)findViewById(R.id.editContact2Phonenumber)).setText(strContact2Phonenumber);

	}

}
