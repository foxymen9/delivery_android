package com.delivery.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.delivery.datamodel.Office.CompanyDetail;
import com.delivery.utils.Utils;

public class OfficeApiParser {

	private static 		OfficeApiParser 	parserInstance = null;

	public static OfficeApiParser sharedInstance() {
		
		if (parserInstance == null) {
		
			parserInstance = new OfficeApiParser();
		}
		
		return parserInstance;
	}

	public void error (String strResponse) {
	    
	    
	}

	public void didGetAllOffices (String strResponse) {
	    
		try {
			
			JSONArray info = new JSONArray(strResponse);
			
			if (Utils.sharedInstance().arrayAllOffices.size() > 0)
		        Utils.sharedInstance().arrayAllOffices.removeAll(null);
		    
		    for (int i = 0 ; i < info.length() ; i ++) {
		        
		    	JSONObject dict = info.getJSONObject(i);
		        CompanyDetail office = new CompanyDetail ();
		        
		        office.officeId = dict.getInt("OfficeId");	        
		        office.officeName = dict.getString("OfficeName");	        
		        office.validatedAddressId = dict.getInt("ValidatedAddressId");
		        office.deliveryLocationId = dict.getInt("DeliveryLocationId");
		        office.deliveryLine1 = dict.getString("DeliveryLine1");
		        office.lastLine = dict.getString("LastLine");
		        office.longitude = dict.getString("Longitude");
		        office.latitude = dict.getString("Latitude");
		        
		        Utils.sharedInstance().arrayAllOffices.add(office);
		    }
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}

	    return;
	}

	public CompanyDetail didCreateOffice (String strResponse) {
	    
		try {
			
			JSONObject info = new JSONObject(strResponse);
			
			CompanyDetail office = new CompanyDetail ();
		    
		    office.count = info.getInt("Count");
		    office.officeId = info.getInt("OfficeId");
		    office.officeName = info.getString("OfficeName");
		    office.validatedAddressId = info.getInt("ValidatedAddressId");
		    office.deliveryLocationId = info.getInt("DeliveryLocationId");
		    office.deliveryLine1 = info.getString("DeliveryLine1");
		    office.lastLine = info.getString("LastLine");
		    office.longitude = info.getString("Longitude");
		    office.latitude = info.getString("Latitude");
		    
		    return office;
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}

		return null;
	}

	public CompanyDetail didGetOffice (String strResponse) {
	    
		try {
			
			JSONObject info = new JSONObject(strResponse);
			
			CompanyDetail office = new CompanyDetail ();
		    
		    office.count = info.getInt("Count");
		    office.officeId = info.getInt("OfficeId");
		    office.officeName = info.getString("OfficeName");
		    office.validatedAddressId = info.getInt("ValidatedAddressId");
		    office.deliveryLocationId = info.getInt("DeliveryLocationId");
		    office.deliveryLine1 = info.getString("DeliveryLine1");
		    office.lastLine = info.getString("LastLine");
		    office.longitude = info.getString("Longitude");
		    office.latitude = info.getString("Latitude");
		    
		    return office;
			
		} catch (JSONException e) {

			e.printStackTrace();
		}

	    return null;
	}

	public void didInviteOffice (String strResponse) {
	    
	    
	}

}
