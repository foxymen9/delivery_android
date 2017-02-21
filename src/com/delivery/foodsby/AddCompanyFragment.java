package com.delivery.foodsby;

import java.util.ArrayList;

import com.delivery.listview.OfficeItem;
import com.delivery.listview.OfficeListAdapter;
import com.delivery.listview.VerifyAddressItem;
import com.delivery.listview.VerifyAddressListAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class AddCompanyFragment extends Fragment{
	
	private View 			view;
	private EditText		editTextCompanyName;
	private EditText		editTextStreetAddress;
	private EditText		editTextSuite;
	private EditText		editTextCity;
	private EditText		editTextState;
	private EditText		editTextZip;
	private Button			buttonAdd;
	
	private ArrayList<VerifyAddressItem> arrayItems;
	private ListView 		listOffice;

	private LinearLayout	linearLayoutAddCompany;
	private RelativeLayout 	relativelayoutVerifyAddress;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        view = inflater.inflate(R.layout.fragment_add_company, null);
        
        linearLayoutAddCompany = (LinearLayout) view.findViewById(R.id.linearlayout_add_company);
        relativelayoutVerifyAddress = (RelativeLayout) view.findViewById(R.id.relativelayout_verify_address);
        
        relativelayoutVerifyAddress.setVisibility(View.GONE);
        
        buttonAdd = (Button) view.findViewById(R.id.button_add);
        editTextCompanyName = (EditText) view.findViewById(R.id.edittext_company_name);
        editTextStreetAddress = (EditText) view.findViewById(R.id.edittext_street_address);
        editTextSuite = (EditText) view.findViewById(R.id.edittext_suite);
        editTextCity = (EditText) view.findViewById(R.id.edittext_city);
        editTextState = (EditText) view.findViewById(R.id.edittext_state);
        editTextZip = (EditText) view.findViewById(R.id.edittext_zip);
        
        buttonAdd.setOnClickListener(new ClickButtonListener());

        MainMenuActivity.showBackButton(true);
        MainMenuActivity.setTitle("Add Company");
        
        return view;
    }
	
	private class ClickButtonListener implements OnClickListener {
		
		@Override
    	public void onClick(View v) {
    		
    		switch (v.getId()) {
    		
    		case R.id.button_add:
    			
    			FragmentTransaction ft = MainMenuActivity.fragmentManager.beginTransaction();

    			BuildLocationFragment fragment = new BuildLocationFragment();

    			ft.setCustomAnimations(R.anim.push_enter, R.anim.push_exit, R.anim.pop_enter, R.anim.pop_exit);
    			ft.replace(R.id.fragmentlayout_main_content, fragment);
    			ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
    			ft.addToBackStack("CustomerContactFragment");
    			ft.commit();			

    			break;
    			
    		}
    	}
	}
	
	
    private void showOffices () {
		
    	listOffice = (ListView) view.findViewById(R.id.listview_verify_address);

    	arrayItems = new ArrayList<VerifyAddressItem>();
    	
		arrayItems.add(new VerifyAddressItem("The Simple Sandwich", "Mayo, greens and tomato"));
		
		listOffice.setOnItemClickListener(new ClickListener());
		
		// setting the nav drawer list adapter
		listOffice.setAdapter(new VerifyAddressListAdapter(this.getActivity().getApplicationContext(), arrayItems));
	}
    
	private class ClickListener implements ListView.OnItemClickListener {
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// display view for selected nav drawer item

			return;		
		}
	}
	
}
