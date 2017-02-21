package com.delivery.listview;

public class OrderSummaryItem {
	
	private String 		displayText;
	private double		price = 0.0;
	private	int			type;
	
	public OrderSummaryItem() {}

	public OrderSummaryItem(double price, String displayText) {
		
		this.price = price;
		this.displayText = displayText;
		this.type = 0;
	}

	public OrderSummaryItem(String displayText) {
		
		this.displayText = displayText;
		this.type = 1;
	}

	public void setPrice (double price) {
		
		this.price = price;
	}
	
	public double getPrice () {
		
		return this.price;
	}
	
	public void setDisplayText(String displayText) {
		
		this.displayText = displayText;
	}
	
	public String getDisplayText() {
		
		return this.displayText;
	}
	
	public void setType (int type) {
		
		this.type = type;
	}
	
	public int getType () {
		
		return this.type;
	}
}