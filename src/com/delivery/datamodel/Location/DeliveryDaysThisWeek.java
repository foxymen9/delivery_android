package com.delivery.datamodel.Location;

import java.util.ArrayList;

public class DeliveryDaysThisWeek {

	public Integer							dayOfWeek;
	public String							dateOfDayForWeek;
	public ArrayList<StoreForLocation> 		stores;
	
	public DeliveryDaysThisWeek () {
		
        dayOfWeek = -1;
        dateOfDayForWeek = null;
        stores = new ArrayList<StoreForLocation> ();
	}
}
