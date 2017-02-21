package com.delivery.datamodel.Address;

import java.util.ArrayList;

public class DeliveryLocationsAndOffices {

	public Integer									validatedAddressId;
	public ArrayList<DeliveryLocationForAddress>	deliveryLocations;
	public String									deliveryLine1;
	public String									lastLine;
	public ArrayList<DeliveryOffice>				offices;
	public String									latitude;
	public String									longitude;
	
	public DeliveryLocationsAndOffices () {
		
        validatedAddressId = -1;
        deliveryLocations = new ArrayList<DeliveryLocationForAddress> ();
        deliveryLine1 = null;
        lastLine = null;
        offices = new ArrayList<DeliveryOffice> ();
        latitude = null;
        longitude = null;
	}
}
