package com.delivery.foodsby;

import java.util.ArrayList;

import com.delivery.listview.OrderItemDetail;
import com.delivery.listview.OrderItemDetailListAdapter;

import android.app.FragmentManager;
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

public class OrderSummaryDetailFragment extends Fragment{
	
	private View 			view;
	private Button			buttonClose;
	private ArrayList<OrderItemDetail> 	arrayItems;
	private ListView 		listOrderDetail;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        view = inflater.inflate(R.layout.fragment_order_summary_item_detail, null);
        
        buttonClose = (Button) view.findViewById(R.id.button_item_detail_close);
        buttonClose.setOnClickListener(new ClickButtonListener());
        
        showOrderItems ();
        
        MainMenuActivity.showBackButton(true);
        MainMenuActivity.setTitle("Order Summary");
        
        return view;
    }
	
    private void showOrderItems () {
		
    	listOrderDetail = (ListView) view.findViewById(R.id.listview_order_item_detail);

    	arrayItems = new ArrayList<OrderItemDetail>();
    	
		arrayItems.add(new OrderItemDetail("greens and tomato"));
		
		listOrderDetail.setOnItemClickListener(new ClickListener());
		
		listOrderDetail.setAdapter(new OrderItemDetailListAdapter(this.getActivity().getApplicationContext(), arrayItems));
	}
    
	private class ClickButtonListener implements OnClickListener {
		
		@Override
    	public void onClick(View v) {
    		
    		switch (v.getId()) {
    		
    		case R.id.button_item_detail_close:

    			MainMenuActivity.fragmentManager.popBackStack("OrderSummaryFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    			break;
    			
    		}
    	}
	}
	
	private class ClickListener implements ListView.OnItemClickListener {
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// display view for selected nav drawer item

			return;		
		}
	}

	
}
