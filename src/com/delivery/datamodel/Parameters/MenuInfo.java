package com.delivery.datamodel.Parameters;

public class MenuInfo {
	
	public Integer				deliveryTimeId;
	public Integer				deliveryLocationId;
	public Integer				storeId;
	public Integer				deliveryId;
//	public Integer				deliveryDayId;
	public Integer				dayOfWeek;
	
	public MenuInfo () {
		
        deliveryTimeId = -1;
        deliveryLocationId = -1;
        storeId = -1;
        deliveryId = -1;
//        deliveryDayId = -1;
        dayOfWeek = 0;
	}
}
