package com.delivery.datamodel.Order;

import java.util.ArrayList;

public class OrderHistoryItemModifier {

	public String								questionName;
	public ArrayList<String>					answers;
	public Integer								depth;
	
	public OrderHistoryItemModifier () {
		
        questionName = null;
        answers = new ArrayList<String> ();
        depth = -1;
	}
}
