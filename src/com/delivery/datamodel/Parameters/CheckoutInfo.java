package com.delivery.datamodel.Parameters;

public class CheckoutInfo {
	
	public Integer			orderId;
	public String			street;
	public String			city;
	public String			state;
	public String			zip;
	public PaymentInfo 		payment;
	public boolean			saveCard;
	public boolean 			isProduction;
	
	public CheckoutInfo () {
		
        orderId = -1;
        street = null;
        city = null;
        state = null;
        zip = null;
        payment = new PaymentInfo();
        saveCard = false;
        isProduction = false;

	}
}
