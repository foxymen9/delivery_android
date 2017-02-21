package com.delivery.datamodel.Location;

public class DeliveryLocation {

	public Integer							deliveryLocationId;
	public String							locationName;
	public String							dropoffInstruction;
	public String							pickupInstruction;
	public String							deliveryLine1;
	public String							lastLine;
	public String							longitude;
	public String							latitude;
	
	public DeliveryLocation () {
		
        deliveryLocationId = -1;
        locationName = null;
        dropoffInstruction = null;
        pickupInstruction = null;
        deliveryLine1 = null;
        lastLine = null;
        longitude = null;
        latitude = null;
	}
}
