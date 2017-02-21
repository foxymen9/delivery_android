package com.delivery.listview;


public class ContactsItem {
	
	private String 		name;
	private String 		email;
	private boolean		buttonValue;

	public ContactsItem () {}

	public ContactsItem (String name, String email, boolean buttonValue) {
		
		this.name = name;
		this.email = email;
		this.buttonValue = buttonValue;
	}

	public void setName (String name) {
		
		this.name = name;
	}
	
	public String getName () {
		
		return this.name;
	}
	
	public void setEmail (String email) {
		
		this.email = email;
	}
	
	public String getEmail () {
		
		return this.email;
	}
	
	public boolean getButtonValue() {
		
		return this.buttonValue;
	}

	public void setButtonValue(boolean buttonValue) {
		
		this.buttonValue = buttonValue;
	}

}