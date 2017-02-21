package com.delivery.datamodel.Checkout;

public class CheckoutCard {

	public boolean							success;
	public String							message;
	public CheckoutOrderDetails		        orderDetails;
	public CheckoutReceiptDetails			receiptDetails;
	
	public CheckoutCard () {
	
        success = false;
        message = null;
        orderDetails = new CheckoutOrderDetails ();
        receiptDetails = new CheckoutReceiptDetails ();
	}
}
