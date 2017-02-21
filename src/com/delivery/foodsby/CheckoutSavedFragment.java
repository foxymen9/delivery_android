package com.delivery.foodsby;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class CheckoutSavedFragment extends Fragment {
	
	private View 			view;
	
	private Button			buttonPlaceOrder;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        view = inflater.inflate(R.layout.fragment_checkout_saved, null);
        
        buttonPlaceOrder = (Button) view.findViewById(R.id.button_place_order);
        buttonPlaceOrder.setOnClickListener(new ClickButtonListener());
        
        showPriceInfo ();
        
        setCreditCards ();
        
        MainMenuActivity.showBackButton(true);
        MainMenuActivity.setTitle("Checkout");
        
        return view;
    }
	
	private void setCreditCards () {
		
		String[] iplTeam= {"Credit Card 1", "Credit Card 2", "Credit Card 3", "Credit Card 4", "Credit Card 5"};

        Spinner spinner=(Spinner) view.findViewById(R.id.spinner_credit_cards);  

        ArrayAdapter adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, iplTeam);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

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
