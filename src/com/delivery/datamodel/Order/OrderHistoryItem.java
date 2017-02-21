package com.delivery.datamodel.Order;

import java.util.ArrayList;

public class OrderHistoryItem {

	public String									specialInstructions;
	public String									itemName;
	public ArrayList<OrderHistoryItemModifier>		modifiers;
	
	public OrderHistoryItem () {
		
        specialInstructions = null;
        itemName = null;
        modifiers = new ArrayList<OrderHistoryItemModifier> ();
	}
}
