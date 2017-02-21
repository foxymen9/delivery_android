package com.delivery.listview;

import com.delivery.foodsby.MainMenuActivity;
import com.delivery.foodsby.OrderSummaryFragment;
import com.delivery.foodsby.PromotionCodeFragment;
import com.delivery.foodsby.R;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrderSummaryListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<OrderSummaryItem> arryItems;
	public OrderSummaryListAdapter(Context context, ArrayList<OrderSummaryItem> items) {
		
		this.context = context;
		this.arryItems = items;
	}

	@Override
	public int getCount() {
		
		return arryItems.size();
	}

	@Override
	public Object getItem(int position) {
		
		return arryItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item_order_summary, parent, false);
        }

		LinearLayout	linearLayoutContainer = null;
		TextView 		textViewMenuItemPrice = null;
		TextView 		textViewMenuItemDisplay = null;
		ImageButton		imageButton = null;
		
		int type = arryItems.get(position).getType();
		
		if (type == 0) {
			
			linearLayoutContainer = (LinearLayout) convertView.findViewById(R.id.linearlayout_order_add);
			textViewMenuItemPrice = (TextView) convertView.findViewById(R.id.textview_menu_item_price);
			textViewMenuItemDisplay = (TextView) convertView.findViewById(R.id.textview_menu_item_display);
			imageButton = (ImageButton) convertView.findViewById(R.id.imagebutton_remove);
			
			linearLayoutContainer.setVisibility(View.GONE);
	        textViewMenuItemPrice.setText(String.format("$%.2f", arryItems.get(position).getPrice()));
			
		} else if (type == 1){
			
			linearLayoutContainer = (LinearLayout) convertView.findViewById(R.id.linearlayout_order_menu);
			textViewMenuItemDisplay = (TextView) convertView.findViewById(R.id.textview_order_item_add);
	        imageButton = (ImageButton) convertView.findViewById(R.id.imagebutton_add);
	        
	        linearLayoutContainer.setVisibility(View.GONE);
		}
        
        textViewMenuItemDisplay.setText(arryItems.get(position).getDisplayText());

		imageButton.setTag(position);

        imageButton.setOnClickListener(new ClickButtonListener());

        return convertView;
	}

	private class ClickButtonListener implements OnClickListener {
		
		@Override
    	public void onClick(View v) {
			
			Integer count = getCount();
			Integer nIndex = (Integer)v.getTag();
			
			if (nIndex == (count - 2)) {
				
				//Add Item
				
    			MainMenuActivity.fragmentManager.popBackStack("SelectMenuFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);

			} else if (nIndex == (count - 1)) {
				//Apply Promotion Code
				
    			FragmentTransaction ft = MainMenuActivity.fragmentManager.beginTransaction();
    			PromotionCodeFragment fragment = new PromotionCodeFragment();
    			ft.setCustomAnimations(R.anim.push_enter, R.anim.push_exit, R.anim.pop_enter, R.anim.pop_exit);
    			ft.replace(R.id.fragmentlayout_main_content, fragment);
    			ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
    			ft.addToBackStack("OrderSummaryFragment");
    			ft.commit();			

			} else {
				//Remove item
				
			}			
			
    	}
	}
	
}
