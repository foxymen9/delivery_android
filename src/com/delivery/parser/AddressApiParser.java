package com.delivery.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.delivery.datamodel.Address.CandidateAddress;
import com.delivery.datamodel.Address.DeliveryLocationForAddress;
import com.delivery.datamodel.Address.DeliveryLocationsAndOffices;
import com.delivery.datamodel.Address.DeliveryOffice;
import com.delivery.datamodel.Address.ValidatedAddress;

public class AddressApiParser {

	private static 		AddressApiParser 	parserInstance = null;

	public static AddressApiParser sharedInstance() {
		
		if (parserInstance == null) {
		
			parserInstance = new AddressApiParser();
		}
		
		return parserInstance;
	}
	
	public void error (String strResponse) {
	    
	    
	}

	public DeliveryLocationsAndOffices didGetDeliveryAddress (String strResponse) {
	    
		try {
			
			JSONObject info = new JSONObject(strResponse);
			
		    DeliveryLocationsAndOffices deliveryLocationsAndOffices = new DeliveryLocationsAndOffices ();
		    
		    deliveryLocationsAndOffices.validatedAddressId = info.getInt("ValidatedAddressId");
		    
		    JSONArray arrayDeliveryLocations = info.getJSONArray("DeliveryLocations");
		    
		    for (int i = 0 ; i < arrayDeliveryLocations.length() ; i ++) {
		        
		    	JSONObject dictDeliveryLocation = arrayDeliveryLocations.getJSONObject(i);
		        DeliveryLocationForAddress deliveryLocation = new DeliveryLocationForAddress ();
		        
		        deliveryLocation.deliveryLocationId = dictDeliveryLocation.getInt("DeliveryLocationId");
		        deliveryLocation.locationName = dictDeliveryLocation.getString("LocationName");
		        deliveryLocation.active = dictDeliveryLocation.getBoolean("Active");
		        
		        deliveryLocationsAndOffices.deliveryLocations.add(deliveryLocation);
		    }

		    deliveryLocationsAndOffices.deliveryLine1 = info.getString("DeliveryLine1");
		    deliveryLocationsAndOffices.lastLine = info.getString("LastLine");
		    
		    JSONArray arrayOffices = info.getJSONArray("Offices");
		    
		    for (int i = 0 ; i < arrayOffices.length() ; i++) {
		        
		    	JSONObject dictOffice = arrayOffices.getJSONObject(i);
		        DeliveryOffice deliveryOffice = new DeliveryOffice ();
		        
		        deliveryOffice.officeId = dictOffice.getInt("OfficeId");
		        deliveryOffice.officeName = dictOffice.getString("OfficeName");
		        deliveryOffice.deliveryLocationId = dictOffice.getInt("DeliveryLocationId");
		        
		        deliveryLocationsAndOffices.offices.add(deliveryOffice);
		    }
		    
		    deliveryLocationsAndOffices.latitude = info.getString("Latitude");
		    deliveryLocationsAndOffices.longitude = info.getString("Longitude");
		    
		    return deliveryLocationsAndOffices;
		    
		} catch (JSONException e) {

			e.printStackTrace();
		}
		
		return null;
	}

	public ValidatedAddress didValidateAddress (String strResponse){
	    
		try {
			
			JSONObject info = new JSONObject(strResponse);
			
		    ValidatedAddress validatedAddress = new ValidatedAddress ();
		    
		    validatedAddress.validatedAddressId = info.getInt("ValidatedAddressId");
		    
	    	JSONArray arrayCandidates = info.getJSONArray("Candidates");
	    
	        for (int i = 0 ; i < arrayCandidates.length() ; i++) {
	            
	        	JSONObject dictCandicate = arrayCandidates.getJSONObject(i);
	            CandidateAddress candidateAddress = new CandidateAddress ();
	            
	            candidateAddress.candidateAddressId = dictCandicate.getInt("CandidateAddressId");            
	            candidateAddress.deliveryLine1 = dictCandicate.getString("DeliveryLine1");
	            candidateAddress.lastLine = dictCandicate.getString("LastLine");
	            
	            validatedAddress.candidates.add(candidateAddress);
	        }
		    
		    validatedAddress.success = info.getBoolean("Success");
		    
		    return validatedAddress;
		    
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return null;
	}

}
