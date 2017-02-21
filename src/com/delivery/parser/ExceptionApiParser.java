package com.delivery.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.delivery.datamodel.FoodsbyException.FoodsbyException;
import com.delivery.utils.Utils;

public class ExceptionApiParser {

	private static 		ExceptionApiParser 	parserInstance = null;

	public static ExceptionApiParser sharedInstance() {
		
		if (parserInstance == null) {
		
			parserInstance = new ExceptionApiParser();
		}
		
		return parserInstance;
	}

	public void error (String strResponse) {
	    
	    
	}

	public void didGetAllGlobalException (String strResponse) {

		try {
			
			JSONArray info = new JSONArray(strResponse);
			
		    if (Utils.sharedInstance().arrayExceptions.size() > 0)
		    	Utils.sharedInstance().arrayExceptions.removeAll(null);
		    
		    for (int i = 0 ; i < info.length() ; i ++) {
		        
		    	JSONObject dict = info.getJSONObject(i);
		    	FoodsbyException exception = new FoodsbyException ();
		        exception.globalExceptionId = dict.getInt("GlobalExceptionId");
		        exception.exceptionDate = dict.getString("ExceptionDate");
		        exception.duration = dict.getInt("Duration");
		        
		        Utils.sharedInstance().arrayExceptions.add(exception);
		    }

		} catch (JSONException e) {

			e.printStackTrace();
		}

	}

	public void didGetOpenTodayException(boolean info) {
	    
	    
	}

}
