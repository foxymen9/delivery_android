package com.delivery.listview;


public class VerifyAddressItem {
	
	private String 		deliveryLine;
	private String 		lastLine;
	
	public VerifyAddressItem() {}

	public VerifyAddressItem(String deliveryLine, String lastLine) {
		
		this.deliveryLine = deliveryLine;
		this.lastLine = lastLine;
	}

	public void setDeliveryLine(String deliveryLine) {
		
		this.deliveryLine = deliveryLine;
	}
	
	public String getDeliveryLine() {
		
		return this.deliveryLine;
	}
	
	public void setLastLine(String lastLine) {
		
		this.lastLine = lastLine;
	}
	
	public String getLastLine() {
		
		return this.lastLine;
	}
}