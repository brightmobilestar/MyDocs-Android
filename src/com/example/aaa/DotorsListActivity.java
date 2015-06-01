package com.example.aaa;

import jane.data.DocRecord;
import jane.data.UIUtils;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DotorsListActivity extends Activity {
	
	LinearLayout apptListLayout;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.table_list);
		
		ViewGroup vg = (ViewGroup)findViewById(R.id.tbl_ViewGroup);
		
		UIUtils.setFontForAll(vg);
		
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		
		 String strToday = month + "-" + day + "-" + year;
		 
		 ((TextView)findViewById(R.id.txtDate_title)).setText(strToday);
		 
		 ((TextView)findViewById(R.id.txtTitle)).setText("Doctors List");
		
		apptListLayout = (LinearLayout) findViewById(R.id.layout_appointments);
//		((Button)findViewById(R.id.btn_Calendar)).setVisibility(View.INVISIBLE);
		
		((Button)findViewById(R.id.btnBack)).setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent data = new Intent();

				data.setData(Uri.parse("-1"));
				setResult(RESULT_OK, data);

				finish();
			}
		});
	}
	
	private String Decode_string(String str)
	{
		if(str.equals("NULL")) return "";
		return str;
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		load_Doctor_list();
	}
	
	private void load_Doctor_list()
	{
		apptListLayout.removeAllViews();

		if(MainActivity.docModel.list_appts.size() == 0)
		{
			Builder dlg = new AlertDialog.Builder(DotorsListActivity.this);
			dlg.setMessage("There is no data");

			dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent data = new Intent();

					data.setData(Uri.parse("-1"));
					setResult(RESULT_OK, data);
					finish();
				}
			});

			dlg.show();
			
			return;
		}

		for(int i = 0; i < MainActivity.docModel.list_appts.size(); i ++)
		{
			appendData_Doctors(i);
		}
	}
	
	private void appendData_Doctors(int idx)
	{
		final LinearLayout newCell = (LinearLayout)(View.inflate(this, R.layout.table_cell_total, null));

		DocRecord record = MainActivity.docModel.list_appts.get(idx);
		
		UIUtils.setFontForAll((ViewGroup)newCell);

		((TextView)(newCell.findViewById(R.id.txtDoctor))).setText(Decode_string(record.doctor));
		((TextView)(newCell.findViewById(R.id.txtAddress))).setText(Decode_string(record.address));
		((TextView)(newCell.findViewById(R.id.txtPhone))).setText(Decode_string(record.phone));

		newCell.setTag(idx);

		apptListLayout.addView(newCell);

		newCell.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				
				int nIdx = (Integer)arg0.getTag();
				Intent data = new Intent();
				
				data.setData(Uri.parse(String.valueOf(nIdx)));
				setResult(RESULT_OK, data);
				
				finish();
			}
		});

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent data = new Intent();

		data.setData(Uri.parse("-1"));
		setResult(RESULT_OK, data);

		finish();
		super.onBackPressed();
	}
	
	

}
