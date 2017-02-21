package com.delivery.foodsby;

import java.util.ArrayList;

import com.delivery.AsyncImageLoad.AsyncThumbnailView;
import com.delivery.listview.SelectMenuItem;
import com.delivery.listview.SelectMenuListAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class SelectMenuFragment extends Fragment{
	
	private View view;
	private ArrayList<SelectMenuItem> arrayItems;
	private ListView listMenuSelect;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        view = inflater.inflate(R.layout.fragment_select_menu, null);

        MainMenuActivity.showBackButton(true);
        MainMenuActivity.setTitle("Select Menu");
        
        showLocationInfo ();

        showSubMenus ();
        
        return view;
    }
	
	private void showLocationInfo () {
		
		AsyncThumbnailView 	imageViewLogo = (AsyncThumbnailView) view.findViewById(R.id.imageview_office_logo);
		TextView textViewDeliveryTime = (TextView) view.findViewById(R.id.textview_delivery_time);
		TextView textViewDeliveryInstructions = (TextView) view.findViewById(R.id.textview_delivery_instructions);

		new com.delivery.AsyncImageLoad.AsyncImageLoader ("https://test.foodsby.com//Content/Images/Logos/YourLogohere.png", imageViewLogo).execute();

		textViewDeliveryTime.setText("Deliveried at 11:30 AM at Saint Anthony Main");
		textViewDeliveryInstructions.setText("All meals delivered to the Foodsby...");
	}
	
    private void showSubMenus () {
		
    	listMenuSelect = (ListView) view.findViewById(R.id.listview_menu);

    	arrayItems = new ArrayList<SelectMenuItem>();
    	
		arrayItems.add(new SelectMenuItem("The Simple Sandwich", "Mayo, greens and tomato"));
		
		listMenuSelect.setOnItemClickListener(new ClickListener());
		
		// setting the nav drawer list adapter
		listMenuSelect.setAdapter(new SelectMenuListAdapter(this.getActivity().getApplicationContext(), arrayItems));
	}
    
	private class ClickListener implements ListView.OnItemClickListener {
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// display view for selected nav drawer item
			
			FragmentTransaction ft = MainMenuActivity.fragmentManager.beginTransaction();
			SelectItemFragment fragment = new SelectItemFragment();
			ft.setCustomAnimations(R.anim.push_enter, R.anim.push_exit, R.anim.pop_enter, R.anim.pop_exit);
			ft.replace(R.id.fragmentlayout_main_content, fragment);
			ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
			ft.addToBackStack("SelectMenuFragment");
			ft.commit();			

			return;		
		}
	}

}
