package com.jjw.learnKorean.http;

import java.io.Serializable;

public class ComResponse implements Serializable
{

	private static final long serialVersionUID = 1L;
	private int code;
	private String message;
	private String rtnData;
	private String sendUrl;
	
	public ComResponse() {
		
	}
	
	public String getRtnData() {
		return rtnData;
	}

	public void setRtnData(String rtnData) {
		this.rtnData = rtnData;
	}
	public int getCode()
	{
		return code;
	}
	public void setCode(int code)
	{
		this.code = code;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	public String getSendUrl()
	{
		return sendUrl;
	}
	public void setSendUrl(String sendUrl)
	{
		this.sendUrl = sendUrl;
	}
}
