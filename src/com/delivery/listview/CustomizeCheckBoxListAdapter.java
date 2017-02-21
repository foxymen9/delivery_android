
package com.delivery.listview;


import com.delivery.foodsby.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class CustomizeCheckBoxListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<CustomizeCheckBoxItem> arryItems;
	
	public CustomizeCheckBoxListAdapter(Context context, ArrayList<CustomizeCheckBoxItem> items) {
		
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
		
		TextView 		txtTitle;
		CheckBox		checkBoxItem;
		
		if (convertView == null) {
			
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item_customize_checkbox, parent, false);
            
            checkBoxItem = (CheckBox) convertView.findViewById(R.id.checkbox_customize_item);
            checkBoxItem.setTag(position);
            
            checkBoxItem.setOnCheckedChangeListener (new OnCheckedChangeListener () {

    			@Override
    			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    				
					Integer index = (Integer)buttonView.getTag();
					arryItems.get(index).setButtonValue(isChecked);
    			}        	
            });

            
        } else  {
        	
        	checkBoxItem = (CheckBox) convertView.findViewById(R.id.checkbox_customize_item);
        }
		
		
        txtTitle = (TextView) convertView.findViewById(R.id.textview_customize_item);
        
        txtTitle.setText(arryItems.get(position).getTitle());
        
        if (arryItems.get(position).getButtonValue() == true)
        	checkBoxItem.setChecked(true);
        else
        	checkBoxItem.setChecked(false);
        
        return convertView;
	}

}
