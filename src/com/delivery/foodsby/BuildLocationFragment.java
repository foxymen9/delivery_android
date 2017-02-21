package com.delivery.foodsby;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class BuildLocationFragment extends Fragment{

	private View			view;
	private LinearLayout	linearLayoutCoWorkers;
	private LinearLayout	linearLayoutStatus;	
	private LinearLayout	linearLayoutTab1;
	private LinearLayout	linearLayoutTab2;

	private Button			buttonFindContracts;
	private Button			buttonSend;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
		view = inflater.inflate(R.layout.fragment_build_location, null);
        
		linearLayoutCoWorkers = (LinearLayout) view.findViewById(R.id.linearlayout_invite_coworks);
		linearLayoutStatus = (LinearLayout) view.findViewById(R.id.linearlayout_status);
		linearLayoutTab1 = (LinearLayout) view.findViewById(R.id.linearlayout_tab1);
		linearLayoutTab2 = (LinearLayout) view.findViewById(R.id.linearlayout_tab2);
		buttonFindContracts = (Button) view.findViewById(R.id.button_find_contracts);
		buttonSend = (Button) view.findViewById(R.id.button_send);		
		
		buttonFindContracts.setOnClickListener(new ClickButtonListener());
		buttonSend.setOnClickListener(new ClickButtonListener());

		linearLayoutStatus.setVisibility(View.GONE);
		
        MainMenuActivity.showBackButton(true);
        MainMenuActivity.setTitle("Build Location");
        
        linearLayoutTab1.setOnClickListener(new View.OnClickListener() {
            
        	@Override
            public void onClick(View v) {
        		
        		linearLayoutCoWorkers.setVisibility(View.VISIBLE);
        		linearLayoutStatus.setVisibility(View.GONE);
        		
        		linearLayoutTab1.setBackgroundColor(getResources().getColor(R.color.main_background_color));
        		linearLayoutTab2.setBackgroundColor(Color.TRANSPARENT);        		
            }
        });

        linearLayoutTab2.setOnClickListener(new View.OnClickListener() {
            
        	@Override
            public void onClick(View v) {
            	
        		linearLayoutCoWorkers.setVisibility(View.GONE);
        		linearLayoutStatus.setVisibility(View.VISIBLE);
        		
        		linearLayoutTab1.setBackgroundColor(Color.TRANSPARENT);
        		linearLayoutTab2.setBackgroundColor(getResources().getColor(R.color.main_background_color));
        	}
        });
        
        return view;
    }
		
	private class ClickButtonListener implements OnClickListener {
		
		@Override
    	public void onClick(View v) {
    		
    		switch (v.getId()) {
    		
    		case R.id.button_find_contracts:
    			
    			FragmentTransaction ft = MainMenuActivity.fragmentManager.beginTransaction();

    			InviteContactsFragment fragment = new InviteContactsFragment();

    			ft.setCustomAnimations(R.anim.push_enter, R.anim.push_exit, R.anim.pop_enter, R.anim.pop_exit);
    			ft.replace(R.id.fragmentlayout_main_content, fragment);
    			ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
    			ft.addToBackStack("BuildLocationFragment");
    			ft.commit();			

    			break;
    			
    		
    		case R.id.button_send:
	
				break;
			
    		}
		}
	}

}