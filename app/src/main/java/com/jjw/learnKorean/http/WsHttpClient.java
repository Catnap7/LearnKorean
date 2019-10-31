package com.jjw.learnKorean.http;

import android.util.Log;
import android.webkit.CookieManager;

import com.wts.common.DicKey;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

public class WsHttpClient {

	private static final String TAG = "WsHttpClient";
	public static final int HTTPCLIENT_REQUEST_TIMEOUT = 35000;
	public static final String encoding = "utf-8";

	public static final int RESCODE_NULL_CONTENT = 100001;
	public static final int RESCODE_EXCEPTION = 100002;

	private static WsHttpClient instance;
	private static HttpClient mHttpClient;

	private String mServerName = "";
	
	public static synchronized WsHttpClient getInstance() {
		if (instance == null) {
			instance = new WsHttpClient();
		}
		return instance;
	}

	private WsHttpClient() {
	}

	public static HttpClient getThreadSafeClient() {
		if (mHttpClient == null) {
			mHttpClient = new DefaultHttpClient();
			ClientConnectionManager mgr = mHttpClient.getConnectionManager();
			HttpParams params = mHttpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(params, HTTPCLIENT_REQUEST_TIMEOUT);
			HttpConnectionParams.setSoTimeout(params, HTTPCLIENT_REQUEST_TIMEOUT);

			mHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(params, mgr.getSchemeRegistry()), params);
		}
		return mHttpClient;
	}

	public ComResponse request(String serverName, ComRequestParam reqParam) {
		mServerName = serverName;
		if (reqParam.isPost()) {
			return postClient(reqParam);
		} else {
			return getClient(reqParam);
		}
	}

	public ComResponse getClient(ComRequestParam reqParam) {

		ComResponse resData = new ComResponse();

		HttpClient client = getThreadSafeClient();
		HttpGet method = null;
		try {

			URI uri = URIUtils.createURI(""/*DicKey.SCHEME*/, mServerName, -1, reqParam.getPath(), URLEncodedUtils.format(reqParam.getParams(), encoding), null);
			method = new HttpGet(uri);
			method.setHeader("Accept", "application/json");
			resData.setSendUrl("" + method.getURI());

			HttpResponse response = client.execute(method);
			resData.setCode(response.getStatusLine().getStatusCode());

			HttpEntity entity = response.getEntity();
			if (resData.getCode() > HttpStatus.SC_OK && resData.getCode() < 300) {
				resData.setCode(HttpStatus.SC_OK);
			}
			if (entity != null) {
				try {
					resData.setMessage(EntityUtils.toString(entity, encoding));

				} catch (Exception e) {
					entity.consumeContent();
				}
			} else {
				resData.setCode(RESCODE_NULL_CONTENT);
			}
		} catch (Exception e) {
			Log.e(TAG, "", e);
			if (method != null)
				method.abort();
			resData.setCode(RESCODE_EXCEPTION);
		} finally {
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}
		return resData;
	}

	public ComResponse postClient(ComRequestParam reqParam) {
		ComResponse mr = new ComResponse();

		HttpClient client = getThreadSafeClient();
		HttpPost method = null;
		try {
			URI uri = URIUtils.createURI(DicKey.SCHEME, mServerName, -1, reqParam.getPath(), null, null);
			
			method = new HttpPost(uri);
			method.setEntity(new UrlEncodedFormEntity(reqParam.getParams(), encoding));
			for (NameValuePair nv : reqParam.getHeaders())
				method.addHeader(nv.getName(), nv.getValue());
			mr.setSendUrl("" + method.getURI());

			HttpResponse response = client.execute(method);
			mr.setCode(response.getStatusLine().getStatusCode());

			if ( mr.getCode() != 200) {
				return mr;
			}
			
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				try {
					
					String sJson = EntityUtils.toString(entity, encoding);
					mr.setRtnData(sJson);
					
					JSONObject json;
					try {
						json = new JSONObject(sJson);
						
						int code = json.getInt("code");
						String msg = json.getString("msg");
						
						mr.setCode(code);
						mr.setMessage(msg);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					mr.setMessage(EntityUtils.toString(entity, encoding));
					JSONObject jsonObj = new JSONObject(EntityUtils.toString( entity, encoding));

				} finally {
					entity.consumeContent();
				}
			}
		} catch (Exception e) {
			if (method != null) {
				method.abort();
			}
		}
		return mr;
	}

	public static String setValue(String key, String value) {
		return "Content-Disposition: form-data; name=\"" + key + "\"\r\n\r\n" + value;
	}

	public static String setFile(String key, String fileName) {
		return "Content-Disposition: form-data; name=\"" + key + "\";filename=\"" + fileName + "\"\r\n";
	}

	public static String getResponseData(InputStream is) throws IOException {
		int ch;
		StringBuffer b = new StringBuffer();
		while ((ch = is.read()) != -1) {
			b.append((char) ch);
		}
		String s = b.toString();
		return s;
	}

	public static boolean syncCookies(){

		if (null == mHttpClient) return false;
		boolean ret = false;

		try {

			List<Cookie> cookies = ((DefaultHttpClient)mHttpClient).getCookieStore().getCookies();

			if (!cookies.isEmpty()) {
				String domain = "";/*DicKey.SCHEME + "://"+DicKey.HOST;*/
				for (Cookie cookie : cookies) {
					String cookieString = cookie.getName() + "=" + cookie.getValue();
					CookieManager.getInstance().setCookie(domain, cookieString);
				}
			}
			ret = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
}
