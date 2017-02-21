package com.delivery.listview;


public class SelectMenuItem {
	
	private String 		subMenuName;
	private String 		menuDescription;
	
	public SelectMenuItem() {}

	public SelectMenuItem(String subMenuName, String menuDescription) {
		
		this.subMenuName = subMenuName;
		this.menuDescription = menuDescription;
	}

	public void setSubMenuName(String subMenuName) {
		
		this.subMenuName = subMenuName;
	}
	
	public String getSubMenuName() {
		
		return this.subMenuName;
	}
	
	public void setMenuDescription(String menuDescription) {
		
		this.menuDescription = menuDescription;
	}
	
	public String getMenuDescription() {
		
		return this.menuDescription;
	}
}