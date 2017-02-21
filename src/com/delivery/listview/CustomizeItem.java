package com.delivery.listview;

import java.util.ArrayList;

import com.delivery.foodsby.R;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class CustomizeItem extends LinearLayout {

	private View 	view;
	private ArrayList<CustomizeCheckBoxItem> arrayItems2;
	private ArrayList<CustomizeRadioButtonItem> arrayItems1;
	private ListView listCustomizeItem;
	private	TextView textViewQuestion;
	private	Context context = null;

	
	public CustomizeItem(Context context) {
		super(context);

		this.context = context;
		
		LayoutInflater inflater = LayoutInflater.from (getContext());
		view = inflater.inflate(R.layout.layout_customize_item, this);
		
		showAnswerItems ();
	}

    private void showAnswerItems () {
		
    	listCustomizeItem = (ListView) view.findViewById(R.id.listview_cutomize_item_answer);
    	textViewQuestion = (TextView) view.findViewById(R.id.textview_customize_item_question);
    	
    	textViewQuestion.setText("Question Title");

    	arrayItems1 = new ArrayList<CustomizeRadioButtonItem>();
    	
    	arrayItems1.add(new CustomizeRadioButtonItem("The Simple Sandwich1", true));
    	arrayItems1.add(new CustomizeRadioButtonItem("The Simple Sandwich2", false));
    	arrayItems1.add(new CustomizeRadioButtonItem("The Simple Sandwich3", true));
    	arrayItems1.add(new CustomizeRadioButtonItem("The Simple Sandwich4", false));
    	arrayItems1.add(new CustomizeRadioButtonItem("The Simple Sandwich5", false));
		
		listCustomizeItem.setOnItemClickListener(new ClickListener());
		
		// setting the nav drawer list adapter
		listCustomizeItem.setAdapter(new CustomizeRadioButtonListAdapter(this.context, arrayItems1));
		
		LayoutParams params = (LayoutParams) listCustomizeItem.getLayoutParams();
		int height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40 * arrayItems1.size() , getResources().getDisplayMetrics());
		params.height = height;
	}
    
	private class ClickListener implements ListView.OnItemClickListener {
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// display view for selected nav drawer item
			
		}
	}


}
