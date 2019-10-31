package com.jjw.learnKorean.http;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComRequestParam{
	private static final String TAG = "ComRequestParam";
	private String url;
	private String path;
	private List<NameValuePair> params;
	private List<NameValuePair> headers;
	private Map<String,File> files;
	private boolean post = true;
	
	public ComRequestParam() {
		params = new ArrayList<>();
		headers = new ArrayList<>();
		files = new HashMap<>();
	}

	public void addParam(String key, Object value) {
		params.add(new BasicNameValuePair(key, "" + value));
	}

	public void addHeader(String key, Object value) {
		headers.add(new BasicNameValuePair(key, "" + value));
	}

	public void addFile(String key, File value){
		
		files.put(key, value);
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<NameValuePair> getParams() {
		return params;
	}
	public Map<String,File> getFiles() {
		return files;
	}
	public void setParams(List<NameValuePair> params) {
		this.params = params;
	}
	public boolean isPost() {
		return post;
	}
	public void setPost(boolean post) {
		this.post = post;
	}

	public List<NameValuePair> getHeaders() {
		return headers;
	}

	public void setHeaders(List<NameValuePair> headers) {
		this.headers = headers;
	}
	
	public void testPrint() {
		Log.d(TAG, "params");
		if(params != null) {
			for(NameValuePair nvp : params) {
				Log.d(TAG, "\t key= " + nvp.getName() + ", value= " + nvp.getValue());
			}
		}
	}
}
