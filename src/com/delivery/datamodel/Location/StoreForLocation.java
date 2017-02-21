package com.delivery.datamodel.Location;

import java.util.ArrayList;

public class StoreForLocation {
	
	public Integer							storeId;
	public Store							store;
	public String							cutOffTime;
	public String							cutOffDateTime;
	public ArrayList<DeliveryTimes>     	deliveryTimes;
	public String							logoLink;
	public String							timeZoneInfoId;
	public Integer							maxWeight;
	public ArrayList<String>		     	availableDays;
	public Integer							currentWeight;
	public boolean							inZone;
	public Integer							mondayCount;
	public Integer							tuesdayCount;
	public Integer							wednesdayCount;
	public Integer							thursdayCount;
	public Integer							fridayCount;
	public Integer							saturdayCount;
	public Integer							sundayCount;

	public StoreForLocation () {
		
        storeId = -1;
        store = new Store();
        cutOffTime = null;
        cutOffDateTime = null;
        deliveryTimes = new ArrayList<DeliveryTimes>();
        logoLink = null;
        timeZoneInfoId = null;
        maxWeight = -1;
        availableDays = new ArrayList<String>();
        currentWeight = -1;
        inZone = false;
        mondayCount = -1;
        tuesdayCount = -1;
        wednesdayCount = -1;
        thursdayCount = -1;
        fridayCount = -1;
        saturdayCount = -1;
        sundayCount = -1;
	}
}
