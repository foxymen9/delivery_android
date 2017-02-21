package com.delivery.foodsby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SignupActivity extends Activity{
	
	private 	EditText		editTextEmailAddress;
	private 	EditText		editTextPassword;
	private 	EditText		editTextConfirmPassword;
	private 	CheckBox		checkboxTermsCondition;
	private 	Button			buttonTermsCondition;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
 
    	super.onCreate(savedInstanceState);
        
	    requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_signup);
        
		editTextEmailAddress = (EditText) findViewById(R.id.edittext_email);
		editTextPassword = (EditText) findViewById(R.id.edittext_password);
		editTextConfirmPassword = (EditText) findViewById(R.id.edittext_confirm_password);
		checkboxTermsCondition = (CheckBox) findViewById(R.id.checkbox_terms_conditions);
	
		buttonTermsCondition = (Button) findViewById(R.id.button_terms_conditions);
		String htmlString = "I have read and agree to <u>Foodsby terms and conditions</u>";
		buttonTermsCondition.setText(Html.fromHtml(htmlString));
    }

    public void onClickSignup (View v) {
		
	}
    
    public void onClickTermsCondition (View v) {
	
		startActivity(new Intent("android.intent.action.TermsConditions"));
	}

    public void onClickBack (View v) {
		
		onBackPressed();
	}
}
