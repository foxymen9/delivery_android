package com.delivery.datamodel.Menu;

import java.util.ArrayList;

public class MenuList {

	public String					locationName;
	public String					pickupInstruction;
	public Integer					dayOfWeek;
	public Integer					dateOfDayForWeek;
	public Integer					deliveryLocationId;
	public String					restaurantName;
	public String					dropoffTime;
	public String					cutOffTime;
	public String					timeZoneInfoId;
	public ArrayList<SubMenu>		subMenus;
	public boolean	                soldOut;
	public Integer					orderId;
	public Integer					orderItemsCount;
	public String					logoLink;
	public boolean	                hasPastOrder;
	
	public MenuList () {
		
        locationName = null;
        pickupInstruction = null;
        dayOfWeek = -1;
        dateOfDayForWeek = -1;
        deliveryLocationId = -1;
        restaurantName = null;
        dropoffTime = null;
        cutOffTime = null;
        timeZoneInfoId = null;
        subMenus = new ArrayList<SubMenu>();
        soldOut = false;
        orderId = -1;
        orderItemsCount = 0;
        logoLink = null;
        hasPastOrder = false;

	}
}
