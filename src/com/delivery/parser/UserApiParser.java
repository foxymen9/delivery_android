package com.delivery.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.util.Log;

import com.delivery.api.ClientApi;
import com.delivery.datamodel.User.UserCard;
import com.delivery.foodsby.R;
import com.delivery.utils.MyApplication;
import com.delivery.utils.Utils;

public class UserApiParser {

	private static 		UserApiParser 	parserInstance = null;

	public static UserApiParser sharedInstance() {
		
		if (parserInstance == null) {
		
			parserInstance = new UserApiParser();
		}
		
		return parserInstance;
	}

	public void error(String strResponse) {
	    
	    
	}

	public void didGetUser (String strResponse) {
		
		try {
			
			JSONObject	info = new JSONObject(strResponse);
			
			Utils.sharedInstance().user.userId = info.getInt("UserId");
		    Utils.sharedInstance().user.inviteCode = info.getString("InviteCode");
		    Utils.sharedInstance().user.userName = info.getString("UserName");
		    Utils.sharedInstance().user.email = info.getString("Email");
		    Utils.sharedInstance().user.firstName = info.getString("FirstName");
		    Utils.sharedInstance().user.lastName = info.getString("LastName");
		    Utils.sharedInstance().user.phone = info.getString("Phone");
		    Utils.sharedInstance().user.officeId = info.getInt("OfficeId");
		    Utils.sharedInstance().user.deliveryLocationId = info.getInt("DeliveryLocationId");	    
		    Utils.sharedInstance().user.created = info.getString("Created");
		    Utils.sharedInstance().user.birthday = info.getString("Birthday");
		    Utils.sharedInstance().user.status = info.getInt("Status");
		    Utils.sharedInstance().user.token = info.getString("Token");
		    Utils.sharedInstance().user.tokenExpire = info.getString("TokenExpire");
		    Utils.sharedInstance().user.smsNotify = info.getBoolean("SMSNotify");
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
	    
	}

	public void didUpdateUser (String strResponse) {
	    
	    didGetUser (strResponse);
	}

	public void didSetContactUserInfo (String strResponse) {
	    
	    didGetUser (strResponse);
	}

	public void didCreateUser (String strResponse) {
	    
	    ClientApi.sharedInstance().getUserToken ();
	}

	public void didLogIn (String strResponse) {
	    
	    ClientApi.sharedInstance().getUserToken ();
	}

	public void didGetUserToken (String strResponse) {
	    
		ClientApi.sharedInstance().getUser();
	}

	public void didResetPassword (String strResponse) {
	    
		AlertDialog.Builder alert = new AlertDialog.Builder(MyApplication.getAppContext());
		alert.setTitle("Foodsby").setMessage("Please check your email for password reset instructions.").setIcon(R.drawable.ic_launcher).setNeutralButton("Ok", null).show();
	}

	public boolean didChangePassword (String strResponse) {

		try {
			JSONObject info = new JSONObject(strResponse);
			
			return info.getBoolean("Success");
			
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return false;	    
	}

	public void didDeleteUserCards (String strResponse) {
	    
		try {
			
			JSONObject info = new JSONObject(strResponse);
			
			boolean bSuccess = info.getBoolean("Success");
		    String  message = info.getString("Message");
		    
		    if (bSuccess == true)
		        Log.d(null, String.format("Message - %s", message));
		    else
		    	Log.d(null, String.format("Failed - %s", message));
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
	}

	public void didGetUserCards (String strResponse) {
	    
		try {
			
			JSONArray info = new JSONArray(strResponse);
			
			if (Utils.sharedInstance().arrayCards.size() > 0)
		    	Utils.sharedInstance().arrayCards.removeAll(null);
		    
		    for (int i = 0 ; i < info.length() ; i ++) {
		        
		        UserCard card = new UserCard ();
		        JSONObject dict = info.getJSONObject(i);
		        
		        card.lastFour = dict.getString("LastFour");
		        card.token = dict.getString("Token");
		        card.cCProfileId = dict.getInt("CCProfileId");
		        
		        Utils.sharedInstance().arrayCards.add(card);
		    }
		    
		    if (info.length() > 0) {
		        
		        UserCard card = Utils.sharedInstance().arrayCards.get(0);
		        
		        Utils.sharedInstance().preferredCard = card.cCProfileId;
		    }
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}	        
	}
	
}
