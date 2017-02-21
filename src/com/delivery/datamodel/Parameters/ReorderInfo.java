package com.delivery.datamodel.Parameters;

public class ReorderInfo {
	
	public Integer				orderHistoryId;
	public Integer				dayOfWeek;
	public Integer				storeId;
	public Integer				deliveryTimeId;
	public Integer				deliveryLocationId;
	
	public ReorderInfo () {
		
        orderHistoryId = -1;
        dayOfWeek = -1;
        storeId = -1;
        deliveryTimeId = -1;
        deliveryLocationId = -1;	
	}
}
