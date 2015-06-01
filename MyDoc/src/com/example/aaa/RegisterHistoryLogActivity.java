package com.example.aaa;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterHistoryLogActivity extends Activity {

	private String prefName = "HistoryLogRegisterInfo";
	SharedPreferences prefs;
	SharedPreferences.Editor editor;
	EditText passwordText, confirmPasswordtext;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register_historylog);
		
		//prefs = getPreferences(MODE_PRIVATE);
		prefs = getSharedPreferences(prefName, MODE_PRIVATE);
		editor = prefs.edit();
		
		passwordText = (EditText)findViewById(R.id.password);
		confirmPasswordtext=(EditText)findViewById(R.id.confirmPwd);
		
		passwordText.setText(prefs.getString("PSW", ""));
		confirmPasswordtext.setText(prefs.getString("PSW", ""));
		
		this.setClickListener();
	}
	
	private void setClickListener(){
		((Button) findViewById(R.id.btnRegister)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				String password = passwordText.getText().toString();
				String confirmPwd = confirmPasswordtext.getText().toString();
				if (password.equals("")) {
					AlertView("Please input the password");
					return;
				} else if (!password.equals(confirmPwd)) {
					AlertView("Please confirm the repeat password");
					return;
				}
				
				editor.putString("PSW", password);
				editor.commit();
				
				dismissActivity();
			}
		});
		
		((Button) findViewById(R.id.btnCancel1)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				dismissActivity();
			}
		});
		
		((Button) findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				finish();
			}
		});

	}
	
	private void AlertView(String strMessage)
	{
		Builder dlg = new AlertDialog.Builder(RegisterHistoryLogActivity.this);
		dlg.setMessage(strMessage);
		
		dlg.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				//dismissActivity();
			}
		});
		dlg.show();
	}
	
	private void dismissActivity()
	{
		Intent intent = new Intent( RegisterHistoryLogActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
