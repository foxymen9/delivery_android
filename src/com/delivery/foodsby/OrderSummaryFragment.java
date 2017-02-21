package com.delivery.foodsby;

import java.util.ArrayList;

import com.delivery.AsyncImageLoad.AsyncThumbnailView;
import com.delivery.listview.OrderSummaryItem;
import com.delivery.listview.OrderSummaryListAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class OrderSummaryFragment extends Fragment{
	
	private View view;
	private ArrayList<OrderSummaryItem> arrayItems;
	private ListView listOrderSummary;
	private	Button 	buttonExpressCheckout;
	private	Button 	buttonCheckout;

//	@Override
//	public void onActivityCreated (Bundle savedInstanceState) {
//		
//		super.onActivityCreated(savedInstanceState);
//		
//		InputMethodManager inputManager = (InputMethodManager) MyApplication.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//    	inputManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
//	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
		
        view = inflater.inflate(R.layout.fragment_order_summary, null);
        
    	buttonExpressCheckout = (Button) view.findViewById(R.id.button_express_checkout);
        buttonCheckout = (Button) view.findViewById(R.id.button_checkout);

        buttonExpressCheckout.setOnClickListener(new ClickButtonListener());
        buttonCheckout.setOnClickListener(new ClickButtonListener());
        
        MainMenuActivity.showBackButton(true);
        MainMenuActivity.setTitle("Order Summary");
        
        showLocationInfo ();

        showOrderMenus ();
        
        showPriceInfo ();
        
        return view;
    }
	
	private void showLocationInfo () {
		
		AsyncThumbnailView 	imageViewLogo = (AsyncThumbnailView) view.findViewById(R.id.imageview_office_logo);
		TextView textViewDeliveryTime = (TextView) view.findViewById(R.id.textview_delivery_time);
		TextView textViewDeliveryInstructions = (TextView) view.findViewById(R.id.textview_delivery_instructions);

		new com.delivery.AsyncImageLoad.AsyncImageLoader ("https://test.foodsby.com//Content/Images/Logos/YourLogohere.png", imageViewLogo).execute();

		textViewDeliveryTime.setText("Deliveried at 11:30 AM at Saint Anthony Main");
		textViewDeliveryInstructions.setText("All meals delivered to the Foodsby...");
	}
	
    private void showOrderMenus () {
		
    	listOrderSummary = (ListView) view.findViewById(R.id.listview_order_summary);

    	arrayItems = new ArrayList<OrderSummaryItem>();
    	
		arrayItems.add(new OrderSummaryItem(8.25, "Mayo, greens and tomato"));
		arrayItems.add(new OrderSummaryItem("Add Item"));
		arrayItems.add(new OrderSummaryItem("Apply Promotion Code"));
		
		listOrderSummary.setOnItemClickListener(new ClickListener());
		
		// setting the nav drawer list adapter
		listOrderSummary.setAdapter(new OrderSummaryListAdapter(this.getActivity().getApplicationContext(), arrayItems));
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

	private class ClickListener implements ListView.OnItemClickListener {
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// display view for selected nav drawer item
			
			if ((position + 1) > (arrayItems.size() - 2))
				return;

			FragmentTransaction ft = MainMenuActivity.fragmentManager.beginTransaction();
			OrderSummaryDetailFragment fragment = new OrderSummaryDetailFragment();
			ft.setCustomAnimations(R.anim.push_enter, R.anim.push_exit, R.anim.pop_enter, R.anim.pop_exit);
			ft.replace(R.id.fragmentlayout_main_content, fragment);
			ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
			ft.addToBackStack("OrderSummaryFragment");
			ft.commit();			

			return;		
		}
	}
	
	private class ClickButtonListener implements OnClickListener {
		
		@Override
    	public void onClick(View v) {
    		
    		switch (v.getId()) {
    		
    		case R.id.button_express_checkout:
    			
    			
    			break;
    			
    		case R.id.button_checkout:
    			
    			FragmentTransaction ft = MainMenuActivity.fragmentManager.beginTransaction();
    			CustomerContactFragment fragment = new CustomerContactFragment();
    			ft.setCustomAnimations(R.anim.push_enter, R.anim.push_exit, R.anim.pop_enter, R.anim.pop_exit);
    			ft.replace(R.id.fragmentlayout_main_content, fragment);
    			ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
    			ft.addToBackStack("OrderSummaryFragment");
    			ft.commit();			
    
    			break;
    		}
    	}
	}

}
