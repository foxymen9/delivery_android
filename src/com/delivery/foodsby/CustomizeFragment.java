package com.delivery.foodsby;

import com.delivery.listview.CustomizeItem;
import com.delivery.utils.MyApplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class CustomizeFragment extends Fragment{
	
	private View 			view;
	private	LinearLayout	linearLayoutCustomize = null;
	private Button			buttonNextItem;
	private Button			buttonCheckout;
	private	EditText		editTextSpecialInstruction;
	

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        view = inflater.inflate(R.layout.fragment_customize, null);
        
        linearLayoutCustomize = (LinearLayout) view.findViewById(R.id.linearlayout_customize_item);
        buttonNextItem = (Button) view.findViewById(R.id.button_next_item);
        buttonCheckout = (Button) view.findViewById(R.id.button_checkout);
        
        editTextSpecialInstruction = (EditText) view.findViewById(R.id.edittext_special_instructions);
        
        buttonNextItem.setOnClickListener(new ClickButtonListener());
        buttonCheckout.setOnClickListener(new ClickButtonListener());

        MainMenuActivity.showBackButton(true);
        MainMenuActivity.setTitle("Customize");
        
        showSubMenus ();
        
        return view;
    }
	
    private void showSubMenus () {
    	
    	for (int i = 0 ; i < 3 ; i ++) {
    		
    		CustomizeItem viewCell = new CustomizeItem (getActivity());
    		linearLayoutCustomize.addView(viewCell);
    	}
	}

    
	private class ClickButtonListener implements OnClickListener {
		
		@Override
    	public void onClick(View v) {
    		
			MainMenuActivity.hideKeyboard();

    		switch (v.getId()) {
    		
    		case R.id.button_next_item:

    			MainMenuActivity.fragmentManager.popBackStack("SelectMenuFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    			break;
    			
    		case R.id.button_checkout:
    			
    			FragmentTransaction ft = MainMenuActivity.fragmentManager.beginTransaction();
    			OrderSummaryFragment fragment = new OrderSummaryFragment();
    			ft.setCustomAnimations(R.anim.push_enter, R.anim.push_exit, R.anim.pop_enter, R.anim.pop_exit);
    			ft.replace(R.id.fragmentlayout_main_content, fragment);
    			ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
    			ft.addToBackStack("CustomizeFragment");
    			ft.commit();			


    			break;
    		}
    	}
	}
	
}
