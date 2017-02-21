
package com.delivery.listview;


import com.delivery.foodsby.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainMenuListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<MainMenuItem> arryItems;
	
	public MainMenuListAdapter(Context context, ArrayList<MainMenuItem> items) {
		
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
            convertView = mInflater.inflate(R.layout.list_item_main_menu, parent, false);
        }
         
        ImageView 	imgIcon = (ImageView) convertView.findViewById(R.id.imageview_menu_icon);
        TextView 	txtTitle = (TextView) convertView.findViewById(R.id.textview_menu_title);
         
        if (arryItems.get(position).getIcon() == 0)
        	imgIcon.setVisibility(View.GONE);
        else
        	imgIcon.setImageResource(arryItems.get(position).getIcon());
        
        txtTitle.setText(arryItems.get(position).getTitle());

        return convertView;
	}

}
