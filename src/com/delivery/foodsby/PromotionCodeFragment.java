package com.delivery.foodsby;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PromotionCodeFragment extends Fragment{
	
	private View 			view;
	private EditText		editTextPromotionCode;
	private Button			buttonCancel;
	private Button			buttonApplyCode;
	

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        view = inflater.inflate(R.layout.fragment_order_summary_promotion_code, null);
        
        buttonCancel = (Button) view.findViewById(R.id.button_promotion_close);
        buttonApplyCode = (Button) view.findViewById(R.id.button_promotion_apply_code);
        editTextPromotionCode = (EditText) view.findViewById(R.id.edittext_promotion_code);
        
        buttonCancel.setOnClickListener(new ClickButtonListener());
        buttonApplyCode.setOnClickListener(new ClickButtonListener());

        MainMenuActivity.showBackButton(true);
        MainMenuActivity.setTitle("Promotion Code");
        
        return view;
    }
	
	private class ClickButtonListener implements OnClickListener {
		
		@Override
    	public void onClick(View v) {
    		
    		switch (v.getId()) {
    		
    		case R.id.button_promotion_close:

    			MainMenuActivity.fragmentManager.popBackStack("OrderSummaryFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    			break;
    			
    		case R.id.button_promotion_apply_code:
    			
    			break;
    		}
    	}
	}
	
}
