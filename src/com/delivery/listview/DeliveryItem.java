package com.delivery.listview;


public class DeliveryItem {
	
	private String 		deliveryTime;
	private String 		orderTime;
	private String 		logo;
	private boolean		bDescription;
	
	public DeliveryItem() {}

	public DeliveryItem(String logo, String deliveryTime, String orderTime, boolean description) {
		
		this.logo = logo;
		this.deliveryTime = deliveryTime;
		this.orderTime = orderTime;
		this.bDescription = description;
	}

	public void setLogo(String logo) {
		
		this.logo = logo;
	}
	
	public String getLogo() {
		
		return this.logo;
	}

	public void setDeliveryTime(String deliveryTime) {
		
		this.deliveryTime = deliveryTime;
	}
	
	public String getDeliveryTime() {
		
		return this.deliveryTime;
	}
	
	public void setOrderTime(String orderTime) {
		
		this.orderTime = orderTime;
	}
	
	public String getOrderTime() {
		
		return this.orderTime;
	}
	
	public void setDescription(boolean description) {
		
		this.bDescription = description;
	}

	public boolean getDescription() {
		
		return this.bDescription;
	}
}