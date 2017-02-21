package com.delivery.datamodel.Parameters;

public class PaymentInfo {
	
	public String			fistName;
	public String			lastName;
	public String			cardNbr;
	public Integer			expMonth;
	public Integer			expYear;
	public String			cVV2;
	public double      		amount;
	
	public PaymentInfo () {
		
        fistName = null;
        lastName = null;
        cardNbr = null;
        expMonth = 0;
        expYear = 0;
        cVV2 = null;
        amount = 0.0;
	}
}
