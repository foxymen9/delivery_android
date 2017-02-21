package com.delivery.datamodel.Menu;

import java.util.ArrayList;

public class MenuQuestionItem {

	public String							displayText;
	public Integer							questionId;
	public ArrayList<MenuAnswerItem>     	answerItems;
	public Integer							minimumSelection;
	public Integer							maximumSelection;
	public boolean							showAsRadio;
	
	public MenuQuestionItem () {
		
        displayText = null;
        questionId = -1;
        answerItems = new ArrayList<MenuAnswerItem>();
        minimumSelection = -1;
        maximumSelection = -1;
        showAsRadio = false;
	}
}
