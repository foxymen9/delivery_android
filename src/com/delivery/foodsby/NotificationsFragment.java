package com.delivery.foodsby;

import java.util.ArrayList;

import com.delivery.listview.NotificationsItem;
import com.delivery.listview.NotificationsListAdapter;
import com.delivery.listview.OrderSummaryItem;
import com.delivery.listview.OrderSummaryListAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class NotificationsFragment extends Fragment{
	
	private View 			view;
	private ArrayList<NotificationsItem> arrayItems;
	private ListView 		listNotifications;
	private Spinner 		spinnerNotificationTime;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        view = inflater.inflate(R.layout.fragment_notifications, null);
        
        showNotifications ();
        setNotificationsTime ();
        
        MainMenuActivity.showBackButton(true);
        MainMenuActivity.setTitle("Notifications");
        
        return view;
    }
	
    private void showNotifications () {
		
    	listNotifications = (ListView) view.findViewById(R.id.listview_notifications);

    	arrayItems = new ArrayList<NotificationsItem>();
    	
		arrayItems.add(new NotificationsItem("Add Item", true));
		arrayItems.add(new NotificationsItem("Apply Promotion Code", false));
		
		// setting the nav drawer list adapter
		listNotifications.setAdapter(new NotificationsListAdapter(this.getActivity().getApplicationContext(), arrayItems));
	}

	private void setNotificationsTime () {
		
		String[] arrayMonth = {"30 minutes", "45 minutes", "1 hour", "2 hours"};

		spinnerNotificationTime = (Spinner) view.findViewById(R.id.spinner_notifications_time);  

        ArrayAdapter adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, arrayMonth);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNotificationTime.setAdapter(adapter);
        spinnerNotificationTime.setOnItemSelectedListener(new MyOnItemSelectedListener());
	}
	
	private  class MyOnItemSelectedListener implements OnItemSelectedListener {
		 
	    @Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	}

	
}
