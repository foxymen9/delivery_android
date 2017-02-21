package com.delivery.api;

import android.util.Log;

/**
 * @author alexander
 * 
 */
public class Debug {
	
	public static boolean showLog() {
		
		return true;
	}
	
	public static void log(String msg) {
		
		if (showLog()) {
			
			Log.d("Foodsby", msg);
		}
	}
}
