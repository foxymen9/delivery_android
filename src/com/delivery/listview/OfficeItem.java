package com.delivery.listview;


public class OfficeItem {
	
	private String 		officeName;
	private String 		deliveryLine;
	
	public OfficeItem () {}

	public OfficeItem (String officeName, String deliveryLine) {
		
		this.officeName = officeName;
		this.deliveryLine = deliveryLine;
	}

	public void setOfficeName (String officeName) {
		
		this.officeName = officeName;
	}
	
	public String getOfficeName () {
		
		return this.officeName;
	}
	
	public void setDeliveryLine (String deliveryLine) {
		
		this.deliveryLine = deliveryLine;
	}
	
	public String getDeliveryLine () {
		
		return this.deliveryLine;
	}
}