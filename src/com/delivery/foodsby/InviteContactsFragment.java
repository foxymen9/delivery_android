package com.delivery.foodsby;

import java.util.ArrayList;

import com.delivery.listview.ContactsItem;
import com.delivery.listview.ContactsListAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class InviteContactsFragment extends Fragment{
	
	private View 			view;
	private ArrayList<ContactsItem> arrayItems;
	private ListView 		listContacts;
	private Button			buttonSendInvitations;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        view = inflater.inflate(R.layout.fragment_invite_contacts, null);

        MainMenuActivity.showBackButton(true);
        MainMenuActivity.setTitle("Invite Contacts");
        
        showOffices ();
        
        buttonSendInvitations = (Button) view.findViewById(R.id.button_send_invitations);
        buttonSendInvitations.setOnClickListener(new ClickButtonListener());

        return view;
    }
	
    private void showOffices () {
		
    	listContacts = (ListView) view.findViewById(R.id.listview_contacts);

    	arrayItems = new ArrayList<ContactsItem>();
    	
		arrayItems.add(new ContactsItem("The Simple Sandwich", "Mayo, greens and tomato", false));
		
		listContacts.setOnItemClickListener(new ClickListener());
		
		// setting the nav drawer list adapter
		listContacts.setAdapter(new ContactsListAdapter(this.getActivity().getApplicationContext(), arrayItems));
	}
    
	private class ClickListener implements ListView.OnItemClickListener {
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// display view for selected nav drawer item
			
			return;		
		}
	}
	
	
	private class ClickButtonListener implements OnClickListener {
		
		@Override
    	public void onClick(View v) {
    		
    	}
	}

}
