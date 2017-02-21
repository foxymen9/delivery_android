
package com.delivery.listview;


import com.delivery.AsyncImageLoad.AsyncThumbnailView;
import com.delivery.foodsby.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DeliveryListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<DeliveryItem> arryItems;
	
	public DeliveryListAdapter(Context context, ArrayList<DeliveryItem> items) {
		
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
            convertView = mInflater.inflate(R.layout.list_item_delivery, parent, false);
        }
         
		AsyncThumbnailView 	imageViewLogo = (AsyncThumbnailView) convertView.findViewById(R.id.imageview_office_logo);
        TextView 	textViewDeliveryTimeDesc = (TextView) convertView.findViewById(R.id.textview_delivery_time_desc);
        TextView 	textViewDeliveryTimeValue = (TextView) convertView.findViewById(R.id.textview_delivery_time_value);
        TextView 	textViewOrderTimeDesc = (TextView) convertView.findViewById(R.id.textview_order_time_desc);
        TextView 	textViewOrderTimeValue = (TextView) convertView.findViewById(R.id.textview_order_time_value);
        
        if (arryItems.get(position).getDescription() == false) {
        	
        	textViewDeliveryTimeDesc.setVisibility(View.GONE);
        	textViewOrderTimeDesc.setVisibility(View.GONE);
        }
        

//        imageViewLogo.setScaleType(ScaleType.CENTER_CROP);
		new com.delivery.AsyncImageLoad.AsyncImageLoader (arryItems.get(position).getLogo(), imageViewLogo).execute();

        textViewDeliveryTimeValue.setText(arryItems.get(position).getDeliveryTime());
        textViewOrderTimeValue.setText(arryItems.get(position).getOrderTime());

        return convertView;
	}

}
