package com.delivery.foodsby;

import java.util.ArrayList;

import com.delivery.AsyncImageLoad.AsyncThumbnailView;
import com.delivery.listview.SelectItemItem;
import com.delivery.listview.SelectItemListAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class SelectItemFragment extends Fragment{
	
	private View view;
	private ArrayList<SelectItemItem> arrayItems;
	private ListView listSelectItem;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        view = inflater.inflate(R.layout.fragment_select_item, null);

        MainMenuActivity.showBackButton(true);
        MainMenuActivity.setTitle("Select Item");
        
        showMenuInfo ();

        showMenuItems ();
        
        return view;
    }
	
	private void showMenuInfo () {
		
		AsyncThumbnailView 	imageViewLogo = (AsyncThumbnailView) view.findViewById(R.id.imageview_office_logo);
		TextView textViewMenuDisplayText = (TextView) view.findViewById(R.id.textview_menu_display_text);
		TextView textViewMenuDescription = (TextView) view.findViewById(R.id.textview_menu_decription);

		new com.delivery.AsyncImageLoad.AsyncImageLoader ("https://test.foodsby.com//Content/Images/Logos/YourLogohere.png", imageViewLogo).execute();

		textViewMenuDisplayText.setText("Subs & Sandwiches");
		textViewMenuDescription.setText("All meals delivered to the Foodsby...");
	}
	
    private void showMenuItems () {
		
    	listSelectItem = (ListView) view.findViewById(R.id.listview_menu_item);

    	arrayItems = new ArrayList<SelectItemItem>();
    	
		arrayItems.add(new SelectItemItem(8.45, "The Simple Sandwich", "Mayo, greens and tomato"));
		
		listSelectItem.setOnItemClickListener(new ClickListener());
		
		// setting the nav drawer list adapter
		listSelectItem.setAdapter(new SelectItemListAdapter(this.getActivity().getApplicationContext(), arrayItems));
	}
    
	private class ClickListener implements ListView.OnItemClickListener {
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			FragmentTransaction ft = MainMenuActivity.fragmentManager.beginTransaction();
			CustomizeFragment fragment = new CustomizeFragment();
			ft.setCustomAnimations(R.anim.push_enter, R.anim.push_exit, R.anim.pop_enter, R.anim.pop_exit);
			ft.replace(R.id.fragmentlayout_main_content, fragment);
			ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
			ft.addToBackStack("SelectItemFragment");
			ft.commit();			
		}
	}

}
