package com.delivery.datamodel.Parameters;

public class UserInfo {

	public String			fistName;
	public String   		lastName;
	public String  			phone;
	public boolean  		bSMSNotify;
	public String    		birthday;
	public Integer			deliveryLocationId;
	public Integer		    officeId;
	
	public UserInfo() {
		
        fistName = null;
        lastName = null;
        phone = null;
        bSMSNotify = false;
        birthday = null;
        deliveryLocationId = -1;
        officeId = -1;
	}
}