package com.delivery.datamodel.Parameters;

public class LocationInfo {
	
	public Integer				deliveryLocationId;
	public String				locationName;
	public String				dropoffInstruction;
	public String				pickupInstruction;
	public String				deliveryLine1;
	public String				lastLine;
	public double				longitude;
	public double				latitude;
	
	public LocationInfo () {
		
        deliveryLocationId = -1;
        locationName = null;
        dropoffInstruction = null;
        pickupInstruction = null;
        deliveryLine1 = null;
        lastLine = null;
        longitude = 0.0;
        latitude = 0;
	}
}
