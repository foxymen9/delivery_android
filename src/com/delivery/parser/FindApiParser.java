package com.delivery.parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.delivery.datamodel.Office.CompanyDetail;

public class FindApiParser {

	private static 		FindApiParser 	parserInstance = null;

	public static FindApiParser sharedInstance() {
		
		if (parserInstance == null) {
		
			parserInstance = new FindApiParser();
		}
		
		return parserInstance;
	}

	public void error(String strResponse) {
	    
	    
	}

	public ArrayList<String> didSearchOffice (String strResponse) {
	    
		try {
			
			JSONArray info = new JSONArray(strResponse);
			
			ArrayList<String> arrayOffices = new ArrayList<String>();

			for (int i = 0 ; i < info.length() ; i ++) {
		        
		        arrayOffices.add(info.getString(i));
		    }
		    
		    return arrayOffices;
			
		} catch (JSONException e) {

			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<CompanyDetail> didSearchCompanyDetails(String strResponse) {
	 
		try {
			
			JSONArray info = new JSONArray(strResponse);
			
			ArrayList<CompanyDetail> arrayCompanyDetails = new ArrayList<CompanyDetail>();

		    for ( int i = 0 ; i < info.length() ; i ++) {
		        
		        CompanyDetail companyDetail = new CompanyDetail ();
		        
	        	JSONObject	dict = info.getJSONObject(i);

		        companyDetail.count = dict.getInt("Count");
		        companyDetail.officeId = dict.getInt("OfficeId");
		        companyDetail.officeName = dict.getString("OfficeName");
		        companyDetail.validatedAddressId = dict.getInt("ValidatedAddressId");
		        companyDetail.deliveryLocationId = dict.getInt("DeliveryLocationId");
		        companyDetail.deliveryLine1 = dict.getString("DeliveryLine1");
		        companyDetail.lastLine = dict.getString("LastLine");
		        companyDetail.longitude = dict.getString("Longitude");
		        companyDetail.latitude = dict.getString("Latitude");
		        
		        arrayCompanyDetails.add(companyDetail);
		    }
		    
		    return arrayCompanyDetails;

		} catch (JSONException e) {

			e.printStackTrace();
		}
		
		return null;
	}

}
