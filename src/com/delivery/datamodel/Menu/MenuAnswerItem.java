package com.delivery.datamodel.Menu;

import java.util.ArrayList;

public class MenuAnswerItem {

	public Integer							answerId;
	public String							displayText;
	public ArrayList<MenuQuestionItem>      questionItems;
	public Integer							 parentQuestionId;
	public boolean							selected;
	public double							price;
	public boolean							showAsRadio;
	public Integer							depth;
	public boolean							autoSelected;
	
	public MenuAnswerItem () {
		
        answerId = -1;
        displayText = null;
        questionItems = new ArrayList<MenuQuestionItem>();
        parentQuestionId = -1;
        selected = false;
        price = 0.0;
        showAsRadio = false;
        depth = -1;
        autoSelected = false;
	}
}
