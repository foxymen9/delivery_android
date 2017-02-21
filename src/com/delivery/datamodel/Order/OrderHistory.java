package com.delivery.datamodel.Order;

import java.util.ArrayList;

public class OrderHistory {

	public Integer							orderHistoryId;
	public String							additionalInstructions;
	public ArrayList<OrderHistoryItem>		orderItems;
	
	public OrderHistory () {
	
        orderHistoryId = -1;
        additionalInstructions = null;
        orderItems = new ArrayList<OrderHistoryItem> ();
	}
}
