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

public class OrderItemDetailListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<OrderItemDetail> arryItems;
	
	public OrderItemDetailListAdapter(Context context, ArrayList<OrderItemDetail> items) {
		
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
            convertView = mInflater.inflate(R.layout.list_item_order_detail, parent, false);
        }

		TextView textViewMenuItemDisplay = (TextView) convertView.findViewById(R.id.textview_order_item_detail);
	    textViewMenuItemDisplay.setText(arryItems.get(position).getDisplayText());
        
        return convertView;
	}
	
}
