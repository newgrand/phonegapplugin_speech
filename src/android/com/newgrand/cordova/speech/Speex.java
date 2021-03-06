package com.newgrand.cordova.speech;  

import android.util.Log;
  
public class Speex  {
    private static String TAG;

	static {
        try {  
            System.loadLibrary("speex");  
        } catch (Throwable e) {  
            Log.e(TAG, e.getMessage(),e);  
        } 
    }
	
    private static final int DEFAULT_COMPRESSION = 8;  
  
    public Speex() {
    	
    }  

    public void init() {  
        open(DEFAULT_COMPRESSION);  
    }  

    public native int open(int compression);
    
    public native int getFrameSize();

    public native int decode(byte encoded[], short lin[], int size);
    
    public native int encode(short lin[], int offset, byte encoded[], int size); 
    
    public native void close();  
      
}  