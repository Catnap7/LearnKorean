package com.jjw.learnKorean.http;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

public class WgHttp {

	public static void requestLogout(){
		ComRequestParam params = new ComRequestParam();
		new WsHttpTask(null).execute(params);
	}

	public static void removeSessionCookie(Context context){
		CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(context);
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.setAcceptCookie(true);
		cookieManager.removeSessionCookie();
		cookieSyncManager.sync();
	}
}
