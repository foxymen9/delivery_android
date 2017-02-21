package com.delivery.datamodel.Address;

public class DeliveryLocationForAddress {

	public Integer									deliveryLocationId;
	public String									locationName;
	public boolean									active;
	
	public DeliveryLocationForAddress () {
		
        deliveryLocationId = -1;
        locationName = null;
        active = false;
	}
}
