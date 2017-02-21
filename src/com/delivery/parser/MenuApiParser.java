package com.delivery.parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.delivery.datamodel.Menu.MenuAnswerItem;
import com.delivery.datamodel.Menu.MenuItem;
import com.delivery.datamodel.Menu.MenuList;
import com.delivery.datamodel.Menu.MenuQuestionItem;
import com.delivery.datamodel.Menu.SubMenu;

public class MenuApiParser {

	private static 		MenuApiParser 	parserInstance = null;

	public static MenuApiParser sharedInstance() {
		
		if (parserInstance == null) {
		
			parserInstance = new MenuApiParser();
		}
		
		return parserInstance;
	}

	public void error (String strResponse) {
	    
	    
	}

	public MenuList  didGetMenu (String strResponse){
	    
		
		try {
			
			JSONObject info = new JSONObject(strResponse);
			if ((strResponse == null) || (strResponse.length() == 0))
		        return null;
		    
		    MenuList menu = new MenuList();

		    menu.locationName = info.getString("LocationName");
		    menu.pickupInstruction = info.getString("PickupInstruction");
		    menu.dayOfWeek = info.getInt("DayOfWeek");
		    menu.dateOfDayForWeek = info.getInt("DateOfDayForWeek");
		    menu.deliveryLocationId = info.getInt("DeliveryLocationId");
		    menu.restaurantName = info.getString("RestaurantName");
		    menu.dropoffTime = info.getString("dropoffTime");
		    menu.cutOffTime = info.getString("CutOffTime");
		    menu.timeZoneInfoId = info.getString("TimeZoneInfoId");

		    JSONArray arraySubMenus = info.getJSONArray("SubMenus");;
		    
		    for (int i = 0 ; i < arraySubMenus.length() ; i ++) {
		        
		        SubMenu subMenu = new SubMenu();
		        
				JSONObject dictSubMenu = arraySubMenus.getJSONObject(i);

		        subMenu.subMenuId = dictSubMenu.getInt("SubMenuId");
		        subMenu.subMenuName = dictSubMenu.getString("SubMenuName");
		        
		        JSONArray arrayMenuItems = dictSubMenu.getJSONArray("Items");
		        
		        for (int j = 0 ; j < arrayMenuItems.length() ; j ++) {
		            
		        	JSONObject dictItem = arrayMenuItems.getJSONObject(j);
		            MenuItem menuItem = new MenuItem ();
		            
		            menuItem.menuItemId = dictItem.getInt("MenuItemId");
		            menuItem.displayText = dictItem.getString("DisplayText");
		            menuItem.price = dictItem.getDouble("Price");
		            menuItem.menuItemDescription = dictItem.getString("Description");
		            menuItem.specialInstructions = dictItem.getString("SpecialInstructions");
		            
		            ///////////////
		            
		            subMenu.menuItems.add(menuItem);
		        }

	            subMenu.subMenuDescription = dictSubMenu.getString("Description");
	            menu.subMenus.add(subMenu);
		    }
		    
		    menu.soldOut = info.getBoolean("SoldOut");
		    menu.orderId = info.getInt("OrderId");
		    menu.orderItemsCount = info.getInt("OrderItemsCount");
		    menu.logoLink = info.getString("LogoLink");
		    menu.hasPastOrder = info.getBoolean("HasPastOrder");
		    
		    return menu;
		    
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;	    
	}

	public MenuItem didGetMenuItem (String strResponse) {
		
	    if ((strResponse == null) || (strResponse.length() == 0))
	        return null;
	    
		try {
			
			JSONObject info = new JSONObject(strResponse);
			
			MenuItem menuItem = new MenuItem();

		    menuItem.menuItemId = info.getInt("MenuItemId");
		    menuItem.displayText = info.getString("DisplayText");
		    menuItem.price = info.getDouble("Price");
		    menuItem.menuItemDescription = info.getString("Description");
	        menuItem.specialInstructions = info.getString("SpecialInstructions");
		    
		    menuItem.questionItems = getQuetiosnItems(info);
		    
		    return menuItem;
			
		} catch (JSONException e) {

			e.printStackTrace();
		}
		
		return null;
	}


	private ArrayList<MenuQuestionItem> getQuetiosnItems( JSONObject info) {
	    
		try {
			
			JSONArray arrayQuestionItems = info.getJSONArray("QuestionItems");
			
			ArrayList<MenuQuestionItem> arrayQuestions = new ArrayList<MenuQuestionItem>();
		    
		    for (int i = 0 ; i < arrayQuestionItems.length() ; i ++) {
		        
		    	JSONObject dictQuestionItem = arrayQuestionItems.getJSONObject(i);
		        MenuQuestionItem menuQuestionItem = new MenuQuestionItem();
		        
		        menuQuestionItem.displayText = dictQuestionItem.getString("DisplayText");
		        menuQuestionItem.questionId = dictQuestionItem.getInt("QuestionId");
		        menuQuestionItem.minimumSelection = dictQuestionItem.getInt("MinimumSelection");
		        menuQuestionItem.maximumSelection = dictQuestionItem.getInt("MaximumSelection");
		        menuQuestionItem.showAsRadio = dictQuestionItem.getBoolean("ShowAsRadio");
		        
		        JSONArray arrayAnswerItems = dictQuestionItem.getJSONArray("AnswerItems");
		        
		        for (int j = 0 ; j < arrayAnswerItems.length() ; j ++) {
		            
		            JSONObject dictAnswerItem = arrayAnswerItems.getJSONObject(j);
		            
		            MenuAnswerItem menuAnswerItem = new MenuAnswerItem ();
		            
		            menuAnswerItem.answerId = dictAnswerItem.getInt("AnswerId");
		            menuAnswerItem.autoSelected = dictAnswerItem.getBoolean("AutoSelected");
		            menuAnswerItem.depth = dictAnswerItem.getInt("Depth");
		            menuAnswerItem.displayText = dictAnswerItem.getString("DisplayText");
		            menuAnswerItem.parentQuestionId = dictAnswerItem.getInt("ParentQuestionId");
		            menuAnswerItem.price = dictAnswerItem.getDouble("Price");
		            menuAnswerItem.selected = dictAnswerItem.getBoolean("Selected");
		            menuAnswerItem.showAsRadio = dictAnswerItem.getBoolean("ShowAsRadio");
		            
		            menuAnswerItem.questionItems = getQuetiosnItems(dictAnswerItem);
		            
		            menuQuestionItem.answerItems.add(menuAnswerItem);
		        }
		        
		        arrayQuestions.add(menuQuestionItem);
		    }
		    
		    return arrayQuestions;
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
	    
		return null;
	}
	
}
