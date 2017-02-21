package com.delivery.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.delivery.datamodel.Order.Order;
import com.delivery.datamodel.Order.OrderApplyCoupon;
import com.delivery.datamodel.Order.OrderHistory;
import com.delivery.datamodel.Order.OrderHistoryItem;
import com.delivery.datamodel.Order.OrderHistoryItemModifier;
import com.delivery.datamodel.Order.OrderItem;
import com.delivery.datamodel.Order.OrderItemModifier;
import com.delivery.datamodel.Order.OrderItemModifierAnswer;
import com.delivery.utils.Utils;

public class OrderApiParser {

	private static 		OrderApiParser 	parserInstance = null;

	public static OrderApiParser sharedInstance() {
		
		if (parserInstance == null) {
		
			parserInstance = new OrderApiParser();
		}
		
		return parserInstance;
	}

	
	public void error (String strResponse) {
	    
	    
	}

	public Order didGetOrder (String strResponse) {
	    
		try {
			
			JSONObject	info = new JSONObject(strResponse);
			
			if (info.length() == 0)
				return null;
			
		    Order order = new Order ();
		    
		    order.orderId = info.getInt("OrderId");
		    order.storeId = info.getInt("StoreId");
		    order.userId = info.getInt("UserId");
		    order.deliveryTimeId = info.getInt("DeliveryTimeId");
		    order.orderDate = info.getString("OrderDate");
		    order.orderStatus = info.getInt("OrderStatus");
		    order.orderDate = info.getString("AdditionalInstructions");
		    
		    JSONArray arrayOrderItems = info.getJSONArray("OrderItems");;

		    for (int i = 0 ; i < arrayOrderItems.length() ; i ++) {
		        
		        OrderItem orderItem = new OrderItem ();
		        JSONObject dictOrderItemInfo = arrayOrderItems.getJSONObject(i);
		        
		        orderItem.itemName = dictOrderItemInfo.getString("ItemName");
		        orderItem.price = dictOrderItemInfo.getDouble("Price");
		        orderItem.orderItemId = dictOrderItemInfo.getInt("OrderItemId");
		        orderItem.specialInstructions = dictOrderItemInfo.getString("SpecialInstructions");
		        
		        JSONArray arrayModifier = dictOrderItemInfo.getJSONArray("Modifiers");
		        
		        for (int j = 0 ; j < arrayModifier.length() ; j ++) {
		            
		            OrderItemModifier modifier = new OrderItemModifier ();
		            JSONObject dictModifier = arrayModifier.getJSONObject(j);
		            
		            modifier.depth = dictModifier.getInt("Depth");
		            modifier.orderItemModifierId = dictModifier.getInt("OrderItemModifierId");

		            JSONArray arrayAnswers = dictModifier.getJSONArray("Answers");
		            
		            for (int k = 0 ; k < arrayAnswers.length() ; k ++) {
		                
		                OrderItemModifierAnswer answer = new OrderItemModifierAnswer ();
		                JSONObject dictAnswer = arrayAnswers.getJSONObject(k);
		                
		                answer.answerId = dictAnswer.getInt("AnswerId");
		                answer.itemName = dictAnswer.getString("ItemName");
		                answer.price = dictAnswer.getDouble("Price");
		                
		                modifier.answers.add(answer);
		            }
		            
		            orderItem.modifiers.add(modifier);
		        }
		        
		        order.orderItems.add(orderItem);
		    }
		    
		    order.created = info.getString("Created");
		    order.itemSubTotal = info.getDouble("ItemSubTotal");
		    order.couponSubTotal = info.getDouble("CouponSubTotal");
		    order.taxSubTotal = info.getDouble("TaxSubTotal");
		    order.orderTotal = info.getDouble("OrderTotal");
		    order.transactionId = info.getString("TransactionId");
		    order.deliveryLocationId = info.getInt("DeliveryLocationId");
		    order.foodsbyFee = info.getDouble("FoodsbyFee");
		    
		    return order;
		    
		} catch (JSONException e) {

			e.printStackTrace();
		}
		
		return null;
	}

	public void didReorder (String strResponse) {
	    
	    
	}

	public void didGetOrderHistory (String strResponse) {

		try {
			
			JSONArray info = new JSONArray(strResponse);
			
			if (Utils.sharedInstance().arrayReorder.size() > 0)
		    	Utils.sharedInstance().arrayReorder.removeAll(null);
		    
		    for (int i = 0 ; i < info.length() ; i ++) {
		        
		        OrderHistory orderHistory = new OrderHistory ();
		        JSONObject dictOrderHistory = info.getJSONObject(i);
		        
		        orderHistory.orderHistoryId = dictOrderHistory.getInt("OrderHistoryId");
		        orderHistory.additionalInstructions = dictOrderHistory.getString("AdditionalInstructions");
		        
		        JSONArray arrayOrderItems = dictOrderHistory.getJSONArray("OrderItems");

		        for (int j = 0 ; j < arrayOrderItems.length() ; j ++) {
		            
		            OrderHistoryItem orderItem = new OrderHistoryItem ();
		            JSONObject dictOrderItemInfo = arrayOrderItems.getJSONObject(j);
		            
		            orderItem.itemName = dictOrderItemInfo.getString("ItemName");
		            orderItem.specialInstructions = dictOrderItemInfo.getString("SpecialInstructions");
		            
		            JSONArray arrayModifier = dictOrderItemInfo.getJSONArray("Modifiers");
		            
		            for (int k = 0 ; k < arrayModifier.length() ; k ++) {
		                
		                OrderHistoryItemModifier modifier = new OrderHistoryItemModifier ();
		                JSONObject dictModifier = arrayOrderItems.getJSONObject(k);
		                
		                modifier.depth = dictModifier.getInt("Depth");
		                modifier.questionName = dictModifier.getString("QuestionName");
		                
		                JSONArray arrayAnswers = dictModifier.getJSONArray("Answers");
		                
		                for (int m = 0 ; m < arrayAnswers.length() ; m ++) {
		                    
		                	JSONObject dictAnswer = arrayAnswers.getJSONObject(m);
		                    modifier.answers.add(dictAnswer.getString("ItemName"));
		                }
		                
		                orderItem.modifiers.add(modifier);
		            }
		            
		            orderHistory.orderItems.add(orderItem);
		        }
		        
		        Utils.sharedInstance().arrayReorder.add(orderHistory);
		    }
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
	    
	    return;
	}

	public void didAddOrderItem (String strResponse) {
	    
	    
	}

	public void didRemoveOrderItem (String strResponse) {
	    
	    
	}

	public OrderApplyCoupon didApplyCouponToOrder (String strResponse) {
	    
		try {
			
			JSONObject	info = new JSONObject(strResponse);
			
			OrderApplyCoupon orderApplyCoupon = new OrderApplyCoupon ();

		    orderApplyCoupon.success = info.getBoolean("Success");
		    orderApplyCoupon.message = info.getString("Message");
		    
		    if (orderApplyCoupon.success == false)
		        return orderApplyCoupon;
		    
		    JSONObject dictOrder = info.getJSONObject("Order");
		    
		    orderApplyCoupon.order.orderId = dictOrder.getInt("OrderId");
		    orderApplyCoupon.order.storeId = dictOrder.getInt("StoreId");
		    orderApplyCoupon.order.userId = dictOrder.getInt("UserId");
		    orderApplyCoupon.order.deliveryTimeId = dictOrder.getInt("DeliveryTimeId");
		    orderApplyCoupon.order.orderDate = dictOrder.getString("OrderDate");
		    orderApplyCoupon.order.orderStatus = dictOrder.getInt("OrderStatus");
		    orderApplyCoupon.order.orderDate = dictOrder.getString("AdditionalInstructions");
		    
		    JSONArray arrayOrderItems = dictOrder.getJSONArray("OrderItems");
		    
		    for (int i = 0 ; i < arrayOrderItems.length() ; i ++) {
		        
		        OrderItem orderItem = new OrderItem ();
		        JSONObject dictOrderItemInfo = arrayOrderItems.getJSONObject(i);
		        
		        orderItem.itemName = dictOrderItemInfo.getString("ItemName");
		        orderItem.price = dictOrderItemInfo.getDouble("Price");
		        orderItem.orderItemId = dictOrderItemInfo.getInt("OrderItemId");
		        orderItem.specialInstructions = dictOrderItemInfo.getString("SpecialInstructions");
		        
		        JSONArray arrayModifier = dictOrderItemInfo.getJSONArray("Modifiers");
		        
		        for (int j = 0 ; j < arrayModifier.length() ; j ++) {
		            
		            OrderItemModifier modifier = new OrderItemModifier ();
		            JSONObject dictModifier = arrayOrderItems.getJSONObject(j);
		            
		            modifier.depth = dictModifier.getInt("Depth");
		            modifier.orderItemModifierId = dictModifier.getInt("OrderItemModifierId");
		            
		            JSONArray arrayAnswers = dictModifier.getJSONArray("Answers");
		            
		            for (int k = 0 ; k < arrayAnswers.length() ; k ++) {
		                
		                OrderItemModifierAnswer answer = new OrderItemModifierAnswer ();
		                JSONObject dictAnswer = arrayAnswers.getJSONObject(k);
		                
		                answer.answerId = dictAnswer.getInt("AnswerId");
		                answer.itemName = dictAnswer.getString("ItemName");
		                answer.price = dictAnswer.getDouble("Price");
		                
		                modifier.answers.add(answer);
		            }
		            
		            orderItem.modifiers.add(modifier);
		        }
		        
		        orderApplyCoupon.order.orderItems.add(orderItem);
		    }
		    
		    orderApplyCoupon.order.created = dictOrder.getString("Created");
		    orderApplyCoupon.order.itemSubTotal = dictOrder.getDouble("ItemSubTotal");
		    orderApplyCoupon.order.couponSubTotal = dictOrder.getDouble("CouponSubTotal");
		    orderApplyCoupon.order.taxSubTotal = dictOrder.getDouble("TaxSubTotal");
		    orderApplyCoupon.order.orderTotal = dictOrder.getDouble("OrderTotal");
		    orderApplyCoupon.order.transactionId = dictOrder.getString("TransactionId");
		    orderApplyCoupon.order.deliveryLocationId = dictOrder.getInt("DeliveryLocationId");
		    orderApplyCoupon.order.foodsbyFee = dictOrder.getDouble("FoodsbyFee");
		    
		    return orderApplyCoupon;
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}

	    return null;
	}

}
