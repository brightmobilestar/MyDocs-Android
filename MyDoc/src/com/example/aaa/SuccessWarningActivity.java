package com.example.aaa;


import org.json.JSONObject;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;

public class SuccessWarningActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.success_warning);
		
		 NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
	      
	      Notification notifyObj=new Notification(R.drawable.icon,  "Notification message!",     System.currentTimeMillis());
	      
	      PendingIntent pi=PendingIntent.getActivity(this, 0, new Intent(), 0);

	      notifyObj.setLatestEventInfo(this, null, null, pi);
	     
	      notifyObj.defaults |= Notification.DEFAULT_VIBRATE;
	      notifyObj.defaults |= Notification.DEFAULT_SOUND;
	      notifyObj.flags|=Notification.FLAG_AUTO_CANCEL;    
	      
//	      mNotificationManager.notify(5000, notifyObj);
		
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
    		Intent i = new Intent(SuccessWarningActivity.this, ControlActivity.class);
			startActivity(i);
    		finish();
    	}
     }
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent i = new Intent(SuccessWarningActivity.this, ControlActivity.class);
		startActivity(i);
		finish();
		super.onBackPressed();
	}
}
