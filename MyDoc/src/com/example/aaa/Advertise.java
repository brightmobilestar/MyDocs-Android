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

public class Advertise extends Activity {
private String prefName = "blogInfo";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ads);
		
		UIUtils.font = Typeface.createFromAsset(this.getAssets(), "font/TimesNewRoman.ttf"); 
		UIUtils.fontBold = Typeface.createFromAsset(this.getAssets(), "font/TimesNewRomanBold.ttf");
		
		WaitThread waitThread = new WaitThread();
		waitThread.execute("");
	}
	
	class WaitThread extends AsyncTask<String, Void, JSONObject> {
    	@Override
    	protected JSONObject doInBackground(String... urls) {
    		try {
				Thread.sleep(5000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	    return null;
    	}

    	@Override
    	protected void onPostExecute(JSONObject result) {
    		
    		SharedPreferences prefs = getSharedPreferences(prefName, MODE_PRIVATE);    
    		String strID = prefs.getString("ID", "");
    		
    		if(strID.equals("")) 
    		{
    			Intent intent = new Intent(Advertise.this, RegisterActivity.class);
        		startActivity(intent);
        		finish();
    		}
    		else
    		{
    			Intent intent = new Intent(Advertise.this, MainActivity.class);
        		startActivity(intent);
        		finish();
    		}
    		
    	}
     }

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		finish();
		super.onBackPressed();
	}
}
