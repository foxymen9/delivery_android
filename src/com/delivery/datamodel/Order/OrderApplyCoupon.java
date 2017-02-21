package com.delivery.datamodel.Order;

public class OrderApplyCoupon {

	public boolean							success;
	public String							message;
	public Order 							order;

	public OrderApplyCoupon () {
		
        success = false;
        message = null;
        order = new Order();
	}
}
