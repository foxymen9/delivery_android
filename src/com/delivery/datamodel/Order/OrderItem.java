package com.delivery.datamodel.Order;

import java.util.ArrayList;

public class OrderItem {

	public String							itemName;
	public double							price;
	public Integer							orderItemId;
	public String							specialInstructions;
	public ArrayList<OrderItemModifier>		modifiers;
	
	public OrderItem () {
	
        itemName = null;
        price = 0.0;
        orderItemId = -1;
        specialInstructions = null;
        modifiers = new ArrayList<OrderItemModifier> ();
	}
}
