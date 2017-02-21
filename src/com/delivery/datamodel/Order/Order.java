package com.delivery.datamodel.Order;

import java.util.ArrayList;

public class Order {

	public Integer							orderId;
	public Integer							storeId;
	public Integer							userId;
	public Integer							deliveryTimeId;
	public String							orderDate;
	public Integer							orderStatus;
	public String							additionalInstructions;
	public ArrayList<OrderItem>				orderItems;
	public String							created;
	public double							itemSubTotal;
	public double							couponSubTotal;
	public double							taxSubTotal;
	public double							orderTotal;
	public String							transactionId;
	public Integer							deliveryLocationId;
	public double							foodsbyFee;
	
	public Order () {
		
        orderId = -1;
        storeId = -1;
        userId = -1;
        deliveryTimeId = -1;
        orderDate = null;
        orderStatus = -1;
        additionalInstructions = null;
        orderItems = new ArrayList<OrderItem> ();
        created = null;
        itemSubTotal = 0.0;
        couponSubTotal = 0.0;
        taxSubTotal = 0.0;
        orderTotal = 0.0;
        transactionId = null;
        deliveryLocationId = -1;
        foodsbyFee = 0.0;
	}
}
