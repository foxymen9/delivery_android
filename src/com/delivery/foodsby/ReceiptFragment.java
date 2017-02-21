package com.delivery.foodsby;

import java.util.ArrayList;

import com.delivery.AsyncImageLoad.AsyncThumbnailView;
import com.delivery.listview.SelectItemItem;
import com.delivery.listview.SelectItemListAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class ReceiptFragment extends Fragment{
	
	private View view;
	private ArrayList<SelectItemItem> arrayItems;
	private ListView listOrderSummary;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
		view = inflater.inflate(R.layout.fragment_receipt, null);
        
        MainMenuActivity.showBackButton(false);
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

    	arrayItems = new ArrayList<SelectItemItem>();
    	
		arrayItems.add(new SelectItemItem(8.25, "The Simple Sandwich", "Mayo, greens and tomato"));
		
		// setting the nav drawer list adapter
		listOrderSummary.setAdapter(new SelectItemListAdapter(this.getActivity().getApplicationContext(), arrayItems));
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

}
