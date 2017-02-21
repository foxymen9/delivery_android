
package com.delivery.listview;


import com.delivery.foodsby.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class CustomizeRadioButtonListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<CustomizeRadioButtonItem> arryItems;
	private ArrayList<RadioButton> arrayButtons;

	public CustomizeRadioButtonListAdapter(Context context, ArrayList<CustomizeRadioButtonItem> items) {
		
		this.context = context;
		this.arryItems = items;
		
		arrayButtons = new ArrayList<RadioButton> ();
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
        RadioButton		radioButtonItem;
        
		if (convertView == null) {
			
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item_customize_radiobutton, parent, false);
            radioButtonItem = (RadioButton) convertView.findViewById(R.id.radiobutton_cutomize_item);
            radioButtonItem.setTag(position);
            arrayButtons.add(radioButtonItem);
            
            radioButtonItem.setOnCheckedChangeListener (new OnCheckedChangeListener () {

    			@Override
    			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    				
    				RadioButton		radioButtonItem;
    				
    				for (int i = 0 ; i < arrayButtons.size() ; i ++) {
    					
    					radioButtonItem = arrayButtons.get(i);
    					radioButtonItem.setChecked(false);
    				}
    					
    				Integer index = (Integer)buttonView.getTag();
    				radioButtonItem = arrayButtons.get(index);
					radioButtonItem.setChecked(isChecked);
					
    				arryItems.get(index).setButtonValue(isChecked);
    			}        	
            });


        } else {
        	
            radioButtonItem = (RadioButton) convertView.findViewById(R.id.radiobutton_cutomize_item);
        }

		
		
		txtTitle = (TextView) convertView.findViewById(R.id.textview_customize_item);
        
        txtTitle.setText(arryItems.get(position).getTitle());
        
        if (arryItems.get(position).getButtonValue() == true)
        	radioButtonItem.setChecked(true);
        else
        	radioButtonItem.setChecked(false);

        return convertView;
	}

}
