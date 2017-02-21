
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

public class SelectMenuListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<SelectMenuItem> arryItems;
	
	public SelectMenuListAdapter(Context context, ArrayList<SelectMenuItem> items) {
		
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
            convertView = mInflater.inflate(R.layout.list_item_select_menu, parent, false);
        }
         
		TextView 	textViewSubMenuName = (TextView) convertView.findViewById(R.id.textview_submenu_name);
        TextView 	textViewMenuDescription = (TextView) convertView.findViewById(R.id.textview_menu_description);
        
        textViewSubMenuName.setText(arryItems.get(position).getSubMenuName());
        
        String strDesc = arryItems.get(position).getMenuDescription();
        
        if (( strDesc == null) || (strDesc.isEmpty() == true))
        	textViewMenuDescription.setVisibility(View.GONE);
        else
        	textViewMenuDescription.setText(arryItems.get(position).getMenuDescription());

        return convertView;
	}

}
