package com.delivery.listview;

public class CustomizeRadioButtonItem {
	
	private String 		title;
	private boolean		buttonValue;
	
	public CustomizeRadioButtonItem() {}

	public CustomizeRadioButtonItem(String title, boolean buttonValue) {
		
		this.title = title;
		this.buttonValue = buttonValue;
	}

	public boolean getButtonValue() {
		
		return this.buttonValue;
	}

	public void setButtonValue(boolean buttonValue) {
		
		this.buttonValue = buttonValue;
	}
	
	public String getTitle() {
		
		return this.title;
	}
	
	public void setTitle(String title) {
		
		this.title = title;
	}
	
}
