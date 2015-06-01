package com.example.aaa;

import java.util.Locale;

import jane.data.UIUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnInitListener {
	
	private TextToSpeech mTts;
	
	private String prefName = "blogInfo";
	private String prefName1 = "HistoryLogRegisterInfo";
	
	SharedPreferences prefs;    
	String strID_Real;
	String strPSW_Real;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		prefs = getSharedPreferences(prefName, MODE_PRIVATE);    
		strID_Real = prefs.getString("ID", "");
		strPSW_Real = prefs.getString("PSW", "");
		
		((EditText)findViewById(R.id.password)).setText(strID_Real);
		
		ViewGroup vg = (ViewGroup)findViewById(R.id.lg_ViewGroup);
		
		UIUtils.setFontForAll(vg);
		setClickListener();
		
		if (ControlActivity.nCategory > 9) {
			
			prefs = getSharedPreferences(prefName1, MODE_PRIVATE);    
			strPSW_Real = prefs.getString("PSW", ""); //
			
			((TextView)findViewById(R.id.TextView01)).setVisibility(View.INVISIBLE);
			((EditText)findViewById(R.id.password)).setVisibility(View.GONE);
		}
		
		//mTts = new TextToSpeech(LoginActivity.this, LoginActivity.this);
		
		//String strText = strID_Real + " with this statdard greeting";
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
//		String strText = strID_Real + " with this statdard greeting";
//		
//		mTts.setLanguage(Locale.US);
//		
//		mTts.speak(strText, TextToSpeech.QUEUE_FLUSH, null);	
	}

	private void AlertView(String strMessage)
	{
		Builder dlg = new AlertDialog.Builder(LoginActivity.this);
		dlg.setMessage(strMessage);
		
		dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				((EditText)findViewById(R.id.password)).setText("");
				((EditText)findViewById(R.id.editPSW)).setText("");
			}
		});
		dlg.show();
	}
	
	private void setClickListener(){
		((Button) findViewById(R.id.btnLogin)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				
				if (ControlActivity.nCategory > 9) {
					String strPSW = ((EditText)findViewById(R.id.editPSW)).getText().toString();
					if(!strPSW.equals(strPSW_Real) || strPSW_Real.equals(""))
					{
						AlertView("Wrong password, please input again!");
						return;
					}
					
				} else {
					String strID = ((EditText)findViewById(R.id.password)).getText().toString();
					String strPSW = ((EditText)findViewById(R.id.editPSW)).getText().toString();

					if(strID == null || strID.equals("") || strPSW == null || strPSW.equals(""))
					{
						AlertView("Please input the ID and password!");
						return;
					}
					
					if(!strPSW.equals(strPSW_Real) || !strID.equals(strID_Real))
					{
						AlertView("Wrong ID or password, please input again!");
						return;
					}
				}
				
				Toast.makeText(getBaseContext(), "Success for Login", Toast.LENGTH_LONG).show();

				Intent intent = new Intent(LoginActivity.this, ControlActivity.class);
				startActivity(intent);
				finish();
			}
		});
		((Button) findViewById(R.id.btnCancel1)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				
//				String strText = strID_Real + " with this statdard greeting";
//				mTts.setLanguage(Locale.US);
//				mTts.speak(strText, TextToSpeech.QUEUE_FLUSH, null);	
				
				Intent intent = new Intent( LoginActivity.this,MainActivity.class);
        		startActivity(intent);
        		finish();
			}
		});
	}
	
	@Override
    public void onDestroy() {
//        if (mTts != null) {
//            mTts.stop();
//            mTts.shutdown();
//        }
        super.onDestroy();
    }
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent i = new Intent(LoginActivity.this, MainActivity.class);
		startActivity(i);
		finish();
		super.onBackPressed();
	}
	
	private void speak(String textToSpeak)
	{
		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR
//        if (status == TextToSpeech.SUCCESS) {
//            // Set preferred language to US english.
//            // Note that a language may not be available, and the result will indicate this.
//            int result = mTts.setLanguage(Locale.US);
//            
//            if (result == TextToSpeech.LANG_MISSING_DATA ||
//                result == TextToSpeech.LANG_NOT_SUPPORTED) {
//               // Lanuage data is missing or the language is not supported.
//                Log.e("404","Language is not available.");
//            }
//        } else {
//            // Initialization failed.
//            Log.e("404", "Could not initialize TextToSpeech.");
//            // May be its not installed so we prompt it to be installed
//            Intent installIntent = new Intent();
//            installIntent.setAction(
//                TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
//            startActivity(installIntent);
//        }
	}

	

}
