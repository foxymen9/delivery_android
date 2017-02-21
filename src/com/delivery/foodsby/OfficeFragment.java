package com.delivery.foodsby;

import java.util.ArrayList;

import com.delivery.listview.OfficeItem;
import com.delivery.listview.OfficeListAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

public class OfficeFragment extends Fragment{
	
	private View 			view;
	private ArrayList<OfficeItem> arrayItems;
	private ListView 		listOffice;
	private SearchView 		mSearchView;
	private Button			buttonAddCompany;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        view = inflater.inflate(R.layout.fragment_office, null);

        MainMenuActivity.showBackButton(false);
        MainMenuActivity.setTitle("Select Office");
        
        showOffices ();
        
		mSearchView = (SearchView) view.findViewById(R.id.searchview_office);
		mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			
            @Override
            public boolean onQueryTextSubmit(String newText) {

            	return false;
            }

            @Override            
            public boolean onQueryTextChange(String newText) {
            
            	return true;
            }
        });
		
		buttonAddCompany = (Button) view.findViewById(R.id.button_add_company);
		buttonAddCompany.setOnClickListener(new ClickButtonListener());

        return view;
    }
	
    private void showOffices () {
		
    	listOffice = (ListView) view.findViewById(R.id.listview_office);

    	arrayItems = new ArrayList<OfficeItem>();
    	
		arrayItems.add(new OfficeItem("The Simple Sandwich", "Mayo, greens and tomato"));
		
		listOffice.setOnItemClickListener(new ClickListener());
		
		// setting the nav drawer list adapter
		listOffice.setAdapter(new OfficeListAdapter(this.getActivity().getApplicationContext(), arrayItems));
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
			ft.addToBackStack("OfficeFragment");
			ft.commit();			

			return;		
		}
	}
	
	
	private class ClickButtonListener implements OnClickListener {
		
		@Override
    	public void onClick(View v) {
    		
			FragmentTransaction ft = MainMenuActivity.fragmentManager.beginTransaction();

			AddCompanyFragment fragment = new AddCompanyFragment();

			ft.setCustomAnimations(R.anim.push_enter, R.anim.push_exit, R.anim.pop_enter, R.anim.pop_exit);
			ft.replace(R.id.fragmentlayout_main_content, fragment);
			ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
			ft.addToBackStack("OfficeFragment");
			ft.commit();			
    	}
	}

}
