package com.delivery.listview;

public class SelectItemItem {
	
	private String 		displayText;
	private String 		description;
	private double		price;
	
	public SelectItemItem() {}

	public SelectItemItem(double price, String displayText, String description) {
		
		this.price = price;
		this.displayText = displayText;
		this.description = description;
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
	
	public void setDescription(String description) {
		
		this.description = description;
	}
	
	public String getDescription() {
		
		return this.description;
	}	
}