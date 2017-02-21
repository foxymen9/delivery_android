package com.delivery.datamodel.Checkout;

import java.util.ArrayList;

public class CheckoutOrderDetails {

	public Integer							orderHistoryId;
	public Integer							orderId;
	public Integer							storeId;
	public String							storeName;
	public Integer							userId;
	public String							userName;
	public Integer							deliveryTimeId;
	public String							dropoffTime;
	public Integer							deliveryId;
	public String							cutoffTime;
	public String							orderDate;
	public Integer							orderStatusId;
	public String							orderStatus;
	public String							additionalInstructions;
	public ArrayList<CheckoutOrderItem>		orderItems;
	public String							created;
	public String							recorded;
	public Integer							couponId;
	public String							couponCode;
	public double							itemSubTotal;
	public double							couponSubTotal;
	public double							taxSubTotal;
	public double							storeFee;
	public double							foodsbyFee;
	public double							orderTotal;
	public String							transactionId;
	public Integer							deliveryLocationId;
	public String							locationName;
	public Integer							restaurantId;
	public String							restaurantName;

	public CheckoutOrderDetails () {
		
        orderHistoryId = -1;
        orderId = -1;
        storeId = -1;
        storeName = null;
        userId = -1;
        userName = null;
        deliveryTimeId = -1;
        dropoffTime = null;
        deliveryId = -1;
        cutoffTime = null;
        orderDate = null;
        orderStatusId = -1;
        orderStatus = null;
        additionalInstructions = null;
        orderItems = new ArrayList<CheckoutOrderItem> ();
        created = null;
        recorded = null;
        couponId = -1;
        couponCode = null;
        itemSubTotal = 0.0;
        couponSubTotal = 0.0;
        taxSubTotal = 0.0;
        storeFee = 0.0;
        foodsbyFee = 0.0;
        orderTotal = 0.0;
        transactionId = null;
        deliveryLocationId = -1;
        locationName = null;
        restaurantId = -1;
        restaurantName = null;
	}
}
