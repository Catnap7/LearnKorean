package com.jjw.learnKorean.common;

import android.app.Application;
import android.content.Context;

public class WsApplication extends Application {
	
	private static WsApplication instance;
	public static boolean ExitApp = false;
	
	public WsApplication() {
		instance = this;
		ExitApp = false;
	}
	public static Context getContext(){
		return instance;
	}
	
}
