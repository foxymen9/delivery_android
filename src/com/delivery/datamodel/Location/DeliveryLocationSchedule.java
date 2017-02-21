package com.delivery.datamodel.Location;

import java.util.ArrayList;

public class DeliveryLocationSchedule {

	public ArrayList<StoreForLocation>     	storesForLocation;
	public ArrayList<DeliveryDaysThisWeek> 	deliveryDaysThisWeek;
	public String							today;
	public String							locationName;

	public DeliveryLocationSchedule () {
		
		storesForLocation = new ArrayList<StoreForLocation>();
	    deliveryDaysThisWeek = new ArrayList<DeliveryDaysThisWeek>();
	    today = null;
	    locationName = null;
	}
}
