package com.delivery.datamodel.Menu;

import java.util.ArrayList;

public class MenuItem {

	public Integer							menuItemId;
	public String							displayText;
	public double							price;
	public String							menuItemDescription;
	public String							specialInstructions;
	public ArrayList<MenuQuestionItem>		questionItems;
	public ArrayList<MenuAnswerItem>		selectedAnswers;

	public MenuItem () {
		
        menuItemId = -1;
        displayText = null;
        price = 0.0;
        menuItemDescription = null;
        specialInstructions = null;
        questionItems = new ArrayList<MenuQuestionItem> ();
        selectedAnswers = new ArrayList<MenuAnswerItem> ();
	}
}
