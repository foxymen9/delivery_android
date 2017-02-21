package com.delivery.listview;


public class MainMenuItem {
	
	private String title;
	private int icon;
	
	public MainMenuItem() {}

	public MainMenuItem(String title, int icon) {
		
		this.title = title;
		this.icon = icon;
	}

	public int getIcon() {
		
		return this.icon;
	}

	public void setIcon(int icon) {
		
		this.icon = icon;
	}
	
	public String getTitle() {
		
		return this.title;
	}
	
	public void setTitle(String title) {
		
		this.title = title;
	}	
}
