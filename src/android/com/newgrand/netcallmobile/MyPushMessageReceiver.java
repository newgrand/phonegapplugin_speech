package com.newgrand.netcallmobile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.baidu.frontia.api.FrontiaPushMessageReceiver;

public class MyPushMessageReceiver extends FrontiaPushMessageReceiver{
	
	
	@Override
	public void onBind(Context arg0, int errorCode, String appid, String userId,
			String channelId, String requestId) {
		// TODO Auto-generated method stub
		File dir = DirectoryUtil.getPropertiesDirectory(arg0);
		File file = new File(dir,"devicetoken");
		try {
			FileOutputStream output = new FileOutputStream(file);
			output.write(userId.getBytes("utf-8"));
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onDelTags(Context arg0, int arg1, List<String> arg2,
			List<String> arg3, String arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onListTags(Context arg0, int arg1, List<String> arg2,
			String arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(Context arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onNotificationClicked(Context arg0, String arg1, String arg2,
			String arg3) {
		// TODO Auto-generated method stub
		updateContent(arg0,arg2,arg1);
    	NotificationManager notificationManager = (NotificationManager) arg0
				.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancelAll();
	}

	@Override
	public void onSetTags(Context arg0, int arg1, List<String> arg2,
			List<String> arg3, String arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnbind(Context arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}
	
	private void updateContent(Context context, String message,String title) {
    	Intent intent = new Intent();
    	intent.setClass(context, MainActivity.class);
    	String strs = message.split(":")[0].trim();//message format:fangt(ft@tc):wwwwwww
    	int s = strs.indexOf("(");
    	if(s<0){
    		return;
    	}
    	intent.putExtra("jid",strs.substring(s+1,strs.length()-1));//jid:ft@tc
        intent.putExtra(Constants.NOTIFICATION_TITLE, title);
        intent.putExtra(Constants.NOTIFICATION_MESSAGE, message);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.getApplicationContext().startActivity(intent);
        Log.e(TAG,"fangt:updateContent");
	}
}
