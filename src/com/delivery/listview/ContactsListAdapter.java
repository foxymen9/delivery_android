
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
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ContactsListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<ContactsItem> arryItems;
	
	public ContactsListAdapter(Context context, ArrayList<ContactsItem> items) {
		
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
		
		TextView 		textViewName;
        TextView 		textViewEmail;
		CheckBox		checkBoxItem;
		
		if (convertView == null) {
			
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item_contacts, parent, false);
            
            checkBoxItem = (CheckBox) convertView.findViewById(R.id.checkbox_contact);
            checkBoxItem.setTag(position);
            
            checkBoxItem.setOnCheckedChangeListener (new OnCheckedChangeListener () {

    			@Override
    			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    				
					Integer index = (Integer)buttonView.getTag();
					arryItems.get(index).setButtonValue(isChecked);
    			}        	
            });

            
        } else  {
        	
        	checkBoxItem = (CheckBox) convertView.findViewById(R.id.checkbox_contact);
        }
		
		
		textViewName = (TextView) convertView.findViewById(R.id.textview_name);        
		textViewName.setText(arryItems.get(position).getName());
        
		textViewEmail = (TextView) convertView.findViewById(R.id.textview_email);        
		textViewEmail.setText(arryItems.get(position).getEmail());

		if (arryItems.get(position).getButtonValue() == true)
        	checkBoxItem.setChecked(true);
        else
        	checkBoxItem.setChecked(false);
        
        return convertView;
	}
	
}
