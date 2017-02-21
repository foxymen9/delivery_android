package com.delivery.datamodel.Checkout;

public class CheckoutOrderItem {
	
	public Integer							orderItemHistoryId;
	public Integer							orderHistoryId;
	public String							specialInstructions;
	public Integer							menuItemId;
	public String							itemName;
	public double							price;
	
	public CheckoutOrderItem () {
		
        orderItemHistoryId = -1;
        orderHistoryId = -1;
        specialInstructions = null;
        menuItemId = -1;
        itemName = null;
        price = 0.0;
	}
}
