package com.delivery.foodsby;

import com.delivery.foodsby.R;

import me.tangke.slidemenu.SlideMenu;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class BaseSlideMenuActivity extends FragmentActivity {
	
	private SlideMenu mSlideMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_slidemenu);
	}

	@Override
	public void onContentChanged() {
		
		super.onContentChanged();
		mSlideMenu = (SlideMenu) findViewById(R.id.slide_menu);
	}

	public void setSlideRole(int res) {
		
		if (null == mSlideMenu) {
			return;
		}

		getLayoutInflater().inflate(res, mSlideMenu, true);
	}

	public SlideMenu getSlideMenu() {
		
		return mSlideMenu;
	}
	
}
