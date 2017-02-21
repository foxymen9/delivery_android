package com.delivery.foodsby;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;

public class TermsConditionsActivity extends Activity{
	
	private 	WebView		webViewTermsConditions;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
 
    	super.onCreate(savedInstanceState);
        
	    requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_terms_conditions);
        
        webViewTermsConditions = (WebView) findViewById(R.id.webview_terms);
        
        webViewTermsConditions.loadUrl("file:///android_asset/terms.html");
    }
    
    public void onClickBack (View v) {
		
		onBackPressed();
	}

}
