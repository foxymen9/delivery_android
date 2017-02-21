package com.delivery.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.util.Base64;

import com.delivery.datamodel.Parameters.CheckoutInfo;
import com.delivery.datamodel.Parameters.ContactInfo;
import com.delivery.datamodel.Parameters.MenuInfo;
import com.delivery.datamodel.Parameters.OrderAddItem;
import com.delivery.datamodel.Parameters.ReorderInfo;
import com.delivery.datamodel.Parameters.SelectedAnswerInfo;
import com.delivery.datamodel.Parameters.UserInfo;
import com.delivery.foodsby.R;
import com.delivery.parser.MenuApiParser;
import com.delivery.parser.UserApiParser;
import com.delivery.utils.MyApplication;
import com.delivery.utils.Utils;



public class ClientApi {
	
	/*************************************************************** URLs ***************************************************************/

	private static final String 	BASE_URL = "https://test.foodsby.com/api/";

	private static final String 	URL_GETMENU = "menu";
	private static final String 	URL_GETMENUITEM = "menu/item/%lu";
	private static final String 	URL_GETLOCATIONSCHEDULE = "location/%ld/schedule";
	private static final String 	URL_GETDELIVERYLOCATIONS = "location";
	private static final String 	URL_GETSPECIFICDELIVERYLOCATIONS = "location/%ld";
	private static final String 	URL_SEARCHOFFICE = "search/%ld";
	private static final String 	URL_SEARCHCOMPANYDETAILS = "search/query/%ld";
	private static final String 	URL_CREATEUSER = "user/createuser";
	private static final String 	URL_GETUSERTOKEN = "token";
	private static final String 	URL_GETUSER = "user";
	private static final String 	URL_LOGIN = "login";
	private static final String 	URL_RESETPASSWORD = "user/resetpassword";
	private static final String 	URL_UPDATEUSER = "user";
	private static final String 	URL_SETCONTACTUSERINFO = "user";
	private static final String 	URL_CHANGEPASSWORD = "user/changepassword";
	private static final String 	URL_GETUSERCARDS = "user/cards";
	private static final String 	URL_DELETEUSERCARDS = "user/cards/delete";
	private static final String 	URL_APPLYCOUPONTOORDER = "order/applycoupon";
	private static final String 	URL_REMOVEORDERITEM = "order/removeitem";
	private static final String 	URL_GETORDER = "order/%ld";
	private static final String 	URL_GETORDERHISTORY = "order/history/%ld";
	private static final String 	URL_ORDERADDITEM = "order/additem";
	private static final String 	URL_REORDER = "order/reorder";
	private static final String 	URL_CHECKOUTSAVEDCARD = "checkout/savedcard";
	private static final String 	URL_CHECKOUTCARD = "checkout";
	private static final String 	URL_CHECKOUTFREEMEAL = "checkout/freemeal";
	private static final String 	URL_ADDRESSVALIDATE = "address/validate";
	private static final String 	URL_DELIVERYADDRESS = "address/%lu";
	private static final String 	URL_CREATEOFFICE = "office";
	private static final String 	URL_INVITEOFFICE = "office/invite";
	private static final String 	URL_GETOFFICE = "office/%ld";
	private static final String 	URL_GETALLOFFICES = "office";
	private static final String 	URL_GETALLGLOBALEXCEPTION = "exception";
	private static final String 	URL_GETOEPNTODAYEXCEPTION = "exception/open";
	
	
	

	/*************************************************************** Constants ***************************************************************/
	
	//error
	public static final int ERROR_NONE = 0;
	public static final int ERROR_NO_CONNECTION = 1;
	public static final int ERROR_MALFORMED_URL = 2;
	public static final int ERROR_IO = 3;
	public static final int ERROR_JSON = 4;
	public static final int ERROR_REQUEST = 5;
	public static final int ERROR_XML = 6;
	public static final int ERROR_SESSION_EXPIRED = 7;

	
	
	
	/*************************************************************** enum ***************************************************************/

	enum API_CALL_TYPE {

	    GETMENU,
	    GETMENUITEM,

	    GETDELIVERYLOCATIONS,
	    GETSPECIFICDELIVERYLOCATION,
	    GETLOCATIONSCHEDULE,
	    
	    SEARCHOFFICE,
	    SEARCHCOMPANYDETAILS,
	    
	    GETUSER,
	    UPDATEUSER,
	    CREATEUSER,
	    GETUSERCARDS,
	    DELETEUSERCARDS,
	    RESETPASSWORD,
	    CHANGEPASSWORD,

	    GETUSERTOKEN,
	    
	    LOGIN,
	    
	    GETORDER,
	    REORDER,
	    GETORDERHISTORY,
	    ORDERADDITEM,
	    REMOVEORDERITEM,
	    APPLYCOUPONTOORDER,

	    CHECKOUTCARD,
	    CHECKOUTSAVEDCARD,
	    CHECKOUTFREEMEAL,

	    ADDRESSVALIDATE,
	    DELIVERYADDRESS,

	    GETALLOFFICES,
	    CREATEOFFICE,
	    GETOFFICE,
	    INVITEOFFICE,

	    GETALLGLOBALEXCEPTION,
	    GETOPENTODAYEXCEPTION,
	    
	    SETCONTACTUSERINFO,
	};

	

	
	/*************************************************************** Variables ***************************************************************/

	private static 		ClientApi 	clientApiInstance = null;
	private 			String 		responseString;
	private static		String 		serverError = null;
	
	
	
	/*************************************************************** General ***************************************************************/	
	
	
	private ClientApi() {}

	public static ClientApi sharedInstance() {
		
		if (clientApiInstance == null) {
		
			clientApiInstance = new ClientApi();
		}
		
		return clientApiInstance;
	}
	
	public static void showErrorMessage() {
		
		if (serverError == null)
			return;
		
		AlertDialog.Builder alert = new AlertDialog.Builder(MyApplication.getAppContext());
		alert.setTitle("Foodsby").setMessage(serverError).setIcon(R.drawable.ic_launcher).setNeutralButton("Ok", null).show();
		
		serverError = null;

	}
	
	private void setResponse(String response) {
		
		this.responseString = response;
	}

	private String getResponse() {
		
		return responseString;
	}
	
	
	//POST Method
	private boolean doPost(String apiName, List<NameValuePair> params) {
		
		HttpEntity entity = null;
    	HttpResponse response = null;
		serverError = null;

		if (params != null) {
			
			try {
				entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
				
			} catch (UnsupportedEncodingException e) {}
		}

		String sUrl = BASE_URL + apiName;

		Debug.log("POST: " + sUrl);

		HttpPost postRequest = new HttpPost(sUrl);
		
		HttpClient client = new DefaultHttpClient();

    	try {

    		
    		if (entity != null)
    			postRequest.setEntity(entity);
    		
			response = client.execute(postRequest);
			HttpEntity responseEntity = response.getEntity();
			
			if (responseEntity != null) {
				
				convertInputStreamToString(responseEntity.getContent());
				return true;
			}
			
		} catch (IOException e) {
		}

    	return false;
	}

	private boolean doPostWithAuth(String apiName, List<NameValuePair> params) {
		
		HttpEntity entity = null;
    	HttpResponse response = null;
		serverError = null;

		if (params != null) {
			
			try {
				entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
				
			} catch (UnsupportedEncodingException e) {}
		}

		String sUrl = BASE_URL + apiName;

		Debug.log("POST: " + sUrl);

		HttpPost postRequest = new HttpPost(sUrl);
		
		String username = Utils.getStringValue("username");
		String password = Utils.getStringValue("password");		
		String auth = Base64.encodeToString( (username + ":" + password).getBytes(), Base64.NO_WRAP);
		postRequest.setHeader("Authorization", "Basic" + auth);

    	HttpClient client = new DefaultHttpClient();

    	try {

    		if (entity != null)
    			postRequest.setEntity(entity);
    		
			response = client.execute(postRequest);
			HttpEntity responseEntity = response.getEntity();
			
			if (responseEntity != null) {
				
				convertInputStreamToString(responseEntity.getContent());
				return true;
			}
			
		} catch (IOException e) {
			
		}

    	return false;
	}
	
	
	//GET Method  
	private boolean doGetWithAuth(String apiName, List<NameValuePair> params) {
		
		serverError = null;
		
		String sUrl = BASE_URL + apiName + encodeParams(params);
		
		HttpParams httpParameters = new BasicHttpParams();

		int timeoutConnection = 30000;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		int timeoutSocket = 50000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		HttpClient httpClient = new DefaultHttpClient(httpParameters);
		HttpGet getRequest = new HttpGet(sUrl);

		String username = Utils.getStringValue("username");
		String password = Utils.getStringValue("password");		
		String auth = Base64.encodeToString( (username + ":" + password).getBytes(), Base64.NO_WRAP);
		getRequest.setHeader("Authorization", "Basic " + auth);
		
		try {
			
			HttpResponse httpResponse = httpClient.execute(getRequest);
			HttpEntity responseEntity = httpResponse.getEntity();

			int responseCode = httpResponse.getStatusLine().getStatusCode();			

			if (responseCode == 200) {

				String jsonString = EntityUtils.toString(responseEntity); 
				
				convertInputStreamToString(responseEntity.getContent());
				
			}
			
			return true;
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return false;
	}

	private String encodeParams(List<NameValuePair> params) {
		
		if (params == null) {
			
			return "";
		}
		
		StringBuffer paramBuffer = new StringBuffer();
		
		boolean f1rst = true;
		
		for (NameValuePair param: params) {
			
			paramBuffer.append(f1rst ? "?" : "&");
			paramBuffer.append(param.getName());
			paramBuffer.append("=");
			String value = param.getValue();
			
			if (value == null) {
				
				value = "null";
				
			} else{
				
				try {
					
					value = URLEncoder.encode(value, "UTF-8");
					
				} catch (UnsupportedEncodingException e) {
					
				}				
			}
			
			paramBuffer.append(value);
			f1rst = false;
		}
		
		return paramBuffer.toString();
	}
	
	private String convertInputStreamToString(InputStream is) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuffer responseBuff = new StringBuffer();
		String line;

		while ((line = reader.readLine()) != null)			
			responseBuff.append(line);
		
		setResponse(responseBuff.toString());
		
		Debug.log("Response: " + getResponse());
		
		return getResponse();
	}

	private boolean sendApiRequest(String method, String apiName, List<NameValuePair> params) {
		
		synchronized (clientApiInstance) {
			
			if (HttpGet.METHOD_NAME.equalsIgnoreCase(method)) {
				
				return doGetWithAuth(apiName, params);
			}
			else if (HttpPost.METHOD_NAME.equalsIgnoreCase(method)) {
				
				return doPostWithAuth(apiName, params);
			}			
		}

		return false;
	}

	
	
	
	
	/*************************************************************** Menu API ***************************************************************/	
	
	public void getMenu (MenuInfo info) {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("DeliveryTimeId", String.format("%ld",info.deliveryTimeId)));
		params.add(new BasicNameValuePair("DeliveryLocationId", String.format("%ld",info.deliveryLocationId)));
		params.add(new BasicNameValuePair("StoreId", String.format("%ld",info.storeId)));
		params.add(new BasicNameValuePair("DeliveryId", String.format("%ld",info.deliveryId)));
		params.add(new BasicNameValuePair("DayOfWeek", String.format("%ld",info.dayOfWeek)));
		
		if (sendApiRequest(HttpPost.METHOD_NAME, URL_GETMENU, params)) {
			
			String strResponse = getResponse();
			MenuApiParser.sharedInstance().didGetMenu(strResponse);

			
			try { 
				

				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}
	
	public void getMenuItem (Integer itemId) {
		
	    String url = String.format(URL_GETMENUITEM, itemId);
		
		if (sendApiRequest(HttpGet.METHOD_NAME, url, null)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	
	
	

	/*************************************************************** Location API ***************************************************************/
	
	public void getLocationSchedule (Integer locationId) {
		
	    String url = String.format(URL_GETLOCATIONSCHEDULE, locationId);
		
		if (sendApiRequest(HttpGet.METHOD_NAME, url, null)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
				
			}
		}
	
		return;
	}

	public void getDeliveryLocations ( ) {
		
		if (sendApiRequest(HttpGet.METHOD_NAME, URL_GETDELIVERYLOCATIONS, null)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
				
			}
		}
	
		return;
	}

	public void getSpecificDeliveryLocation (Integer locationId) {
		
	    String url = String.format(URL_GETSPECIFICDELIVERYLOCATIONS, locationId);
		
		if (sendApiRequest(HttpGet.METHOD_NAME, url, null)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	
	
	
	
	/*************************************************************** Find API ***************************************************************/
	
	public void searchOffice (Integer officeId) {
		
	    String url = String.format(URL_SEARCHOFFICE, officeId);
		
		if (sendApiRequest(HttpGet.METHOD_NAME, url, null)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}
	
	public void searchCompanyDetails (Integer companyId) {
		
	    String url = String.format(URL_SEARCHCOMPANYDETAILS, companyId);
		
		if (sendApiRequest(HttpGet.METHOD_NAME, url, null)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}
	
	
	
	

	/*************************************************************** User API ***************************************************************/
	
	public void createUser (String email, String password) {
		
		Utils.setStringValue("username", email);
		Utils.setStringValue("password", password);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("Email", email));
		params.add(new BasicNameValuePair("Password", password));
		params.add(new BasicNameValuePair("SMSNotify", "0"));
		
		if (doPost( URL_CREATEUSER, params)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}
	
	public void getUserToken () {
		
	    if (sendApiRequest(HttpGet.METHOD_NAME, URL_GETUSERTOKEN, null)) {
			
			String strResponse = getResponse();
			UserApiParser.sharedInstance().didGetUserToken(strResponse);			
		}
	
		return;
	}

	public void getUser () {
		
	    if (sendApiRequest(HttpGet.METHOD_NAME, URL_GETUSER, null)) {
			
			String strResponse = getResponse();
			UserApiParser.sharedInstance().didGetUser(strResponse);
		}
	
		return;
	}
	
	public void logIn (String email, String password) {
		
		Utils.setStringValue("username", email);
		Utils.setStringValue("password", password);

		if (sendApiRequest(HttpGet.METHOD_NAME, URL_LOGIN, null)) {
			
			String strResponse = getResponse();
			UserApiParser.sharedInstance().didLogIn(strResponse);
		}
	
		return;
	}

	public void resetPassword (String email) {
		

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("email", email));

		if (sendApiRequest(HttpPost.METHOD_NAME, URL_RESETPASSWORD, params)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	public void updateUser (UserInfo info) {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("FirstName", info.fistName));
		params.add(new BasicNameValuePair("LastName", info.lastName));
		params.add(new BasicNameValuePair("Phone", info.phone));
		params.add(new BasicNameValuePair("SMSNotify", String.format("%ld",info.bSMSNotify)));
		params.add(new BasicNameValuePair("Birthday", info.birthday));
		params.add(new BasicNameValuePair("DeliveryLocationId", String.format("%ld",info.deliveryLocationId)));
		params.add(new BasicNameValuePair("OfficeId", String.format("%ld",info.officeId)));
		
		if (sendApiRequest(HttpPost.METHOD_NAME, URL_UPDATEUSER, params)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}
	
	public void setContactUserInfo (ContactInfo info) {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("FirstName", info.fistName));
		params.add(new BasicNameValuePair("LastName", info.lastName));
		params.add(new BasicNameValuePair("SMSNotify", String.format("%ld",info.bSMSNotify)));
		params.add(new BasicNameValuePair("Phone", info.phone));
		
		if (sendApiRequest(HttpPost.METHOD_NAME, URL_SETCONTACTUSERINFO, params)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	public void changePassword (String oldPassword, String newPassword) {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("OldPassword", oldPassword));
		params.add(new BasicNameValuePair("NewPassword", newPassword));
		
		if (sendApiRequest(HttpPost.METHOD_NAME, URL_CHANGEPASSWORD, params)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	public void getUserCards () {
		
	    if (sendApiRequest(HttpGet.METHOD_NAME, URL_GETUSERCARDS, null)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
				
			}
		}
	
		return;
	}
	
	public void deleteUserCards () {
		
	    if (sendApiRequest(HttpGet.METHOD_NAME, URL_DELETEUSERCARDS, null)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}
	
	
	
	
	
	/*************************************************************** Order API ***************************************************************/

	public void applyCouponToOrder (Integer orderId, String couponCode) {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("OrderId", String.format("%ld", orderId)));
		params.add(new BasicNameValuePair("CouponCode", couponCode));
		
		if (sendApiRequest(HttpPost.METHOD_NAME, URL_APPLYCOUPONTOORDER, params)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	public void removeOrderItem (Integer orderId, Integer orderItemId) {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("OrderId", String.format("%ld", orderId)));
		params.add(new BasicNameValuePair("OrderItemId", String.format("%ld", orderItemId)));
		
		if (sendApiRequest(HttpPost.METHOD_NAME, URL_REMOVEORDERITEM, params)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	public void getOrder (Integer orderId) {
		
	    String url = String.format(URL_GETORDER, orderId);
		
		if (sendApiRequest(HttpGet.METHOD_NAME, url, null)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	public void getOrderHistory (Integer storeId) {
		
	    String url = String.format(URL_GETORDERHISTORY, storeId);
		
		if (sendApiRequest(HttpGet.METHOD_NAME, url, null)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	public void addOrderItem (OrderAddItem info) {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("OrderId", String.format("%ld", info.orderId)));
		params.add(new BasicNameValuePair("menuItemId", String.format("%ld", info.menuItemId)));
		params.add(new BasicNameValuePair("SpecialInstructions", info.specialInstructions));
		
		ArrayList <Object> array = new ArrayList <Object>();
		
		for (int i = 0 ; i < info.selectedAnswers.size() ; i ++) {
		
			SelectedAnswerInfo select = info.selectedAnswers.get(i);
			List<NameValuePair> selectedAnswersParams = new ArrayList<NameValuePair>();
			
			selectedAnswersParams.add(new BasicNameValuePair("AnswerId", String.format("%ld", select.answerId)));
			selectedAnswersParams.add(new BasicNameValuePair("Selected", String.format("%ld", select.selected)));
			selectedAnswersParams.add(new BasicNameValuePair("Depth", String.format("%ld", select.depth)));
			
			array.add(selectedAnswersParams);
		}		

		params.add(new BasicNameValuePair("SelectedAnswers", array.toString()));
		
		if (sendApiRequest(HttpPost.METHOD_NAME, URL_ORDERADDITEM, params)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	public void reorder (ReorderInfo info) {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("OrderHistoryId", String.format("%ld", info.orderHistoryId)));
		params.add(new BasicNameValuePair("DayOfWeek", String.format("%ld", info.dayOfWeek)));
		params.add(new BasicNameValuePair("StoreId", String.format("%ld", info.storeId)));
		params.add(new BasicNameValuePair("DeliveryTimeId", String.format("%ld", info.deliveryTimeId)));
		params.add(new BasicNameValuePair("DeliveryLocationId", String.format("%ld", info.deliveryLocationId)));
		
		if (sendApiRequest(HttpPost.METHOD_NAME, URL_REORDER, params)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	
	
	
	
	/*************************************************************** Checkout API ***************************************************************/

	public void checkoutSavedCard (Integer orderId, Integer ccProfileId, boolean isProduction) {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("OrderId", String.format("%ld", orderId)));
		params.add(new BasicNameValuePair("CCProfileId", String.format("%ld", ccProfileId)));
		params.add(new BasicNameValuePair("IsProduction", String.format("%ld", isProduction)));
		
		if (sendApiRequest(HttpPost.METHOD_NAME, URL_CHECKOUTSAVEDCARD, params)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	public void checkoutCard (CheckoutInfo info) {
		
		List<NameValuePair> paymentParams = new ArrayList<NameValuePair>();
		
		paymentParams.add(new BasicNameValuePair("FirstName", info.payment.fistName));
		paymentParams.add(new BasicNameValuePair("LastName", info.payment.lastName));
		paymentParams.add(new BasicNameValuePair("CardNbr", info.payment.cardNbr));
		paymentParams.add(new BasicNameValuePair("ExpMonth", String.format("%ld", info.payment.expMonth)));
		paymentParams.add(new BasicNameValuePair("ExpYear", String.format("%ld", info.payment.expYear)));
		paymentParams.add(new BasicNameValuePair("CVV2", info.payment.cVV2));
		paymentParams.add(new BasicNameValuePair("Amount", String.format("%ld", info.payment.amount)));
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("OrderId", String.format("%ld", info.orderId)));
		params.add(new BasicNameValuePair("Street", info.street));
		params.add(new BasicNameValuePair("City", info.street));
		params.add(new BasicNameValuePair("Zip", info.street));
		params.add(new BasicNameValuePair("Payment", paymentParams.toString()));
		params.add(new BasicNameValuePair("SaveCard", String.format("%ld", info.saveCard)));
		params.add(new BasicNameValuePair("IsProduction", String.format("%ld", info.isProduction)));
		
		if (sendApiRequest(HttpPost.METHOD_NAME, URL_CHECKOUTCARD, params)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	public void checkoutFreeMeal (Integer orderId) {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("OrderId", String.format("%ld", orderId)));
		
		if (sendApiRequest(HttpPost.METHOD_NAME, URL_CHECKOUTFREEMEAL, params)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	
	
	
	
	/*************************************************************** Address API ***************************************************************/
	
	public void validateAddress (String street, String city, String state, String zip) {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("Street", street));
		params.add(new BasicNameValuePair("city", city));
		params.add(new BasicNameValuePair("state", state));
		params.add(new BasicNameValuePair("zip", zip));
		
		if (sendApiRequest(HttpPost.METHOD_NAME, URL_ADDRESSVALIDATE, params)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	public void getDevliveryAddress (Integer addressId) {
		
	    String url = String.format(URL_DELIVERYADDRESS, addressId);
		
		if (sendApiRequest(HttpGet.METHOD_NAME, url, null)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	
	
	
	
	/*************************************************************** Address API ***************************************************************/

	public void createOffice (String companyName, boolean isCandidateAddress, Integer selectedAddressId) {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("CompanyName", companyName));
		params.add(new BasicNameValuePair("IsCandidateAddress", String.format("%ld", isCandidateAddress)));
		params.add(new BasicNameValuePair("SelectedAddressId", String.format("%ld", selectedAddressId)));
		
		if (sendApiRequest(HttpPost.METHOD_NAME, URL_CREATEOFFICE, params)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}
	
	public void inviteOffice (String emails, Integer officeId) {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("InviteEmails", emails));
		params.add(new BasicNameValuePair("OfficeId", String.format("%ld", officeId)));
		
		if (sendApiRequest(HttpPost.METHOD_NAME, URL_INVITEOFFICE, params)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	public void getOffice (Integer officeId) {
		
	    String url = String.format(URL_GETOFFICE, officeId);
		
		if (sendApiRequest(HttpGet.METHOD_NAME, url, null)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	public void getAllOffices () {
		
	    if (sendApiRequest(HttpGet.METHOD_NAME, URL_GETALLOFFICES, null)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	
	

	
	/*************************************************************** Exception API ***************************************************************/

	public void getAllGlobalException () {
		
	    if (sendApiRequest(HttpGet.METHOD_NAME, URL_GETALLGLOBALEXCEPTION, null)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

	public void getOpenTodayException () {
		
	    if (sendApiRequest(HttpGet.METHOD_NAME, URL_GETOEPNTODAYEXCEPTION, null)) {
			
			String strResponse = getResponse();
			
			try { 
				
				JSONObject jsonData = new JSONObject(strResponse);
				
				String status = jsonData.getString("status");
				
				if (status.equalsIgnoreCase("success")) {
					
					//JSONArray jsonArray = jsonData.getJSONArray("response");
					
					return;
					
				} else {
					
					serverError = jsonData.getString("message");
				}
	
			} catch (JSONException e) {
			}
		}
	
		return;
	}

}
