package com.delivery.foodsby;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CustomerContactFragment extends Fragment{
	
	private View 			view;
	private EditText		editTextEmail;
	private EditText		editTextFistName;
	private EditText		editTextLastName;
	private EditText		editTextPhoneNumber;
	private Button			buttonContinue;
	

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        view = inflater.inflate(R.layout.fragment_customer_contact, null);
        
        buttonContinue = (Button) view.findViewById(R.id.button_continue);
        editTextEmail = (EditText) view.findViewById(R.id.edittext_email);
        editTextFistName = (EditText) view.findViewById(R.id.edittext_first_name);
        editTextLastName = (EditText) view.findViewById(R.id.edittext_last_name);
        editTextPhoneNumber = (EditText) view.findViewById(R.id.edittext_phone_number);
        
        buttonContinue.setOnClickListener(new ClickButtonListener());

        MainMenuActivity.showBackButton(true);
        MainMenuActivity.setTitle("Customer Info");
        
        return view;
    }
	
	private class ClickButtonListener implements OnClickListener {
		
		@Override
    	public void onClick(View v) {
    		
    		switch (v.getId()) {
    		
    		case R.id.button_continue:
    			
    			FragmentTransaction ft = MainMenuActivity.fragmentManager.beginTransaction();

    			CheckoutFragment fragment = new CheckoutFragment();
    			
//    			CheckoutSavedFragment fragment = new CheckoutSavedFragment ();

    			ft.setCustomAnimations(R.anim.push_enter, R.anim.push_exit, R.anim.pop_enter, R.anim.pop_exit);
    			ft.replace(R.id.fragmentlayout_main_content, fragment);
    			ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
    			ft.addToBackStack("CustomerContactFragment");
    			ft.commit();			

    			break;
    			
    		}
    	}
	}
	
}
