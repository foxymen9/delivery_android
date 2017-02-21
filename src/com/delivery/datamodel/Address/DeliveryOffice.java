package com.delivery.datamodel.Address;

public class DeliveryOffice {
	
	public Integer									officeId;
	public String									officeName;
	public Integer									deliveryLocationId;
	
	public DeliveryOffice () {
		
        officeId = -1;
        officeName = null;
        deliveryLocationId = -1;
	}
}
