package com.example.aaa;

import java.util.Locale;

import jane.data.AppointmentModel;
import jane.data.DocModel;
import jane.data.InsuranceModel;
import jane.data.PharmacyModel;
import jane.data.SQLHelper;
import jane.data.SpecialistModel;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnInitListener {
    /** Called when the activity is first created. */
	static public AppointmentModel aModel;
	static public InsuranceModel insModel;
	static public SpecialistModel specModel;
	static public PharmacyModel phaModel;
	static public DocModel docModel;
	static public SQLHelper sqlHelper;
	
	ImageButton editProfile;
	
	private TextToSpeech mTts;
	
	private String prefName = "blogInfo";
	
	SharedPreferences prefs;    
	String strID_Real;
	String strPSW_Real;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        //---------------------------------------------- Voice - rec ------------------
        prefs = getSharedPreferences(prefName, MODE_PRIVATE);    
		strID_Real = prefs.getString("ID", "");
		strPSW_Real = prefs.getString("PSW", "");
		
//		mTts = new TextToSpeech(this, this);
		//----------------------------------------------
		
        sqlHelper = new SQLHelper(this);
        
		aModel = new AppointmentModel();
		insModel = new InsuranceModel();
		specModel = new SpecialistModel();
		phaModel = new PharmacyModel();
		docModel = new DocModel();
		
		aModel.init();
		insModel.init();
		specModel.init();
		phaModel.init();
		docModel.init();
        
        setClickListener(); 
        editProfile = (ImageButton) findViewById(R.id.editProfile);
        editProfile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
				startActivity(intent);
				finish();
				
				
			}
		});
    }
    
    void speechToText(String str)
    {
//    	String strText = strID_Real + " with this statdard greeting";
//		mTts.setLanguage(Locale.US);
//		mTts.speak(strText, TextToSpeech.QUEUE_FLUSH, null);	
    }
    
    private void setClickListener(){
    	
    	
		((Button) findViewById(R.id.btnInsurance)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				ControlActivity.nCategory = 0;
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        		startActivity(intent);
        		speechToText("");
        		finish();
//        		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			}
		 });
		
		((Button) findViewById(R.id.btnPrimary)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				ControlActivity.nCategory = 1;
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        		startActivity(intent);
        		speechToText("");
        		finish();
			}
		 });
		
		((Button) findViewById(R.id.btnSpecialist)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				ControlActivity.nCategory = 2;
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        		startActivity(intent);
        		speechToText("");
        		finish();
			}
		 });
		
		((Button) findViewById(R.id.btnDentist)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				ControlActivity.nCategory = 3;
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        		startActivity(intent);
        		speechToText("");
        		finish();
			}
		 });
		
		((Button) findViewById(R.id.btnOptometrist)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				ControlActivity.nCategory = 4;
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        		startActivity(intent);
        		speechToText("");
        		finish();
			}
		 });
		
		((Button) findViewById(R.id.btnPharmacy)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				ControlActivity.nCategory = 5;
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        		startActivity(intent);
        		speechToText("");
        		finish();
			}
		 });
		
		((Button) findViewById(R.id.btnHospital)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				ControlActivity.nCategory = 6;
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        		startActivity(intent);
        		speechToText("");
        		finish();
			}
		 });
		
		((Button) findViewById(R.id.btnAppointments)).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				ControlActivity.nCategory = 7;
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        		startActivity(intent);
        		speechToText("");
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

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		
	}
    
}