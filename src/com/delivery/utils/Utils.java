package com.delivery.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.RingtoneManager;

import com.delivery.datamodel.Checkout.CheckoutOrderDetails;
import com.delivery.datamodel.Checkout.CheckoutReceiptDetails;
import com.delivery.datamodel.FoodsbyException.FoodsbyException;
import com.delivery.datamodel.Location.DeliveryDaysThisWeek;
import com.delivery.datamodel.Location.DeliveryLocation;
import com.delivery.datamodel.Location.DeliveryLocationSchedule;
import com.delivery.datamodel.Location.StoreForLocation;
import com.delivery.datamodel.Menu.MenuItem;
import com.delivery.datamodel.Menu.MenuList;
import com.delivery.datamodel.Menu.SubMenu;
import com.delivery.datamodel.Office.CompanyDetail;
import com.delivery.datamodel.Order.Order;
import com.delivery.datamodel.Order.OrderHistory;
import com.delivery.datamodel.Order.PromoCode;
import com.delivery.datamodel.Parameters.MenuInfo;
import com.delivery.datamodel.User.User;
import com.delivery.datamodel.User.UserCard;
import com.delivery.foodsby.R;

public class Utils{

	private static 		Utils 	utilsInstance = null;

	public Integer											officeId;
	public Integer											deliveryLocationId;
	public Integer											orderId;
	public Integer											menuItemId;
	public Integer											orderItemsCount;
	public Integer											preferredCard;
	public boolean											isProduction;

	public User 				                            user;
	public ArrayList<UserCard>		         			    arrayCards;//cards
	public ArrayList<DeliveryLocation>				        arrayDeliveryLocations;//locations
	public CompanyDetail 					                office;
	public ArrayList<CompanyDetail>                 		arrayAllOffices;
	public DeliveryLocationSchedule       					deliveryLocationSchedule;//schedule
	public MenuInfo					                        identifiers;
	public ArrayList<FoodsbyException>                 		arrayExceptions;
	public ArrayList<OrderHistory>           		      	arrayReorder;
	public MenuList                     				    menu;
	public ArrayList<SubMenu>            				    arraySubMenus;
	public SubMenu                         					subMenu;
	public ArrayList<MenuItem>                 				arrayItems;
	public MenuItem                        					qa;
	public PromoCode                       					promo;
	public Order                          					order;
	public CheckoutOrderDetails            					orderDetails;//order
	public CheckoutReceiptDetails          					receiptDetails;//receipt
	
	public static Utils sharedInstance() {
		
		if (utilsInstance == null) {
		
			utilsInstance = new Utils();
		}
		
		return utilsInstance;
	}
	
	private Utils () {
		
		resetUtils ();
	}
	
	public void resetUtils () {
	    
	    officeId = -1;
	    deliveryLocationId = -1;
	    menuItemId = -1;
	    orderId = -1;
	    orderItemsCount = 0;
	    preferredCard = -1;
	    isProduction = true;
	    
	    user = new User();
	    arrayCards = new ArrayList<UserCard> ();
	    arrayDeliveryLocations = new ArrayList<DeliveryLocation> ();
	    arrayAllOffices = new ArrayList<CompanyDetail> ();
	    office = null;
	    deliveryLocationSchedule = new DeliveryLocationSchedule();
	    identifiers = new MenuInfo ();
	    arrayExceptions = new ArrayList<FoodsbyException> ();
	    arrayReorder = new ArrayList<OrderHistory> ();
	    menu = null;
	    arraySubMenus = null;
	    subMenu = null;
	    arrayItems = null;
	    qa = null;
	    promo = null;
	    order = null;
	    orderDetails = null;
	    receiptDetails = null;
	}

	
	/*************************************************************** SharedPreferences ***************************************************************/
    
    //boolean
    static public void setBooleanValue (String key, boolean value) {
    	
		SharedPreferences    sharedpreferences = MyApplication.getAppContext().getSharedPreferences(key, Context.MODE_PRIVATE);
		Editor editor = sharedpreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
    }
    
    static public boolean getBooleanValue (String key) {
    	
		SharedPreferences    sharedpreferences = MyApplication.getAppContext().getSharedPreferences(key, Context.MODE_PRIVATE);
		return sharedpreferences.getBoolean(key, false);
    }
    
    //String
    static public void setStringValue (String key, String value) {
    	
		SharedPreferences    sharedpreferences = MyApplication.getAppContext().getSharedPreferences(key, Context.MODE_PRIVATE);
		Editor editor = sharedpreferences.edit();
		editor.putString(key, value);
		editor.commit();
    }
    
    static public String getStringValue (String key) {
    	
    	SharedPreferences    sharedpreferences = MyApplication.getAppContext().getSharedPreferences(key, Context.MODE_PRIVATE);
    	return sharedpreferences.getString(key, "");
    }
    
    //Integer
    static public void setIntValue (String key, Integer value) {
    	
		SharedPreferences    sharedpreferences = MyApplication.getAppContext().getSharedPreferences(key, Context.MODE_PRIVATE);
		Editor editor = sharedpreferences.edit();
		editor.putInt(key, value);
		editor.commit();
    }
    
    static public Integer getIntValue (String key) {
    	
    	SharedPreferences    sharedpreferences = MyApplication.getAppContext().getSharedPreferences(key, Context.MODE_PRIVATE);
    	return sharedpreferences.getInt(key, 0);
    }
    
    //Float
   static public void setFloatValue (String key, float value) {
    	
		SharedPreferences    sharedpreferences = MyApplication.getAppContext().getSharedPreferences(key, Context.MODE_PRIVATE);
		Editor editor = sharedpreferences.edit();
		editor.putFloat(key, value);
		editor.commit();
    }
    
    static public float getFloatValue (String key) {
    	
    	SharedPreferences    sharedpreferences = MyApplication.getAppContext().getSharedPreferences(key, Context.MODE_PRIVATE);
    	return sharedpreferences.getFloat(key, 0);
    }

    
	@SuppressLint("SimpleDateFormat") 
	public String convertCurrentTimeZone (String time) throws ParseException {
	    
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss Z");
		Date newDate = format.parse(time);
		
		format = new SimpleDateFormat ("hh:mm a");
		return format.format(newDate);
	}

	public boolean hasMissingInfo () {
	    
	    if ((user.email == null) || (user.email.isEmpty() == true))
	        return true;
	    
	    if ((user.firstName == null) || (user.firstName.isEmpty() == true))
	        return true;
	    
	    if ((user.lastName == null) || (user.lastName.isEmpty() == true))
	        return true;
	    
	    if ((user.phone == null) || (user.phone.isEmpty() == true))
	        return true;
	    
	    return false;
	}

	public boolean hasSavedCards () {
	    
	    if (arrayCards.size() > 0)
	        return true;
	    
	    return false;
	}

	public void standardError (String strResponse) throws JSONException {
		
//		JSONObject error = new JSONObject(strResponse);
//    
//	    String errorDescription = [error objectForKey:@"NSLocalizedDescription"];
//	    
//	    if ([errorDescription rangeOfString:@"canceled"].location != NSNotFound) {
//	        
//	        UIAlertView * alert = [[UIAlertView alloc] initWithTitle: @"An internet connection is required to proceed. Please try again when a connection is present." message: nil delegate:nil  cancelButtonTitle:nil otherButtonTitles:@"OK",nil];
//	        
//	        [alert show];
//	        
//	    } else {
//	        
//	        UIAlertView * alert = [[UIAlertView alloc] initWithTitle: @"Sorry, but we've run into an error on our end. Please try again later." message: nil delegate:nil  cancelButtonTitle:nil otherButtonTitles:@"OK",nil];
//	        
//	        [alert show];
//	    }
	}	    

	public void clearNotifications () {
		
		NotificationManager notificationManager = (NotificationManager)	MyApplication.getAppContext().getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancelAll();		
	}
	
	@SuppressLint("SimpleDateFormat") 
	public void setupNotifications(DeliveryLocationSchedule schedule) throws ParseException {
	    
		clearNotifications ();
	     
	    Integer notificationTime =  getIntValue("NotificationTime");
	    
	    if (notificationTime == 0) {
	        
	        notificationTime = 30;
	        setIntValue("NotificationTime", notificationTime);
	    }

	    for (int i = 0 ; i < schedule.deliveryDaysThisWeek.size() ; i ++) {
	        
	        DeliveryDaysThisWeek deliveryDay = schedule.deliveryDaysThisWeek.get(i);
	        
	        for (int j = 0 ; j < deliveryDay.stores.size() ; j ++) {
	            
	            StoreForLocation store = deliveryDay.stores.get(j);
	            
	            boolean bState =  getBooleanValue(String.format("LocalNotification%ld", store.storeId));
	            
	            if (bState == true)
	                continue;

	            String cutoffString = convertCurrentTimeZone(store.cutOffDateTime);
	            
	    		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss Z");
	    		Date cutoffDate = format.parse(store.cutOffDateTime);
	    		
	    		cutoffDate = new Date (cutoffDate.getTime() - notificationTime * 60 * 1000);
	    		
	    		Date now = new Date();
	    		
	    		if (now.compareTo(cutoffDate) < 0) {
	    		
		            NotificationManager notificationManager = (NotificationManager)	MyApplication.getAppContext().getSystemService(Context.NOTIFICATION_SERVICE);
	
		            notificationManager.notify(i,
		    						new Notification.Builder(MyApplication.getAppContext())
		    								.setSmallIcon(R.drawable.ic_launcher)
		    								.setContentTitle(store.store.restaurantName)
		    								.setTicker(store.store.restaurantName)
		    								.setContentText(String.format("The cutoff time is almost up! Please your order by %s", cutoffString))
		    								.setContentIntent(PendingIntent.getActivity(MyApplication.getAppContext(), i,null, PendingIntent.FLAG_UPDATE_CURRENT))
		    								.setAutoCancel(true)
		    								.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
		    								.build());
	    		}
	    	}
	    }
	}
}

//new AlertDialog.Builder(this) 
//.setTitle("Some Title") 
//.setMessage("some message") 
//.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
//        public void onClick(DialogInterface arg0, int arg1) { 
//                // Some stuff to do when ok got clicked 
//        } 
//}) 
//.setNegativeButton("cancel", new DialogInterface.OnClickListener() { 
//        public void onClick(DialogInterface arg0, int arg1) { 
//                // Some stuff to do when cancel got clicked 
//        } 
//}) 
//.show(); 
