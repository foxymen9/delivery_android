package com.delivery.datamodel.Menu;

import java.util.ArrayList;

public class SubMenu {

	public Integer					subMenuId;
	public String					subMenuName;
	public ArrayList<MenuItem>		menuItems;
	public String					subMenuDescription;
	
	public SubMenu() {
		
		subMenuId = -1;
		subMenuName = null;
		menuItems = new ArrayList<MenuItem>();
		subMenuDescription = null;
	}
}
