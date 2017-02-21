package com.delivery.foodsby;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class ResetPasswordActivity extends Activity{
	
	private 	EditText		editTextEmailAddress;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
 
    	super.onCreate(savedInstanceState);
        
	    requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_reset_password);
        
		editTextEmailAddress = (EditText) findViewById(R.id.edittext_email);
    }

    public void onClickSendPasswordLink (View v) {
		
	}
    
    public void onClickBack (View v) {
		
		onBackPressed();
	}

}
