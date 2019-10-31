package com.jjw.learnKorean.http;

public interface WsHttpRequestListener {
	public void onFailure(ComResponse response);
	public void onSuccess(ComResponse response);
}
