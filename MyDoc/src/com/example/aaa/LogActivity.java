package com.example.aaa;

import java.util.Calendar;

import com.example.aaa.R;
import com.example.aaa.R.color;

import android.R.integer;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LogActivity extends Activity {
	static public int nMode;
	SharedPreferences prefs;
	SharedPreferences.Editor editor;
	
	private String prefName = "LogInfo";
	private String btnIndex;
	LinearLayout linearMedication;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		prefs = getSharedPreferences(prefName, MODE_PRIVATE);
		editor = prefs.edit();
		
		if (nMode == 0) {
			setContentView(R.layout.new_log);
			
			refreshButtonStatus();
		} else if (nMode == 1) {
			setContentView(R.layout.new_log);
			this.setData();
			refreshButtonStatus();
		} else {
			setContentView(R.layout.log);
			
			this.readData();
		}
		
		if (nMode != 2) {
			linearMedication = (LinearLayout)this.findViewById(R.id.linearMedication);
			
			this.setClickListener();
		}
		
		
	}
	
	
	private void setData()
	{
		String strFullname = Decode_string( prefs.getString("fullname", "")); 
		String strBirthday = Decode_string( prefs.getString("birthday", ""));
		
		((EditText)this.findViewById(R.id.editFullname)).setText(strFullname);
		((EditText)this.findViewById(R.id.editBirthday)).setText(strBirthday);
		
		for (int i = 1; i < 10; i++) {
			String _btnIndex = Integer.toString(i);
			
			String strTemp = Decode_string( prefs.getString("medicationName" + _btnIndex, ""));
			
			switch (i) {
			case 1:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd1)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit1)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd1)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd1)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit1)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit1)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;
				
			case 2:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd2)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit2)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd2)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd2)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit2)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit2)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;
				
			case 3:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd3)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit3)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd3)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd3)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit3)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit3)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;
				
			case 4:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd4)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit4)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd4)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd4)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit4)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit4)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;

			case 5:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd5)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit5)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd5)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd5)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit5)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit5)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;

			case 6:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd6)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit6)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd6)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd6)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit6)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit6)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;

			case 7:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd7)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit7)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd7)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd7)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit7)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit7)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;

			case 8:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd8)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit8)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd8)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd8)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit8)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit8)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;

			case 9:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd9)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit9)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd9)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd9)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit9)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit9)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;

			default:
				break;
			}
		}
	}
	
	private void saveData()
	{
		String _strTemp = ((EditText)findViewById(R.id.editFullname)).getText().toString();
		 editor.putString("fullname", _strTemp);
		 
		 _strTemp = ((EditText)findViewById(R.id.editBirthday)).getText().toString();
		 editor.putString("birthday", _strTemp);
		 
		 Calendar cal = Calendar.getInstance();
		 Integer	day = cal.get(Calendar.DAY_OF_MONTH);
		 Integer	month = cal.get(Calendar.MONTH) + 1;
		 Integer	year = cal.get(Calendar.YEAR);
		 
		 editor.putString("updatedDate", month.toString()+"-"+ day.toString()+"-"+year.toString());
		 
		 editor.commit();
	}
	
	private void saveMedicationData()
	{
		String _strTemp = ((EditText)findViewById(R.id.editMedicineName)).getText().toString();
		 editor.putString("medicationName" + btnIndex, _strTemp);
		 
		 _strTemp = ((EditText)findViewById(R.id.editMedicineDosage)).getText().toString();
		 editor.putString("medicationDosage" + btnIndex, _strTemp);
		 
		 _strTemp = ((EditText)findViewById(R.id.editMedicineWhen)).getText().toString();
		 editor.putString("medicationWhen" + btnIndex, _strTemp);
		 
		 _strTemp = ((EditText)findViewById(R.id.editMedicineCapsule)).getText().toString();
		 editor.putString("medicationCapsule" + btnIndex, _strTemp);
		 
		 _strTemp = ((EditText)findViewById(R.id.editMedicineWhat)).getText().toString();
		 editor.putString("medicationWhat" + btnIndex, _strTemp);
		
		editor.commit();
	}
	
	private void disableAllButton()
	{
//				((Button)this.findViewById(R.id.btnEdit1)).setClickable(false);
				((Button)this.findViewById(R.id.btnAdd1)).setBackgroundResource(R.drawable.btn_disableadd);
//				((Button)this.findViewById(R.id.btnAdd1)).setClickable(false);
				((Button)this.findViewById(R.id.btnEdit1)).setBackgroundResource(R.drawable.btn_disableedit);

//				((Button)this.findViewById(R.id.btnEdit2)).setClickable(false);
				((Button)this.findViewById(R.id.btnAdd2)).setBackgroundResource(R.drawable.btn_disableadd);
				
//				((Button)this.findViewById(R.id.btnAdd2)).setClickable(false);
				((Button)this.findViewById(R.id.btnEdit2)).setBackgroundResource(R.drawable.btn_disableedit);

//				((Button)this.findViewById(R.id.btnEdit3)).setClickable(false);
				((Button)this.findViewById(R.id.btnAdd3)).setBackgroundResource(R.drawable.btn_disableadd);
				

//				((Button)this.findViewById(R.id.btnAdd3)).setClickable(false);
				((Button)this.findViewById(R.id.btnEdit3)).setBackgroundResource(R.drawable.btn_disableedit);

//				((Button)this.findViewById(R.id.btnEdit4)).setClickable(false);
				((Button)this.findViewById(R.id.btnAdd4)).setBackgroundResource(R.drawable.btn_disableadd);
				

//				((Button)this.findViewById(R.id.btnAdd4)).setClickable(false);
				((Button)this.findViewById(R.id.btnEdit4)).setBackgroundResource(R.drawable.btn_disableedit);

//				((Button)this.findViewById(R.id.btnEdit5)).setClickable(false);
				((Button)this.findViewById(R.id.btnAdd5)).setBackgroundResource(R.drawable.btn_disableadd);
				

//				((Button)this.findViewById(R.id.btnAdd5)).setClickable(false);
				((Button)this.findViewById(R.id.btnEdit5)).setBackgroundResource(R.drawable.btn_disableedit);

//				((Button)this.findViewById(R.id.btnEdit6)).setClickable(false);
				((Button)this.findViewById(R.id.btnAdd6)).setBackgroundResource(R.drawable.btn_disableadd);
				

//				((Button)this.findViewById(R.id.btnAdd6)).setClickable(false);
				((Button)this.findViewById(R.id.btnEdit6)).setBackgroundResource(R.drawable.btn_disableedit);

//				((Button)this.findViewById(R.id.btnEdit7)).setClickable(false);
				((Button)this.findViewById(R.id.btnAdd7)).setBackgroundResource(R.drawable.btn_disableadd);


//				((Button)this.findViewById(R.id.btnAdd7)).setClickable(false);
				((Button)this.findViewById(R.id.btnEdit7)).setBackgroundResource(R.drawable.btn_disableedit);

//				((Button)this.findViewById(R.id.btnEdit8)).setClickable(false);
				((Button)this.findViewById(R.id.btnAdd8)).setBackgroundResource(R.drawable.btn_disableadd);
				

//				((Button)this.findViewById(R.id.btnAdd8)).setClickable(false);
				((Button)this.findViewById(R.id.btnEdit8)).setBackgroundResource(R.drawable.btn_disableedit);

//				((Button)this.findViewById(R.id.btnEdit9)).setClickable(false);
				((Button)this.findViewById(R.id.btnAdd9)).setBackgroundResource(R.drawable.btn_disableadd);
				

//				((Button)this.findViewById(R.id.btnAdd9)).setClickable(false);
				((Button)this.findViewById(R.id.btnEdit9)).setBackgroundResource(R.drawable.btn_disableedit);
	}
	
	private void refreshButtonStatus()
	{
		disableAllButton();
		for (int i = 1; i < 10; i++) {
			String _btnIndex = Integer.toString(i);
			
			String strTemp = Decode_string( prefs.getString("medicationName" + _btnIndex, ""));
			
			switch (i) {
			case 1:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd1)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit1)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd1)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd1)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit1)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit1)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;
				
			case 2:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd2)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit2)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd2)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd2)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit2)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit2)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;
				
			case 3:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd3)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit3)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd3)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd3)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit3)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit3)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;
				
			case 4:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd4)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit4)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd4)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd4)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit4)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit4)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;

			case 5:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd5)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit5)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd5)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd5)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit5)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit5)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;

			case 6:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd6)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit6)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd6)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd6)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit6)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit6)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;

			case 7:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd7)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit7)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd7)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd7)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit7)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit7)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;

			case 8:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd8)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit8)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd8)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd8)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit8)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit8)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;

			case 9:
				if (strTemp.equals("")) {
					((Button)this.findViewById(R.id.btnAdd9)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit9)).setClickable(false);
					((Button)this.findViewById(R.id.btnAdd9)).setBackgroundResource(R.drawable.btn_enableadd);
					
					i = 100;
				} else {
					((Button)this.findViewById(R.id.btnAdd9)).setClickable(false);
					((Button)this.findViewById(R.id.btnEdit9)).setClickable(true);
					((Button)this.findViewById(R.id.btnEdit9)).setBackgroundResource(R.drawable.btn_enableedit);
				}
				break;

			default:
				break;
			}
		}
		
	}
	
	private void resetMedicationFields()
	{
		((EditText)findViewById(R.id.editMedicineName)).setText("");
		 
		 ((EditText)findViewById(R.id.editMedicineDosage)).setText("");
		 
		 ((EditText)findViewById(R.id.editMedicineWhen)).setText("");
		 
		((EditText)findViewById(R.id.editMedicineCapsule)).setText("");
		 
		 ((EditText)findViewById(R.id.editMedicineWhat)).setText("");
	}
	
	private void setMedicationField()
	{
		String strTemp = Decode_string( prefs.getString("medicationName" + btnIndex, ""));
		((EditText)findViewById(R.id.editMedicineName)).setText(strTemp);
		 
		strTemp = Decode_string( prefs.getString("medicationDosage" + btnIndex, ""));
		 ((EditText)findViewById(R.id.editMedicineDosage)).setText(strTemp);
		 
		 strTemp = Decode_string( prefs.getString("medicationWhen" + btnIndex, ""));
		 ((EditText)findViewById(R.id.editMedicineWhen)).setText(strTemp);
		 
		 strTemp = Decode_string( prefs.getString("medicationCapsule" + btnIndex, ""));
		((EditText)findViewById(R.id.editMedicineCapsule)).setText(strTemp);
		 
		strTemp = Decode_string( prefs.getString("medicationWhat" + btnIndex, ""));
		 ((EditText)findViewById(R.id.editMedicineWhat)).setText(strTemp);
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
		
		this.btnAddEdit();
		
		// ------------ Medication Window ----------------------
		((Button) findViewById(R.id.btnMedicineSave)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				saveMedicationData();
				refreshButtonStatus();
				linearMedication.setVisibility(View.GONE);
			}
		});
		((Button) findViewById(R.id.btnMedicineHome)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				linearMedication.setVisibility(View.GONE);
			}
		});
		
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
		Intent intent = new Intent( LogActivity.this,ControlActivity.class);
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
	
	private void btnAddEdit(){
		// --------------- To add Medication data ----------------
				((Button) findViewById(R.id.btnAdd1)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "1";
						resetMedicationFields();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				((Button) findViewById(R.id.btnEdit1)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "1";
						setMedicationField();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				
				((Button) findViewById(R.id.btnAdd2)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "2";
						resetMedicationFields();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				((Button) findViewById(R.id.btnEdit2)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "2";
						setMedicationField();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				
				((Button) findViewById(R.id.btnAdd3)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "3";
						resetMedicationFields();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				((Button) findViewById(R.id.btnEdit3)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "3";
						setMedicationField();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				
				((Button) findViewById(R.id.btnAdd4)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "4";
						resetMedicationFields();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				((Button) findViewById(R.id.btnEdit4)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "4";
						setMedicationField();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				
				((Button) findViewById(R.id.btnAdd5)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "5";
						resetMedicationFields();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				((Button) findViewById(R.id.btnEdit5)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "5";
						setMedicationField();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				
				((Button) findViewById(R.id.btnAdd6)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "6";
						resetMedicationFields();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				((Button) findViewById(R.id.btnEdit6)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "6";
						setMedicationField();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				
				((Button) findViewById(R.id.btnAdd7)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "7";
						resetMedicationFields();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				((Button) findViewById(R.id.btnEdit7)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "7";
						setMedicationField();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				
				((Button) findViewById(R.id.btnAdd8)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "8";
						resetMedicationFields();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				((Button) findViewById(R.id.btnEdit8)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "8";
						setMedicationField();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				
				((Button) findViewById(R.id.btnAdd9)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "9";
						resetMedicationFields();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
				((Button) findViewById(R.id.btnEdit9)).setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						btnIndex = "9";
						setMedicationField();
						linearMedication.setVisibility(View.VISIBLE);
					}
				});
	}
	
	private void readData()
	{
		((Button) findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				dismissActivity();
			}
		});
		
		String strFullname = Decode_string( prefs.getString("fullname", ""));
		String strBirthday = Decode_string( prefs.getString("birthday", ""));
		String strUpdatedDate = Decode_string( prefs.getString("updatedDate", ""));
		
		((TextView)this.findViewById(R.id.editFullname)).setText(strFullname);
		((TextView)this.findViewById(R.id.editBirthday)).setText(strBirthday);
		((TextView)this.findViewById(R.id.editMedicineAllergies)).setText(strUpdatedDate);
		
		String[] ele = strBirthday.split("-");
		
		int nYear;
		
		if (ele.length > 1) {
			nYear = Integer.valueOf(ele[2]);
			int nMonth = Integer.valueOf(ele[0]);
			if (nMonth > 4) {
				((TextView)this.findViewById(R.id.btnMedicineAllergies)).setText(Integer.toString(2015 - nYear - 1));
			} else {
				((TextView)this.findViewById(R.id.btnMedicineAllergies)).setText(Integer.toString(2015 - nYear));
			}
		}
		
		for (int i = 1; i < 11; i++) {
			String _btnIndex = Integer.toString(i);
			
			String strTemp = Decode_string( prefs.getString("medicationName" + _btnIndex, ""));
			
			 
			String strTemp2 = Decode_string( prefs.getString("medicationDosage" + _btnIndex, ""));
			
			 
			 String strTemp3 = Decode_string( prefs.getString("medicationWhen" + _btnIndex, ""));
			 
			 
			 String strTemp4 = Decode_string( prefs.getString("medicationCapsule" + _btnIndex, ""));
			
			 
			String strTemp5 = Decode_string( prefs.getString("medicationWhat" + _btnIndex, ""));
			 
			 
			switch (i) {
			case 1:
				if (strTemp.equals("")) {
					((TextView)findViewById(R.id.editMedicationCount)).setText("0");
				
					i = 100;
				} else {
					((LinearLayout)findViewById(R.id.linearmedication1)).setVisibility(View.VISIBLE);
					((TextView)findViewById(R.id.medication1name)).setText(strTemp);
					((TextView)findViewById(R.id.medication1dosage)).setText(strTemp2);
					((TextView)findViewById(R.id.medication1when)).setText(strTemp3);
					((TextView)findViewById(R.id.medication1capsule)).setText(strTemp4);
					((TextView)findViewById(R.id.medication1what)).setText(strTemp5);
				}
				break;
				
			case 2:
				if (strTemp.equals("")) {
					((TextView)findViewById(R.id.editMedicationCount)).setText("1");
					
					i = 100;
				} else {
					((LinearLayout)findViewById(R.id.linearmedication2)).setVisibility(View.VISIBLE);
					((TextView)findViewById(R.id.medication2name)).setText(strTemp);
					((TextView)findViewById(R.id.medication2dosage)).setText(strTemp2);
					((TextView)findViewById(R.id.medication2when)).setText(strTemp3);
					((TextView)findViewById(R.id.medication2capsule)).setText(strTemp4);
					((TextView)findViewById(R.id.medication2what)).setText(strTemp5);
				}
				break;
				
			case 3:
				if (strTemp.equals("")) {
					((TextView)findViewById(R.id.editMedicationCount)).setText("2");
					
					i = 100;
				} else {
					((LinearLayout)findViewById(R.id.linearmedication3)).setVisibility(View.VISIBLE);
					((TextView)findViewById(R.id.medication3name)).setText(strTemp);
					((TextView)findViewById(R.id.medication3dosage)).setText(strTemp2);
					((TextView)findViewById(R.id.medication3when)).setText(strTemp3);
					((TextView)findViewById(R.id.medication3capsule)).setText(strTemp4);
					((TextView)findViewById(R.id.medication3what)).setText(strTemp5);
				}
				break;
				
			case 4:
				if (strTemp.equals("")) {
					((TextView)findViewById(R.id.editMedicationCount)).setText("3");
					
					i = 100;
				} else {
					((LinearLayout)findViewById(R.id.linearmedication4)).setVisibility(View.VISIBLE);
					((TextView)findViewById(R.id.medication4name)).setText(strTemp);
					((TextView)findViewById(R.id.medication4dosage)).setText(strTemp2);
					((TextView)findViewById(R.id.medication4when)).setText(strTemp3);
					((TextView)findViewById(R.id.medication4capsule)).setText(strTemp4);
					((TextView)findViewById(R.id.medication4what)).setText(strTemp5);
				}
				break;

			case 5:
				if (strTemp.equals("")) {
					((TextView)findViewById(R.id.editMedicationCount)).setText("4");
					
					i = 100;
				} else {
					((LinearLayout)findViewById(R.id.linearmedication5)).setVisibility(View.VISIBLE);
					((TextView)findViewById(R.id.medication5name)).setText(strTemp);
					((TextView)findViewById(R.id.medication5dosage)).setText(strTemp2);
					((TextView)findViewById(R.id.medication5when)).setText(strTemp3);
					((TextView)findViewById(R.id.medication5capsule)).setText(strTemp4);
					((TextView)findViewById(R.id.medication5what)).setText(strTemp5);
				}
				break;

			case 6:
				if (strTemp.equals("")) {
					((TextView)findViewById(R.id.editMedicationCount)).setText("5");
					
					i = 100;
				} else {
					((LinearLayout)findViewById(R.id.linearmedication6)).setVisibility(View.VISIBLE);
					((TextView)findViewById(R.id.medication6name)).setText(strTemp);
					((TextView)findViewById(R.id.medication6dosage)).setText(strTemp2);
					((TextView)findViewById(R.id.medication6when)).setText(strTemp3);
					((TextView)findViewById(R.id.medication6capsule)).setText(strTemp4);
					((TextView)findViewById(R.id.medication6what)).setText(strTemp5);
				}
				break;

			case 7:
				if (strTemp.equals("")) {
					((TextView)findViewById(R.id.editMedicationCount)).setText("6");
					
					i = 100;
				} else {
					((LinearLayout)findViewById(R.id.linearmedication7)).setVisibility(View.VISIBLE);
					((TextView)findViewById(R.id.medication7name)).setText(strTemp);
					((TextView)findViewById(R.id.medication7dosage)).setText(strTemp2);
					((TextView)findViewById(R.id.medication7when)).setText(strTemp3);
					((TextView)findViewById(R.id.medication7capsule)).setText(strTemp4);
					((TextView)findViewById(R.id.medication7what)).setText(strTemp5);
				}
				break;

			case 8:
				if (strTemp.equals("")) {
					((TextView)findViewById(R.id.editMedicationCount)).setText("7");
					
					i = 100;
				} else {
					((LinearLayout)findViewById(R.id.linearmedication8)).setVisibility(View.VISIBLE);
					((TextView)findViewById(R.id.medication8name)).setText(strTemp);
					((TextView)findViewById(R.id.medication8dosage)).setText(strTemp2);
					((TextView)findViewById(R.id.medication8when)).setText(strTemp3);
					((TextView)findViewById(R.id.medication8capsule)).setText(strTemp4);
					((TextView)findViewById(R.id.medication8what)).setText(strTemp5);
				}
				break;

			case 9:
				if (strTemp.equals("")) {
					((TextView)findViewById(R.id.editMedicationCount)).setText("8");
					
					i = 100;
				} else {
					((LinearLayout)findViewById(R.id.linearmedication9)).setVisibility(View.VISIBLE);
					((TextView)findViewById(R.id.medication9name)).setText(strTemp);
					((TextView)findViewById(R.id.medication9dosage)).setText(strTemp2);
					((TextView)findViewById(R.id.medication9when)).setText(strTemp3);
					((TextView)findViewById(R.id.medication9capsule)).setText(strTemp4);
					((TextView)findViewById(R.id.medication9what)).setText(strTemp5);
				}
				break;

			default:
				((TextView)findViewById(R.id.editMedicationCount)).setText("9");
				break;
			}
		}
	}
	

}
