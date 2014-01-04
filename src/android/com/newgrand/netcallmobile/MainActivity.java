package com.newgrand.netcallmobile;

import org.apache.cordova.DroidGap;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.newgrand.netcallmobile.MainActivity;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public class MainActivity extends DroidGap {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PushManager.startWork(getApplicationContext(),PushConstants.LOGIN_TYPE_API_KEY, getMetaValue(MainActivity.this, "api_key"));
		super.loadUrl("file:///android_asset/www/index.html");
	}
	
	// 获取ApiKey
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
        	return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
            	apiKey = metaData.getString(metaKey);
            }
        } catch (NameNotFoundException e) {

        }
        return apiKey;
    }
}
