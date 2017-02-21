package com.delivery.datamodel.Parameters;

import java.util.ArrayList;

public class OrderAddItem {
	
	public Integer							orderId;
	public Integer							menuItemId;
	public String							specialInstructions;
	public ArrayList<SelectedAnswerInfo>	selectedAnswers;
	
	public OrderAddItem () {
		
        orderId = -1;
        menuItemId = -1;
        specialInstructions = null;
        selectedAnswers = new ArrayList<SelectedAnswerInfo>();;        
	}
}
