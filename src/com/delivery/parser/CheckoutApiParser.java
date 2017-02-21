package com.delivery.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.delivery.datamodel.Checkout.CheckoutCard;
import com.delivery.datamodel.Checkout.CheckoutOrderItem;

public class CheckoutApiParser {

	private static 		CheckoutApiParser 	parserInstance = null;

	public static CheckoutApiParser sharedInstance() {
		
		if (parserInstance == null) {
		
			parserInstance = new CheckoutApiParser();
		}
		
		return parserInstance;
	}

	public void error(String strResponse) {
	    
	    
	}

	private CheckoutCard checkout (String strResponse) {
		
		
		try {
			
			JSONObject info = new JSONObject(strResponse);
			
		    CheckoutCard checkoutCard = new CheckoutCard ();
		    
		    checkoutCard.success = info.getBoolean("Success");
		    checkoutCard.message = info.getString("Message");
		    
		    if (checkoutCard.success == false)
		        return checkoutCard;        
		    
		    JSONObject dictOrderDetailes = info.getJSONObject("OrderDetails");
		    
		    checkoutCard.orderDetails.orderHistoryId = dictOrderDetailes.getInt("OrderHistoryId");
		    checkoutCard.orderDetails.orderId = dictOrderDetailes.getInt("OrderId");
		    checkoutCard.orderDetails.storeId = dictOrderDetailes.getInt("StoreId");
		    checkoutCard.orderDetails.storeName = dictOrderDetailes.getString("StoreName");
		    checkoutCard.orderDetails.userId = dictOrderDetailes.getInt("UserId");
		    checkoutCard.orderDetails.userName = dictOrderDetailes.getString("UserName");
		    checkoutCard.orderDetails.deliveryTimeId = dictOrderDetailes.getInt("DeliveryTimeId");
		    checkoutCard.orderDetails.dropoffTime = dictOrderDetailes.getString("DropoffTime");
		    checkoutCard.orderDetails.deliveryId = dictOrderDetailes.getInt("DeliveryId");
		    checkoutCard.orderDetails.cutoffTime = dictOrderDetailes.getString("CutoffTime");
		    checkoutCard.orderDetails.orderDate = dictOrderDetailes.getString("OrderDate");
		    checkoutCard.orderDetails.orderStatusId = dictOrderDetailes.getInt("OrderStatusId");
		    checkoutCard.orderDetails.orderStatus = dictOrderDetailes.getString("OrderStatus");
		    checkoutCard.orderDetails.additionalInstructions = dictOrderDetailes.getString("AdditionalInstructions");
		    
		    JSONArray arrayOrderItems = dictOrderDetailes.getJSONArray("OrderItems");
		    
		    for (int i = 0 ; i < arrayOrderItems.length() ; i ++) {
		        
		        CheckoutOrderItem orderItem = new CheckoutOrderItem ();
		        JSONObject dictOrderItem = arrayOrderItems.getJSONObject(i);
		        
		        orderItem.orderItemHistoryId = dictOrderItem.getInt("OrderItemHistoryId");
		        orderItem.orderHistoryId = dictOrderItem.getInt("OrderHistoryId");
		        orderItem.specialInstructions = dictOrderItem.getString("SpecialInstructions");
		        orderItem.menuItemId = dictOrderItem.getInt("MenuItemId");
		        orderItem.itemName = dictOrderItem.getString("ItemName");
		        orderItem.price = dictOrderItem.getDouble("Price");
		        
		        checkoutCard.orderDetails.orderItems.add(orderItem);
		    }
		    
		    checkoutCard.orderDetails.created = dictOrderDetailes.getString("Created");
		    checkoutCard.orderDetails.recorded = dictOrderDetailes.getString("Recorded");
		    checkoutCard.orderDetails.couponId = dictOrderDetailes.getInt("CouponId");
		    checkoutCard.orderDetails.couponCode = dictOrderDetailes.getString("CouponCode");
		    checkoutCard.orderDetails.itemSubTotal = dictOrderDetailes.getDouble("ItemSubTotal");
		    checkoutCard.orderDetails.couponSubTotal = dictOrderDetailes.getDouble("CouponSubTotal");
		    checkoutCard.orderDetails.taxSubTotal = dictOrderDetailes.getDouble("TaxSubTotal");
		    checkoutCard.orderDetails.storeFee = dictOrderDetailes.getDouble("StoreFee");
		    checkoutCard.orderDetails.foodsbyFee = dictOrderDetailes.getDouble("FoodsbyFee");
		    checkoutCard.orderDetails.orderTotal = dictOrderDetailes.getDouble("OrderTotal");
		    checkoutCard.orderDetails.transactionId = dictOrderDetailes.getString("TransactionId");
		    checkoutCard.orderDetails.deliveryLocationId = dictOrderDetailes.getInt("DeliveryLocationId");
		    checkoutCard.orderDetails.locationName = dictOrderDetailes.getString("LocationName");
		    checkoutCard.orderDetails.restaurantId = dictOrderDetailes.getInt("RestaurantId");
		    checkoutCard.orderDetails.restaurantName = dictOrderDetailes.getString("RestaurantName");
		    
		    JSONObject dictReceiptDetails = info.getJSONObject("ReceiptDetails");
		    
		    checkoutCard.receiptDetails.deliveryLocationId = dictReceiptDetails.getInt("DeliveryLocationId");
		    checkoutCard.receiptDetails.orderId = dictReceiptDetails.getInt("OrderId");
		    
		    checkoutCard.receiptDetails.transactionId = dictReceiptDetails.getString("TransactionId");
		    checkoutCard.receiptDetails.locationName = dictReceiptDetails.getString("LocationName");
		    checkoutCard.receiptDetails.dropoffTime = dictReceiptDetails.getString("DropoffTime");
		    checkoutCard.receiptDetails.orderDate = dictReceiptDetails.getString("OrderDate");
		    checkoutCard.receiptDetails.restaurantName = dictReceiptDetails.getString("RestaurantName");
		    checkoutCard.receiptDetails.pickupInstruction = dictReceiptDetails.getString("PickupInstruction");
		    checkoutCard.receiptDetails.phone = dictReceiptDetails.getString("Phone");
		    checkoutCard.receiptDetails.showSMS = dictReceiptDetails.getBoolean("ShowSMS");
		    checkoutCard.receiptDetails.smsNumber = dictReceiptDetails.getString("SMSNumber");
		    
		    return checkoutCard;
			
		} catch (JSONException e) {

			e.printStackTrace();
		}
	    
		return null;
	}

	public CheckoutCard didCheckoutCard (String strResponse) {
	    
	    return checkout(strResponse);
	}

	public CheckoutCard didCheckoutSavedCard (String strResponse) {

	    return  checkout(strResponse);
	}

	public CheckoutCard didCheckoutFreeMeal (String strResponse) {
	    
	    return checkout(strResponse);
	}

}
