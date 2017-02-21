package com.delivery.foodsby;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.apache.http.Header;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


public class MainActivity extends Activity {

	private 	EditText		editTextEmailAddress;
	private 	EditText		editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
 
    	super.onCreate(savedInstanceState);
	    
    	requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        
		editTextEmailAddress = (EditText) findViewById(R.id.edittext_email);
		editTextPassword = (EditText) findViewById(R.id.edittext_password);
		
		editTextEmailAddress.setText("test16@gmail.com");
		editTextPassword.setText("123");		
    }

	public void onClickForgotPassword(View v) {

		startActivity(new Intent("android.intent.action.ResetPassword"));
	}

	public void onClickLogin (View v) {
		
	    InputMethodManager inputMethodManager = (InputMethodManager)  getSystemService(Activity.INPUT_METHOD_SERVICE);
	    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

		String strEmailAddress = editTextEmailAddress.getText().toString();
		String strPassword = editTextPassword.getText().toString();
		
		if (strEmailAddress.isEmpty() == true) {
			
			new AlertDialog.Builder(this).setTitle("Foodsby").setMessage("Email address is required").setIcon(R.drawable.ic_launcher).setNeutralButton("Ok", null).show();
			return;
		}
		
		if (strPassword.isEmpty() == true) {
			
			new AlertDialog.Builder(this).setTitle("Foodsby").setMessage("Password is required").setIcon(R.drawable.ic_launcher).setNeutralButton("Ok", null).show();
			return;
		}

		login(strEmailAddress, strPassword);

//		startActivity(new Intent("android.intent.action.Dashboard"));
	}

	public void onClickSignup (View v) {
		
		startActivity(new Intent("android.intent.action.Signup"));
	}
	
	private void login(final String emailAddress, final String password) {
		
//		ProgressDialogManage.show (this);
		
		AsyncHttpClient client = new AsyncHttpClient();
		
		client.setResponseTimeout(30000);
		
		client.setBasicAuth(emailAddress, password);
		
		client.get("https://test.foodsby.com/api/login", null, new AsyncHttpResponseHandler() {

		    @Override
		    public void onStart () {
		        // called before request is started
		    	
		    	return;
		    }

		    @Override
		    public void onSuccess(int statusCode, Header[] headers, byte[] response) {
		        // called when response HTTP status is "200 OK"
		    	token (emailAddress, password);
		    	return;
		    }

		    @Override
		    public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
		        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
		    	
		    	
		    	return;
		    }

		    @Override
		    public void onRetry(int retryNo) {
		        // called when request is retried
		    	
		    	return;
			}

		});

	}

	private void token (final String emailAddress, final String password) {
		
		AsyncHttpClient client = new AsyncHttpClient();
		
		client.setResponseTimeout(30000);
		
		client.setBasicAuth(emailAddress, password);
		
		client.get("https://test.foodsby.com/api/token", null, new AsyncHttpResponseHandler() {

		    @Override
		    public void onStart() {
		        // called before request is started
		    	
		    	return;
		    }

		    @Override
		    public void onSuccess(int statusCode, Header[] headers, byte[] response) {
		        // called when response HTTP status is "200 OK"
		    	
		    	return;
		    }

		    @Override
		    public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
		        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
		    	
		    	
		    	return;
		    }

		    @Override
		    public void onRetry(int retryNo) {
		        // called when request is retried
		    	
		    	return;
			}

		});

	}

}
