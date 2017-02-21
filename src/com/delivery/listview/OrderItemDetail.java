package com.delivery.listview;

public class OrderItemDetail {
	
	private String 		displayText;
	
	public OrderItemDetail() {}

	public OrderItemDetail(String displayText) {
		
		this.displayText = displayText;
	}

	public void setDisplayText(String displayText) {
		
		this.displayText = displayText;
	}
	
	public String getDisplayText() {
		
		return this.displayText;
	}
}