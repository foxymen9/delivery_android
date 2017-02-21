package com.delivery.datamodel.Checkout;

public class CheckoutReceiptDetails {

	public Integer							deliveryLocationId;
	public Integer							orderId;
	public String							transactionId;
	public String							locationName;
	public String							dropoffTime;
	public String							orderDate;
	public String							restaurantName;
	public String							pickupInstruction;
	public String							 phone;
	public boolean							showSMS;
	public String							smsNumber;
	
	public CheckoutReceiptDetails () {
		
        deliveryLocationId = -1;
        orderId = -1;
        transactionId = null;
        locationName = null;
        dropoffTime = null;
        orderDate = null;
        restaurantName = null;
        pickupInstruction = null;
        phone = null;
        showSMS = false;
        smsNumber = null;
	}
}
