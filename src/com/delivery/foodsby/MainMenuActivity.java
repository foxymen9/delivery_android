package com.delivery.foodsby;

import java.util.ArrayList;

import com.delivery.listview.MainMenuItem;
import com.delivery.listview.MainMenuListAdapter;
import com.delivery.utils.MyApplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import me.tangke.slidemenu.SlideMenu;

public class MainMenuActivity extends BaseSlideMenuActivity {
	
	private SlideMenu 		mSlideMenu;
	private String[] 		strMainMenuTitles = null;
	
	private TypedArray		arrayMainMenuIcons;
	private ListView 		mMainMenuList;
	
	private	ArrayList<MainMenuItem>	navMainMenuItems;
	
	private static	TextView 		textViewTitle;
	private static LinearLayout 	linearLayoutTitle;
	private static TextView			textViewMainTitle;
	private static TextView			textViewSubTitle;
	private static	ImageButton		imageButtonBack;
	
	public	static 	FragmentManager 	fragmentManager = null;
	private	static 	MainMenuActivity	menuActivity = null;
	
	
	@Override
	public void onContentChanged() {
		
		super.onContentChanged();
		
		menuActivity = this;
		
		setSlideRole(R.layout.activity_main_menu);
		setSlideRole(R.layout.layout_primary_menu);
		
		mSlideMenu = getSlideMenu();
		
		textViewTitle = (TextView) findViewById(R.id.textview_title);
		linearLayoutTitle = (LinearLayout) findViewById(R.id.linearlayout_title);
		textViewMainTitle = (TextView) findViewById(R.id.textview_main_title);
		textViewSubTitle = (TextView) findViewById(R.id.textview_sub_title);
		imageButtonBack = (ImageButton) findViewById (R.id.imagebutton_menu_back);
		
		fragmentManager = MainMenuActivity.this.getSupportFragmentManager();

		CreateMainMenu();
	}
	
    public void onClickBack (View v) {
		
    	hideKeyboard ();
    	
    	if (fragmentManager.getBackStackEntryCount() > 0)
    		fragmentManager.popBackStack();
	}

    public void onMenu (View v) {
		
    	if (mSlideMenu.getCurrentState() == SlideMenu.STATE_CLOSE)
    		mSlideMenu.open(true, true);
    	else
    		mSlideMenu.close(true);
    }
    
    //Menu
    
    private void CreateMainMenu() {
		
		mMainMenuList = (ListView) findViewById(R.id.listview_menu_main);

		strMainMenuTitles = getResources().getStringArray(R.array.main_menu_items);
		arrayMainMenuIcons = getResources().obtainTypedArray(R.array.main_menu_icons);
		
		navMainMenuItems = new ArrayList<MainMenuItem>();

		// adding nav drawer items to array
		// Home
		navMainMenuItems.add(new MainMenuItem(strMainMenuTitles[0], arrayMainMenuIcons.getResourceId(0, -1)));
		// Order Summary
		navMainMenuItems.add(new MainMenuItem(strMainMenuTitles[1], arrayMainMenuIcons.getResourceId(1, -1)));
		// User Profile
		navMainMenuItems.add(new MainMenuItem(strMainMenuTitles[2], arrayMainMenuIcons.getResourceId(2, -1)));
		// Notifications
		navMainMenuItems.add(new MainMenuItem(strMainMenuTitles[3], arrayMainMenuIcons.getResourceId(3, -1)));
		// Change Office
		navMainMenuItems.add(new MainMenuItem(strMainMenuTitles[4], arrayMainMenuIcons.getResourceId(4, -1)));
		// Logout
		navMainMenuItems.add(new MainMenuItem(strMainMenuTitles[5], arrayMainMenuIcons.getResourceId(5, -1)));

		// Recycle the typed array
		arrayMainMenuIcons.recycle();

		mMainMenuList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		mMainMenuList.setAdapter(new MainMenuListAdapter(getApplicationContext(), navMainMenuItems));

		FragmentTransaction ft = fragmentManager.beginTransaction();
		
		OfficeFragment fragment = new OfficeFragment();
		
		ft.add(R.id.fragmentlayout_main_content, fragment);
		ft.commit();
		
		textViewTitle.setText(strMainMenuTitles[0]);
		
		linearLayoutTitle.setVisibility(View.VISIBLE);
		textViewTitle.setVisibility(View.GONE);		
	}

	/**
	 * Slide menu item click listener
	 * */
	
	private class SlideMenuClickListener implements ListView.OnItemClickListener {
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			displayMainMenu(position);
			
		}
	}

	private void displayMainMenu(int position) {
		
		Fragment fragment = null;

		// update the main content by replacing fragments
		switch (position) {
		
		case 0://Home
			
			fragment = new DashboardFragment ();
			
			break;
			
		case 1://Order Summary
			
			fragment = new OrderSummaryFragment ();
			
			break;
			
		case 2://User Profile
			
			fragment = new UserProfileFragment ();
			
			break;
			
		case 3://Notifications
			
			fragment = new NotificationsFragment ();
			
			break;
			
		case 4://Change Office
			
			fragment = new OfficeFragment ();
			
			break;
			
		case 5://Logout
			
			 Intent intent = new Intent(this, MainActivity.class);
			 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			 startActivity(intent);
			
			break;

		default:
			
			break;
		}
		
		if (position == 0) {
			//Home -
			linearLayoutTitle.setVisibility(View.VISIBLE);
			textViewTitle.setVisibility(View.GONE);
			showBackButton(false);
			
		} else {
			
			linearLayoutTitle.setVisibility(View.GONE);
			textViewTitle.setVisibility(View.VISIBLE);
			showBackButton(true);
		}			

		if (fragment != null) {
			
			FragmentTransaction ft = MainMenuActivity.fragmentManager.beginTransaction();
			ft.setCustomAnimations(R.anim.push_enter, R.anim.push_exit, R.anim.pop_enter, R.anim.pop_exit);
			ft.replace(R.id.fragmentlayout_main_content, fragment);
			ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
			ft.addToBackStack("MainMenuActivity");
			ft.commit();			

			textViewTitle.setText(strMainMenuTitles[position]);
			
		} 

		onMenu (null);
	}
	
	public static void setTitle (String title) {
		
		linearLayoutTitle.setVisibility(View.GONE);
		textViewTitle.setVisibility(View.VISIBLE);
		
		textViewTitle.setText(title);
	}
	
	public static void setTitle (String title, String subTitle) {
		
		linearLayoutTitle.setVisibility(View.VISIBLE);
		textViewTitle.setVisibility(View.GONE);
		
		textViewMainTitle.setText(title);
		textViewSubTitle.setText(subTitle);
	}
	
	public static void setMenuType (Integer type) {
	
		
	}
	
	public static void showBackButton (boolean show) {
		
		if (show == true)
			imageButtonBack.setVisibility(View.VISIBLE);
		else
			imageButtonBack.setVisibility(View.GONE);
	}
	
	public static void hideKeyboard () {

    	InputMethodManager inputManager = (InputMethodManager) MyApplication.getAppContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
    	inputManager.hideSoftInputFromWindow(menuActivity.getCurrentFocus().getWindowToken(), 0);

    	menuActivity.getCurrentFocus().clearFocus();
	}
}
