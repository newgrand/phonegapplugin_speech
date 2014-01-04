package com.newgrand.cordova.speech;

public interface AudioStatus {

	void recordStatus(int status,String speexFileUrl);
	
	void playStatus(int status,String param);
}
