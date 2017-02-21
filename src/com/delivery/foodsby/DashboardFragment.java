package com.delivery.foodsby;
import java.util.ArrayList;

import com.delivery.listview.DeliveryItem;
import com.delivery.listview.DeliveryListAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class DashboardFragment extends Fragment{
	
	private View view;
	private ArrayList<DeliveryItem> arrayItems;
	private ListView listDelivery;
	private Button[]		buttonDays;
	private Button			prevButton;
	

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        view = inflater.inflate(R.layout.fragment_dashboard, null);
        
        MainMenuActivity.showBackButton(false);
        MainMenuActivity.setTitle("Title", "sub title");

        showDelivery ();
        
        initDayButtons ();
        
        return view;
    }
	
	private void initDayButtons () {
		
        buttonDays = new Button [7];
        
        buttonDays[0] = (Button) view.findViewById(R.id.button_today);
        buttonDays[1] = (Button) view.findViewById(R.id.button_day1);
        buttonDays[2] = (Button) view.findViewById(R.id.button_day2);
        buttonDays[3] = (Button) view.findViewById(R.id.button_day3);
        buttonDays[4] = (Button) view.findViewById(R.id.button_day4);
        buttonDays[5] = (Button) view.findViewById(R.id.button_day5);
        buttonDays[6] = (Button) view.findViewById(R.id.button_day6);
        
        for (int i = 0 ; i < 7 ; i ++)
        	buttonDays[i].setOnClickListener(new ClickButtonListener());

		prevButton = buttonDays[0];
		prevButton.setBackgroundColor(getResources().getColor(R.color.button_pressed_days_background));
		prevButton.setTextColor(getResources().getColor(R.color.button_default_text_color));
	}
	
    private void showDelivery() {
		
    	listDelivery = (ListView) view.findViewById(R.id.listview_delivery_cell);

    	arrayItems = new ArrayList<DeliveryItem>();
    	
		arrayItems.add(new DeliveryItem("https://test.foodsby.com//Content/Images/Logos/YourLogohere.png", "9:00 am", "9:00 am", true));
		arrayItems.add(new DeliveryItem("https://test.foodsby.com//Content/Images/Logos/YourLogohere.png", "9:00 am", "9:00 am", true));
		
		listDelivery.setOnItemClickListener(new ClickListener());
		
		// setting the nav drawer list adapter
		listDelivery.setAdapter(new DeliveryListAdapter(this.getActivity().getApplicationContext(), arrayItems));
	}
    
	private class ClickListener implements ListView.OnItemClickListener {
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// display view for selected nav drawer item
			
			FragmentTransaction ft = MainMenuActivity.fragmentManager.beginTransaction();
			SelectMenuFragment fragment = new SelectMenuFragment();
			ft.setCustomAnimations(R.anim.push_enter, R.anim.push_exit, R.anim.pop_enter, R.anim.pop_exit);
			ft.replace(R.id.fragmentlayout_main_content, fragment);
			ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
			ft.addToBackStack("DashboardFragment");
			ft.commit();
			
			MainMenuActivity.showBackButton(true);

			return;			
		}
	}

	private class ClickButtonListener implements OnClickListener {
		
		@Override
    	public void onClick(View v) {
    		
    		int nIndex = 0;
    		
    		switch (v.getId()) {
    		
    		case R.id.button_day1:
    			nIndex = 1;
    			break;
    			
    		case R.id.button_day2:
    			nIndex = 2;
    			break;
    			
    		case R.id.button_day3:
    			nIndex = 3;
    			break;
    			
    		case R.id.button_day4:
    			nIndex = 4;
    			break;
    			
    		case R.id.button_day5:
    			nIndex = 5;
    			break;
    			
    		case R.id.button_day6:
    			nIndex = 6;
    			break;
    		}
    		
    		if (buttonDays[nIndex] == prevButton)
    			return;
    		
    		buttonDays[nIndex].setBackgroundColor(getResources().getColor(R.color.button_pressed_days_background));
    		buttonDays[nIndex].setTextColor(getResources().getColor(R.color.button_default_text_color));
    		
    		prevButton.setBackgroundResource(R.drawable.button_days_background);
    		prevButton.setTextColor(getResources().getColor(R.color.dark_text_color));
    		
    		prevButton = buttonDays[nIndex];
    	}
	}
}
