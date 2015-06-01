package com.example.aaa;

import jane.data.UIUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private String prefName = "blogInfo";
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
		
		dlg.show();
		phoneText = (EditText) findViewById(R.id.editPhone);
		emailText = (EditText) findViewById(R.id.editEmail);
		usernameText=(EditText)findViewById(R.id.editID);
		passwordText = (EditText)findViewById(R.id.editPSW);
		confirmPasswordtext=(EditText)findViewById(R.id.editCFM);
		
		
		phoneText.setText(prefs.getString("PHONE", ""));
		emailText.setText(prefs.getString("EMAIL", ""));
		passwordText.setText(prefs.getString("PSW", ""));
		confirmPasswordtext.setText(prefs.getString("PSW", ""));
		usernameText.setText(prefs.getString("ID", ""));
		
		setClickListener();
	}
	
	private void AlertView()
	{
		Builder dlg = new AlertDialog.Builder(RegisterActivity.this);
		dlg.setMessage("Wrong ID or password");
		
		dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				((EditText)findViewById(R.id.editID)).setText("");
				((EditText)findViewById(R.id.editPSW)).setText("");
				((EditText)findViewById(R.id.editCFM)).setText("");
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
				if(strID == null || strID.equals("") || strPSW == null || strPSW.equals("") || phoneNumber ==null || phoneNumber.equals(""))
				{
					AlertView();
					return;
				}
				
				if(strCFM == null || strCFM.equals("") || !strPSW.equals(strCFM))
				{
					AlertView();
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
		 ((Button) findViewById(R.id.btnCancel)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				finish();
			}
		 });
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
		super.onBackPressed();
	}
	
	

}
