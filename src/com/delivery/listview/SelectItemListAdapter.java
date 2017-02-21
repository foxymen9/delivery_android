package com.delivery.listview;

import com.delivery.foodsby.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SelectItemListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<SelectItemItem> arryItems;
	public SelectItemListAdapter(Context context, ArrayList<SelectItemItem> items) {
		
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
            convertView = mInflater.inflate(R.layout.list_item_select_item, parent, false);
        }
         
		TextView 	textViewMenuItemPrice = (TextView) convertView.findViewById(R.id.textview_menu_item_price);
        TextView 	textViewMenuItemDisplay = (TextView) convertView.findViewById(R.id.textview_menu_item_display);
        TextView 	textViewMenuItemDescription = (TextView) convertView.findViewById(R.id.textview_menu_item_description);
        
        textViewMenuItemPrice.setText(String.format("$%.2f", arryItems.get(position).getPrice()));
        textViewMenuItemDisplay.setText(arryItems.get(position).getDisplayText());
        textViewMenuItemDescription.setText(arryItems.get(position).getDescription());
        
        return convertView;
	}

}
