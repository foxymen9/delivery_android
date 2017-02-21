package com.delivery.datamodel.Location;

public class DeliveryTimes {

	public String							dropoffTime;
	public String							dropoffDateTime;
	public Integer							deliveryTimeId;
	public Integer							deliveryLocationId;
	public Integer							deliveryId;
	public String							deliveryName;
	public boolean							isPending;
	public boolean							inZone;
	
	public DeliveryTimes () {
		
        dropoffTime = null;
        dropoffDateTime = null;
        deliveryTimeId = -1;
        deliveryLocationId = -1;
        deliveryId = -1;
        deliveryName = null;
        isPending = false;
        inZone = false;
	}
}
