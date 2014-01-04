package com.newgrand.cordova.speech;

import java.io.File;

import android.content.Context;
import android.os.Environment;

public class DirectoryUtil {

	private static File getRootDirectory(Context context){
        File ret = null;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			ret = Environment.getExternalStorageDirectory();
        }else{
        	ret = context.getFilesDir();
        }
		return ret;
	}
	
	public static File getImageDirectory(Context context){
		File dir = new File(getRootDirectory(context),"image");
		if(!dir.exists()){
			dir.mkdir();
		}
		return dir;
	}
	public static File getPropertiesDirectory(Context context){
		File dir = new File(getRootDirectory(context),"properties");
		if(!dir.exists()){
			dir.mkdir();
		}
		return dir;
	}
	public static File getFileDirectory(Context context){
		File dir = new File(getRootDirectory(context),"file");
		if(!dir.exists()){
			dir.mkdir();
		}
		return dir;
	}
	public static File getSoundDirectory(Context context){
		File dir = new File(getRootDirectory(context),"speech");
		if(!dir.exists()){
			dir.mkdir();
		}
		return dir;
	}
}
