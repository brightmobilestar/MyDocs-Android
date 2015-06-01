package com.example.aaa;

import jane.data.UIUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private String prefName = "blogInfo";
	private String strMail;
	private EditText phoneText;
	EditText usernameText,passwordText,confirmPasswordtext,emailText;
	SharedPreferences prefs;
	SharedPreferences.Editor editor;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		prefs = getSharedPreferences(prefName, MODE_PRIVATE);
		editor = prefs.edit();
		//ViewGroup vg = (ViewGroup)findViewById(R.id.reg_ViewGroup); 
		//UIUtils.setFontForAll(vg);
		
		Builder dlg = new AlertDialog.Builder(this);
//		dlg.setTitle("Warning");
		
		LayoutInflater inflater = (LayoutInflater) getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);     
		final View view = inflater.inflate(R.layout.warning,null);
        dlg.setView(view);
        
        dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
        
        dlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		phoneText = (EditText) findViewById(R.id.editPhone);
		emailText = (EditText) findViewById(R.id.editEmail);
		usernameText=(EditText)findViewById(R.id.password);
		passwordText = (EditText)findViewById(R.id.editPSW);
		confirmPasswordtext=(EditText)findViewById(R.id.confirmPwd);
		
		phoneText.setText(prefs.getString("PHONE", ""));
		emailText.setText(prefs.getString("EMAIL", ""));
		passwordText.setText(prefs.getString("PSW", ""));
		confirmPasswordtext.setText(prefs.getString("PSW", ""));
		usernameText.setText(prefs.getString("ID", ""));
		
		strMail = usernameText.getText().toString();
		
		if (strMail.equals("")) {
			dlg.show();
		}
		
		setClickListener();
	}
	
	private void AlertView()
	{
		Builder dlg = new AlertDialog.Builder(RegisterActivity.this);
		dlg.setMessage("Wrong user name or password or Phonenumber");
		
		dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				((EditText)findViewById(R.id.password)).setText("");
				((EditText)findViewById(R.id.editPSW)).setText("");
				((EditText)findViewById(R.id.confirmPwd)).setText("");
			}
		});
		
		dlg.show();
	}
	
	private void setClickListener(){
		((Button) findViewById(R.id.btnRegister)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				
				String strID = usernameText.getText().toString();
				String strPSW = passwordText.getText().toString();
				String strCFM = confirmPasswordtext.getText().toString();
				String phoneNumber = phoneText.getText().toString();
				String email = emailText.getText().toString();
				if(strID == null || strID.equals(""))
				{
					Builder dlg = new AlertDialog.Builder(RegisterActivity.this);
					dlg.setMessage("Please enter user name");
					
					dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					
					dlg.show();
					return;
				}
				
				if(strPSW == null || strPSW.equals(""))
				{
					Builder dlg = new AlertDialog.Builder(RegisterActivity.this);
					dlg.setMessage("Please enter password");
					
					dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							((EditText)findViewById(R.id.editPSW)).setText("");
							((EditText)findViewById(R.id.confirmPwd)).setText("");
						}
					});
					
					dlg.show();
					return;
				}
				
				if(phoneNumber == null || phoneNumber.equals(""))
				{
					Builder dlg = new AlertDialog.Builder(RegisterActivity.this);
					dlg.setMessage("Please enter Phonenumber");
					
					dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					
					dlg.show();
					return;
				}
				
				if(strCFM == null || strCFM.equals("") || !strPSW.equals(strCFM))
				{
					Builder dlg = new AlertDialog.Builder(RegisterActivity.this);
					dlg.setMessage("Confirm password doesn't match with password.");
					
					dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							((EditText)findViewById(R.id.editPSW)).setText("");
							((EditText)findViewById(R.id.confirmPwd)).setText("");
						}
					});
					
					dlg.show();
					return;
				}
				
				editor.putString("ID", strID);
				editor.putString("PSW", strPSW);
				editor.putString("PHONE", phoneNumber);
				editor.putString("EMAIL", email);
				editor.commit();
				
				Toast.makeText(getBaseContext(), "Success for Registeration", Toast.LENGTH_LONG).show();
				
				Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        		startActivity(intent);
        		finish();
			}
		 });
		
		 ((Button) findViewById(R.id.btnCancel1)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				
				if (strMail.equals("")) {
					finish();
				} else {
					Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
	        		startActivity(intent);
	        		finish();
				}	
			}
		 });
		 
		 ((Button) findViewById(R.id.btn_backupemail)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				send_Email();
			}
		 });
		 
	}
	
	private String Decode_string(String str)
	{
		if(str.equals("NULL")) return "";
		return str;
	}
		
	public void send_Email() {
		
		String strBody = "";
		
		String strEmail = Decode_string( prefs.getString("EMAIL", "") );
		
		// ------- Medical History ----------------------------
		
		prefName = "HistoryInfo";
		prefs = getSharedPreferences(prefName, MODE_PRIVATE);
		
		strBody = strBody + "<b>Medical History</b><br>" + "<br>";
		
		String strFullname = Decode_string( prefs.getString("fullname", ""));
		strBody = strBody + "FullName :"+ strFullname+ "<br>";
		
		String strBirthday = Decode_string( prefs.getString("birthday", ""));
		strBody = strBody + "Birthday :"+ strBirthday+ "<br>";

		String strUpdatedDate = Decode_string( prefs.getString("updatedDate", ""));
		
		String strMedicalAllergies = Decode_string(prefs.getString("medicalAllergies", "")) ;
		String strMedicalAllergiesVal = Decode_string(prefs.getString("medicalAllergiesVal", ""));
		
		if (!strMedicalAllergies.equals("Medicine Allergies")) {
			strBody = strBody + "Medical Allergies Select :"+ strMedicalAllergies + "<br>";
			strBody = strBody + "Medical Allergies :"+ strMedicalAllergiesVal + "<br>";
		}
		
		String strOtherAllergies = Decode_string(prefs.getString("otherAllergies", "")) ;
		String strOtherAllergiesVal = Decode_string(prefs.getString("otherAllergiesVal", ""));
		
		if (!strOtherAllergies.equals("Other Allergies")) {
			strBody = strBody + "Other Allergies Select :"+ strOtherAllergies + "<br>";
			strBody = strBody + "Other Allergies :"+ strOtherAllergiesVal + "<br>";
		}
		
		String strMedicalCondition = Decode_string(prefs.getString("medicalCondition", "")) ;
		String strMedicalConditionVal = Decode_string(prefs.getString("medicalConditionVal", ""));
		
		if (!strMedicalCondition.equals("Medical Condition")) {
			strBody = strBody + "Medicine Condition Select :"+ strMedicalCondition + "<br>";
			strBody = strBody + "Medicine Condition :"+ strMedicalConditionVal + "<br>";
		}
		
		strBody = strBody + "Last Updated :"+ strUpdatedDate + "<br>";
		
		String strContact1Fullname = Decode_string(prefs.getString("contact1Fullname", ""));
		String strContact1Relationship = Decode_string(prefs.getString("contact1Relationship", ""));
		String strContact1Phonenumber = Decode_string(prefs.getString("contact1Phonenumber", ""));
		
		strBody = strBody + "Contact1 :"+ "<br>";
		strBody = strBody + "name(relationship) :"+ strContact1Fullname + "(" + strContact1Relationship + ")" + "<br>";
		strBody = strBody + " phonenumber        :" + strContact1Phonenumber + "<br>";
		
		String strContact2Fullname = Decode_string(prefs.getString("contact2Fullname", ""));
		String strContact2Relationship = Decode_string(prefs.getString("contact2Relationship", ""));
		String strContact2Phonenumber = Decode_string(prefs.getString("contact2Phonenumber", ""));
		
		strBody = strBody + "Contact2 :"+ "<br>";
		strBody = strBody + "name(relationship) :"+ strContact2Fullname + "(" + strContact2Relationship + ")" + "<br>";
		strBody = strBody + "  phonenumber   :" + strContact2Phonenumber + "<br>";	
			
			String[] ele = strBirthday.split("-");
			
			int nYear;
			
			if (ele.length > 1) {
				nYear = Integer.valueOf(ele[2]);
				//((TextView)this.findViewById(R.id.btnAge)).setText(Integer.toString(nYear - 2015));
			}
			
		
		// ------- Medical Log ----------------------------
			
		prefName = "LogInfo";
		prefs = getSharedPreferences(prefName, MODE_PRIVATE);
		
		strBody = strBody + "<br><br>Medical Log</b>" + "<br><br>";
		
		strFullname = Decode_string( prefs.getString("fullname", ""));
		strBody = strBody + "FullName :"+ strFullname+ "<br>";
		
		strBirthday = Decode_string( prefs.getString("birthday", ""));
		strBody = strBody + "Birthday :"+ strBirthday+ "<br>";
		
		strUpdatedDate = Decode_string( prefs.getString("updatedDate", ""));
		
		ele = strBirthday.split("-");
		
		if (ele.length > 1) {
			nYear = Integer.valueOf(ele[2]);
			//((TextView)this.findViewById(R.id.btnMedicineAllergies)).setText(Integer.toString(nYear - 2015));
		}
		
		strBody = strBody + "<b>Current Medications</b> :"+ "<br>";
		for (int i = 1; i < 11; i++) {
			String _btnIndex = Integer.toString(i);
			
			String strTemp = Decode_string( prefs.getString("medicationName" + _btnIndex, ""));
			
			if (strTemp.equals("")) {
				break;
			}
			
			strBody = strBody + "<b> " + _btnIndex + " </b><br>";
			strBody = strBody + "Name :" + strTemp+ "<br>";
			 
			String strTemp2 = Decode_string( prefs.getString("medicationDosage" + _btnIndex, ""));
			strBody = strBody + "Dosage :" + strTemp2+ "<br>";
			 
			 String strTemp3 = Decode_string( prefs.getString("medicationWhen" + _btnIndex, ""));
			 strBody = strBody + "When :" + strTemp3+ "<br>";
			 
			 String strTemp4 = Decode_string( prefs.getString("medicationCapsule" + _btnIndex, ""));
			 strBody = strBody + "Type :" + strTemp4+ "<br>";
			 
			String strTemp5 = Decode_string( prefs.getString("medicationWhat" + _btnIndex, ""));
			strBody = strBody + "Token :" + strTemp5+ "<br>";

		}
		
		/// --------------------------------
		
		Intent intentEmail = new Intent(Intent.ACTION_SEND);
		intentEmail.setType("text/html");
		intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{strEmail});
		intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Emergency Notify!"); 
		intentEmail.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(strBody));
		intentEmail.setType("text/html");
		
//		intentEmail.setType("message/rfc822");
		intentEmail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intentEmail);
		
		//this.startActivity(Intent.createChooser(intentEmail, "Choose an email provider :") );
		
//		final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
//		emailIntent.setType("text/html");
//		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "s");
//		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,  Html.fromHtml("Check out this great application: <a href=\"https://www.google.com.sg/maps/@1.5256499,103.810312,11z?hl=zh-CN"+
//                "\">Link</a>"));
//		_context.startActivity(Intent.createChooser(emailIntent, "Email:"));
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
		super.onBackPressed();
	}
	
	

}
