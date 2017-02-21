package com.delivery.foodsby;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class UserProfileFragment extends Fragment{
	
	private View 			view;
	private EditText		editTextFullName;
	private EditText		editTextCreditCardNumber;
	private EditText		editTextCvv;
	private EditText		editTextBillingAddress1;
	private EditText		editTextBillingAddress2;
	private EditText		editTextCity;
	private EditText		editTextZip;
	
	private Button			buttonSaveContact;
	private Button			buttonSavePassword;
	

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        view = inflater.inflate(R.layout.fragment_user_profile, null);
        
        buttonSaveContact = (Button) view.findViewById(R.id.button_save_contact);
        buttonSaveContact.setOnClickListener(new ClickButtonListener());
        
        buttonSavePassword = (Button) view.findViewById(R.id.button_save_password);
        buttonSavePassword.setOnClickListener(new ClickButtonListener());

        MainMenuActivity.showBackButton(true);
        MainMenuActivity.setTitle("User Profile");
        
        return view;
    }
	
	private class ClickButtonListener implements OnClickListener {
		
		@Override
    	public void onClick(View v) {
    		
    		switch (v.getId()) {
    		
    		case R.id.button_save_contact:
    			
    			
    			break;
    			
    		case R.id.button_save_password:
    			
    			
    			break;	
    		}
    	}
	}
	
}
