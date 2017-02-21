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

public class CheckoutFragment extends Fragment{
	
	private View 			view;
	private EditText		editTextFullName;
	private EditText		editTextCreditCardNumber;
	private EditText		editTextCvv;
	private EditText		editTextBillingAddress1;
	private EditText		editTextBillingAddress2;
	private EditText		editTextCity;
	private EditText		editTextZip;
	
	private Spinner 		spinnerExpireMonth;
	private Spinner 		spinnerExpireYear;
	private Spinner 		spinnerState;
	
	private Button			buttonPlaceOrder;
	

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        view = inflater.inflate(R.layout.fragment_checkout, null);
        
        buttonPlaceOrder = (Button) view.findViewById(R.id.button_place_order);
        
        buttonPlaceOrder.setOnClickListener(new ClickButtonListener());
        
        setExpires();
        
        setState ();
        
        showPriceInfo ();
        
        MainMenuActivity.showBackButton(true);
        MainMenuActivity.setTitle("Checkout");
        
        return view;
    }
	
	private void showPriceInfo () {
		
		TextView textViewSubtotalPrice = (TextView) view.findViewById(R.id.textview_subtotal_price);
		TextView textViewDeliveryPrice = (TextView) view.findViewById(R.id.textview_delivery_price);
		TextView textViewTaxPrice = (TextView) view.findViewById(R.id.textview_tax_price);
		TextView textViewDiscountPrice = (TextView) view.findViewById(R.id.textview_discount_price);
		TextView textViewTotalPrice = (TextView) view.findViewById(R.id.textview_total_price);

		textViewSubtotalPrice.setText(String.format("$%.2f", 2.0));
		textViewDeliveryPrice.setText(String.format("$%.2f", 2.0));
		textViewTaxPrice.setText(String.format("$%.2f", 2.0));
		textViewDiscountPrice.setText(String.format("$%.2f", 2.0));
		textViewTotalPrice.setText(String.format("$%.2f", 2.0));
	}

	private class ClickButtonListener implements OnClickListener {
		
		@Override
    	public void onClick(View v) {
    		
    		switch (v.getId()) {
    		
    		case R.id.button_place_order:
    			
    			FragmentTransaction ft = MainMenuActivity.fragmentManager.beginTransaction();
    			ReceiptFragment fragment = new ReceiptFragment();
    			ft.setCustomAnimations(R.anim.push_enter, R.anim.push_exit, R.anim.pop_enter, R.anim.pop_exit);
    			ft.replace(R.id.fragmentlayout_main_content, fragment);
    			ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
    			ft.addToBackStack("CheckoutFragment");
    			ft.commit();			

    			break;
    			
    		}
    	}
	}
	
	private void setExpires () {
		
		String[] arrayMonth = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

        spinnerExpireMonth = (Spinner) view.findViewById(R.id.spinner_expire_month);  

        ArrayAdapter adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, arrayMonth);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExpireMonth.setAdapter(adapter);
        spinnerExpireMonth.setOnItemSelectedListener(new MyOnItemSelectedListener());

        String[] arrayYear = {"2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2055"};

        spinnerExpireYear = (Spinner) view.findViewById(R.id.spinner_expire_year);  

        adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, arrayYear);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExpireYear.setAdapter(adapter);
        spinnerExpireYear.setOnItemSelectedListener(new MyOnItemSelectedListener());

	}

	private void setState () {
		
		String[] arrayMonth = {"Alabama", "Alaska", "American Samoa", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "District Of Columbia", 
				"Federated States Of Micronesia", "Florida", "Georgia", "Guam", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", 
				"Marshall Islands", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", 
				"New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Northern Mariana Islands", "Ohio", "Oklahoma", "Oregon", "Palau", "Pennsylvania", 
				"Puerto Rico", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virgin Islands", "Virginia", "Washington", 
				"West Virginia", "Wisconsin", "Wyoming"};

        spinnerState = (Spinner) view.findViewById(R.id.spinner_state);  

        ArrayAdapter adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, arrayMonth);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(adapter);
        spinnerState.setOnItemSelectedListener(new MyOnItemSelectedListener());
	}
	
	private  class MyOnItemSelectedListener implements OnItemSelectedListener {
		 
	    @Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	}

	
}
