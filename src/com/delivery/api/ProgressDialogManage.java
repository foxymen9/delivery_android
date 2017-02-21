package com.delivery.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;

public class ProgressDialogManage {
	
	static ProgressDialog progressDialog = null;
	
	public static void show(Context context) {
		
		if (progressDialog != null)
			progressDialog = null;
		
		progressDialog = ProgressDialog.show(context, "", "Please wait...");
		progressDialog.getWindow().setLayout(450, LayoutParams.WRAP_CONTENT);
	}
	
	public static void hide() {
		
		if (progressDialog == null)
			return;
		
		progressDialog.dismiss();		
	}
}
