package com.delivery.datamodel.Order;

import java.util.ArrayList;

public class OrderItemModifier {

	public Integer								depth;
	public Integer								orderItemModifierId;
	public ArrayList<OrderItemModifierAnswer>	answers;
	
	public OrderItemModifier () {
	
        depth = -1;
        orderItemModifierId = -1;
        answers = new ArrayList<OrderItemModifierAnswer> ();
	}
}
