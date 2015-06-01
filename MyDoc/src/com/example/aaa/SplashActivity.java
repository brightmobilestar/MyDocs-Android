package com.example.aaa;

import jane.data.UIUtils;

import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;

public class SplashActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		
		UIUtils.font = Typeface.createFromAsset(this.getAssets(), "font/TimesNewRoman.ttf"); 
		UIUtils.fontBold = Typeface.createFromAsset(this.getAssets(), "font/TimesNewRomanBold.ttf");
		
		WaitThread waitThread = new WaitThread();
		waitThread.execute("");
	}
	
	class WaitThread extends AsyncTask<String, Void, JSONObject> {
    	@Override
    	protected JSONObject doInBackground(String... urls) {
    		try {
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	    return null;
    	}

    	@Override
    	protected void onPostExecute(JSONObject result) {
    		
    		Intent intent = new Intent(SplashActivity.this, Advertise.class);
    		startActivity(intent);
    		finish();    		
    	}
     }

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		finish();
		super.onBackPressed();
	}
	
	

}
