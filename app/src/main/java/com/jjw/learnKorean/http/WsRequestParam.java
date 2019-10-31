package com.jjw.learnKorean.http;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WsRequestParam{
	
	private String path;
	public List<NameValuePair> params;
	public List<NameValuePair> headers;
	public Map<String,File> files;
	private boolean post = true;
	
	public WsRequestParam() {
		params = new ArrayList<NameValuePair>();
		headers = new ArrayList<NameValuePair>();
		files = new HashMap<String, File>();
		post = true;
	}

	public void addParam(String key, String value) {
		params.add(new BasicNameValuePair(key, value));
	}

	public void addHeader(String key, String value) {
		headers.add(new BasicNameValuePair(key, value));
	}

	public void addFile(String key, File value){
		
		files.put(key, value);
	}
	
	public boolean isPost() {
		return post;
	}
	
	public void setPost(boolean post) {
		this.post = post;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
