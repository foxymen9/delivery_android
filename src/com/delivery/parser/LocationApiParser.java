package com.delivery.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.delivery.datamodel.Location.DeliveryDaysThisWeek;
import com.delivery.datamodel.Location.DeliveryLocation;
import com.delivery.datamodel.Location.DeliveryTimes;
import com.delivery.datamodel.Location.Store;
import com.delivery.datamodel.Location.StoreForLocation;
import com.delivery.utils.Utils;

public class LocationApiParser {

	private static 		LocationApiParser 	parserInstance = null;

	public static LocationApiParser sharedInstance() {
		
		if (parserInstance == null) {
		
			parserInstance = new LocationApiParser();
		}
		
		return parserInstance;
	}
	
	public void error(String strResponse) {
	    
	    
	}
	
	public void didGetDeliveryLocations (String strResponse) {
		
		try {
			
			JSONArray info = new JSONArray(strResponse);
			
			if (Utils.sharedInstance().arrayDeliveryLocations.size() > 0)
		    	Utils.sharedInstance().arrayDeliveryLocations.removeAll(null);
		    
		    for (int i = 0 ; i < info.length() ; i ++ ) {
		        
		    	JSONObject dict = info.getJSONObject(i);
		        
		        DeliveryLocation location = new DeliveryLocation ();
		        
		        location.deliveryLocationId = dict.getInt("DeliveryLocationId");
		        location.locationName = dict.getString("LocationName");
		        location.dropoffInstruction = dict.getString("DropoffInstruction");
		        location.pickupInstruction = dict.getString("PickupInstruction");
		        location.deliveryLine1 = dict.getString("DeliveryLine1");
		        location.lastLine = dict.getString("LastLine");
		        location.longitude = dict.getString("Longitude");
		        location.latitude = dict.getString("Latitude");
		        
		        Utils.sharedInstance().arrayDeliveryLocations.add(location);
		    }
			
		} catch (JSONException e) {

			e.printStackTrace();
		}
	}
	
	public DeliveryLocation didGetSpecificDeliveryLocation (String strResponse) {
	    
		try {
			
			JSONObject info = new JSONObject(strResponse);
			
			DeliveryLocation location = new DeliveryLocation ();
		    
		    location.deliveryLocationId = info.getInt("DeliveryLocationId");
		    location.locationName = info.getString("LocationName");
		    location.dropoffInstruction = info.getString("DropoffInstruction");
		    location.pickupInstruction = info.getString("PickupInstruction");
		    location.deliveryLine1 = info.getString("DeliveryLine1");
		    location.lastLine = info.getString("LastLine");
		    location.longitude = info.getString("Longitude");
		    location.latitude = info.getString("Latitude");
		    
		    return location;
			
		} catch (JSONException e) {

			e.printStackTrace();
		}
		
		return null;
	}
	
	public void didGetLocationSchedule (String strResponse) {
		
		try {
			
			JSONObject info = new JSONObject(strResponse);
			
		    if (Utils.sharedInstance().deliveryLocationSchedule.storesForLocation.size() > 0)
		    	Utils.sharedInstance().deliveryLocationSchedule.storesForLocation.removeAll(null);
		    
		    if (Utils.sharedInstance().deliveryLocationSchedule.deliveryDaysThisWeek.size() > 0)
		    	Utils.sharedInstance().deliveryLocationSchedule.deliveryDaysThisWeek.removeAll(null);
		    
		    JSONArray arrayStoresForLocation = info.getJSONArray("StoresForLocation");
		    JSONArray arrayDeliveryDaysThisWeek = info.getJSONArray("DeliveryDaysThisWeek");
		    Utils.sharedInstance().deliveryLocationSchedule.today = info.getString("Today");
		    Utils.sharedInstance().deliveryLocationSchedule.locationName = info.getString("LocationName");
		    
		    for (int i = 0 ; i < arrayStoresForLocation.length() ; i ++) {
		        
		    	JSONObject dict = arrayStoresForLocation.getJSONObject(i);
		        StoreForLocation storeForLocation = new StoreForLocation ();
		
		        storeForLocation.storeId = dict.getInt("StoreId");
		        
		        //Store
		        JSONObject dictStore = dict.getJSONObject("Store");
		        JSONObject dictRestaurant = dictStore.getJSONObject("Restaurant");
		        
		        storeForLocation.store.restaurantName = dictRestaurant.getString("RestaurantName");
		        storeForLocation.store.storeName = dictStore.getString("StoreName");
		        
		        storeForLocation.cutOffTime = dict.getString("CutOffTime");
		        storeForLocation.cutOffDateTime = dict.getString("CutOffDateTime");
		        
		        //DeliveryTimes	        
		        JSONArray arrayDeliveryTimes = dict.getJSONArray("DeliveryTimes");
		            
	            for (int j = 0 ; j < arrayDeliveryTimes.length() ; j ++) {
	                
	            	JSONObject dictDeliveryTimes = arrayDeliveryTimes.getJSONObject(j);
	                
	                DeliveryTimes deliveryTimes = new DeliveryTimes ();
	                
	                deliveryTimes.dropoffTime = dictDeliveryTimes.getString("DropoffTime");
	                deliveryTimes.dropoffDateTime = dictDeliveryTimes.getString("DropoffDateTime");
	                deliveryTimes.deliveryTimeId = dictDeliveryTimes.getInt("DeliveryTimeId");
	                deliveryTimes.deliveryLocationId = dictDeliveryTimes.getInt("DeliveryLocationId");
	                deliveryTimes.deliveryId = dictDeliveryTimes.getInt("DeliveryId");
	                deliveryTimes.deliveryName = dictDeliveryTimes.getString("DeliveryName");
	                deliveryTimes.isPending = dictDeliveryTimes.getBoolean("IsPending");
	                deliveryTimes.inZone = dictDeliveryTimes.getBoolean("InZone");
	                
	                storeForLocation.deliveryTimes.add(deliveryTimes);
	            }
		            
		        storeForLocation.logoLink = dict.getString("LogoLink");
		        storeForLocation.timeZoneInfoId = dict.getString("TimeZoneInfoId");
		        storeForLocation.maxWeight = dict.getInt("MaxWeight");
		        
		        JSONArray arrayAvailableDays = dict.getJSONArray("AvailableDays");
	            
	            for (int j = 0 ; j < arrayAvailableDays.length() ; j ++)
	                storeForLocation.availableDays.add(arrayAvailableDays.getString(j));
	            
		        storeForLocation.currentWeight = dict.getInt("CurrentWeight");
		        storeForLocation.inZone = dict.getBoolean("InZone");
		        storeForLocation.mondayCount = dict.getInt("MondayCount");
		        storeForLocation.tuesdayCount = dict.getInt("TuesdayCount");
		        storeForLocation.wednesdayCount = dict.getInt("WednesdayCount");
		        storeForLocation.thursdayCount = dict.getInt("ThursdayCount");
		        storeForLocation.fridayCount = dict.getInt("FridayCount");
		        storeForLocation.saturdayCount = dict.getInt("SaturdayCount");
		        storeForLocation.sundayCount = dict.getInt("SundayCount");
		        
		        Utils.sharedInstance().deliveryLocationSchedule.storesForLocation.add(storeForLocation);
		    }
		    
		    for (int i = 0 ; i < arrayDeliveryDaysThisWeek.length() ; i ++) {
		
		        DeliveryDaysThisWeek deliveryDayThisWeek = new DeliveryDaysThisWeek ();
		
		        JSONObject dictMain = arrayDeliveryDaysThisWeek.getJSONObject(i);
		        deliveryDayThisWeek.dayOfWeek = dictMain.getInt("DayOfWeek");
		        deliveryDayThisWeek.dateOfDayForWeek = dictMain.getString("DateOfDayForWeek");
		        
		        JSONArray arrayStores = dictMain.getJSONArray("Stores");
		        
		        for (int k = 0 ; k < arrayStores.length() ; k ++) {
		            
		        	JSONObject	dict = arrayStores.getJSONObject(k);
		            StoreForLocation storeForLocation = new StoreForLocation ();
		            
		            storeForLocation.storeId = dict.getInt("StoreId");
		            
		            //Store
		            storeForLocation.store = new Store();
		            JSONObject	dictStore = dict.getJSONObject("Store");
		            JSONObject	dictRestaurant = dictStore.getJSONObject("Restaurant");
		            
		            storeForLocation.store.restaurantName = dictRestaurant.getString("RestaurantName");
		
		            storeForLocation.store.storeName = dictStore.getString("StoreName");
		            
		            storeForLocation.cutOffTime = dict.getString("CutOffTime");
		            storeForLocation.cutOffDateTime = dict.getString("CutOffDateTime");
		            
		            //DeliveryTimes
		            JSONArray arrayDeliveryTimes = dict.getJSONArray("DeliveryTimes");
		                
	                for (int j = 0 ; j < arrayDeliveryTimes.length() ; j ++) {
	                    
	                	JSONObject	dictDeliveryTimes = arrayDeliveryTimes.getJSONObject(j);
	                	
	                    DeliveryTimes deliveryTimes = new DeliveryTimes ();
	                    
	                    deliveryTimes.dropoffTime = dictDeliveryTimes.getString("DropoffTime");
	                    deliveryTimes.dropoffDateTime = dictDeliveryTimes.getString("DropoffDateTime");
	                    deliveryTimes.deliveryTimeId = dictDeliveryTimes.getInt("DeliveryTimeId");
	                    deliveryTimes.deliveryLocationId = dictDeliveryTimes.getInt("DeliveryLocationId");
	                    deliveryTimes.deliveryId = dictDeliveryTimes.getInt("DeliveryId");
	                    deliveryTimes.deliveryName = dictDeliveryTimes.getString("DeliveryName");
	                    deliveryTimes.isPending = dictDeliveryTimes.getBoolean("IsPending");
	                    deliveryTimes.inZone = dictDeliveryTimes.getBoolean("InZone");
	                    
	                    storeForLocation.deliveryTimes.add(deliveryTimes);
	                }
		            
		            storeForLocation.logoLink = dict.getString("LogoLink");
		            storeForLocation.timeZoneInfoId = dict.getString("TimeZoneInfoId");
		            storeForLocation.maxWeight = dict.getInt("MaxWeight");
		            
		            JSONArray arrayAvailableDays = dict.getJSONArray("AvailableDays");
		            
	                for (int j = 0 ; j < arrayAvailableDays.length() ; j ++) 
	                    storeForLocation.availableDays.add(arrayAvailableDays.getString(j));
	                
		            storeForLocation.currentWeight = dict.getInt("CurrentWeight");
		            storeForLocation.inZone = dict.getBoolean("InZone");
		            storeForLocation.mondayCount = dict.getInt("MondayCount");
		            storeForLocation.tuesdayCount = dict.getInt("TuesdayCount");
		            storeForLocation.wednesdayCount = dict.getInt("WednesdayCount");
		            storeForLocation.thursdayCount = dict.getInt("ThursdayCount");
		            storeForLocation.fridayCount = dict.getInt("FridayCount");
		            storeForLocation.saturdayCount = dict.getInt("SaturdayCount");
		            storeForLocation.sundayCount = dict.getInt("SundayCount");
		            
		            deliveryDayThisWeek.stores.add(storeForLocation);	            
		        }
		        
		        Utils.sharedInstance().deliveryLocationSchedule.deliveryDaysThisWeek.add(deliveryDayThisWeek);
		    }
			
		} catch (JSONException e) {

			e.printStackTrace();
		}
	}
	
}
