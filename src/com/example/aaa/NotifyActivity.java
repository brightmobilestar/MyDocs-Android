

package com.example.aaa;

import jane.data.UIUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class NotifyActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    setContentView(R.layout.reminder);
    
    ViewGroup vg = (ViewGroup)findViewById(R.id.ntfy_ViewGroup);
    
    UIUtils.setFontForAll(vg);
    
    String defaultTitle="";
    String defaultDescription ="";
    Bundle extras = getIntent().getExtras();
    
    if (extras!=null)
    {
    
    	defaultTitle = extras.getString("ALERT_TITLE");
    	defaultDescription = extras.getString("ALERT_DESCRIPTION");
    }
    
    ((TextView)findViewById(R.id.txtTitle)).setText(defaultTitle);
    ((TextView)findViewById(R.id.txtDescription)).setText(defaultDescription);
  }

  @Override
  public void onBackPressed() {
	// TODO Auto-generated method stub
	Intent i = new Intent(NotifyActivity.this, MainActivity.class);
	startActivity(i);
	finish();
	super.onBackPressed();
  }
}
