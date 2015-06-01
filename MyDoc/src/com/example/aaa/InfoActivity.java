package com.example.aaa;

import jane.data.UIUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends Activity {
	LinearLayout linearActionsheet;
	
	SharedPreferences prefs;
	SharedPreferences.Editor editor;
	
	private String prefName = "blogInfo";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.info);
		
		prefs = getSharedPreferences(prefName, MODE_PRIVATE);
		editor = prefs.edit();
		
		linearActionsheet = (LinearLayout)this.findViewById(R.id.blinearAddtionalDisable);
		
		this.setClickListener();
	}
	
	private void setClickListener(){
		((Button) findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				dismissActivity();
			}
		});
		
		((Button) findViewById(R.id.btnSignUp)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				linearActionsheet.setVisibility(View.VISIBLE);
			}
		});

		((Button) findViewById(R.id.actsheet_item_11)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				AlertView("Pay Now");
			}
		});

		((Button) findViewById(R.id.actsheet_item_12)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				linearActionsheet.setVisibility(View.GONE);
				
				editor.putString("status", "free");
				editor.commit();
				
				Intent intent = new Intent( InfoActivity.this,MainActivity.class);
        		startActivity(intent);
        		finish();
			}
		});

	}
	
	private void AlertView(String strMessage)
	{
		Builder dlg = new AlertDialog.Builder(InfoActivity.this);
		dlg.setTitle(strMessage);
		
		dlg.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dismissActivity();
			}
		});
		dlg.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				
				editor.putString("status", "paid");
				editor.commit();
				
				goActivityToRegister();
			}
		});
		dlg.show();
	}
	
	private void dismissActivity()
	{
		Intent intent = new Intent( InfoActivity.this,MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	private void goActivityToRegister()
	{
		linearActionsheet.setVisibility(View.GONE);
		Intent intent = new Intent( InfoActivity.this,RegisterHistoryLogActivity.class);
		startActivity(intent);
	}
}
