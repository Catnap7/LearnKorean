/*
   Copyright 2012 Harri Smatt

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.jjw.learnKorean.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.jjw.learnKorean.R;
import java.io.File;

/**
 * WsWebView 수정
 * 2014.02.07
 * @author Windn Rhyang
 *
 */
@SuppressLint("NewApi") public class WsWebView extends WebView implements DownloadListener{
	
	private final String TAG = this.getClass().getSimpleName();

	private final static int FILECHOOSER_RESULTCODE=1111;  
	private final static int CAMERAREQUEST_RESULTCODE = 2222;

//	protected Create_ProgressDialog pDialog = null;
	private DialogInterface.OnCancelListener mOnCancelListener;
	public boolean ClearHistory = true;

	private static ValueCallback<Uri> mUploadMessage;  
	private static Uri mCapturedImageURI;
	private File mPhoto;

	private WsWebView instance;
	
	Mng_Cache cache;

	public WsWebView(Context context) {
		this(context, null, 0);
	}

	public WsWebView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public WsWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		cache = Mng_Cache.getInstance();

		if(!isInEditMode()){
			setWebSetting();
			instance = this;
		}
	}

	private void setWebSetting() {
		setFocusable(true);
		setFocusableInTouchMode(true);

		WebSettings websetting = getSettings();

		websetting.setLoadsImagesAutomatically(true);
		websetting.setJavaScriptEnabled(true);
		//		websetting.setSupportZoom(true);
		websetting.setLoadWithOverviewMode(true);
		websetting.setTextZoom(100);
//		websetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

		setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_UP:
					if (!v.hasFocus()) {
						v.requestFocus();
					}
					break;
				}
				return false;
			}
		});

		websetting.setJavaScriptCanOpenWindowsAutomatically(true);
		websetting.setSupportMultipleWindows(true);
		websetting.setBlockNetworkImage(false);
		websetting.setUseWideViewPort(true);
		//		websetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
		//		websetting.setPluginState(PluginState.ON);
		websetting.setBuiltInZoomControls(false);
		websetting.setGeolocationEnabled(true);

		setVerticalScrollBarEnabled(true);

		setWebViewClient(new WsWebViewClient());
		setWebChromeClient(new WsWebChromeClient());
		setDownloadListener(this);


	}

	protected class WsWebViewClient extends WebViewClient{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			CookieManager cookieManager = CookieManager.getInstance();
			String cookie = cookieManager.getCookie(url); // 로그인 후 쿠키값을 들고온다.

			// 잘못들어온 URL wb_detailTire.do?member_idx가 들어와야됨
			if (url.indexOf("wb_detailTire.do?comment_txt") > 0) {
				return true;
			}

			if (url.startsWith("tel:")) {
//				Intent i = new Intent(Intent.ACTION_CALL, Uri.parse(url));
				Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
				getContext().startActivity(i);
				return true;
			}
			if (url.startsWith("smsto:")) {
				Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
				getContext().startActivity(i);
				return true;
			}
			if (url.startsWith("mailto:")) {
				Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
				getContext().startActivity(i);
				return true;
			}

			view.loadUrl(url);
			return true;
		}

		public void onPageStarted(WebView view, String url, Bitmap favicon) {

			if (url.indexOf("http") >= 0) {
				int result =0;
				if (0 != result) {
					Toast.makeText(getContext(), "네트워크 에러", Toast.LENGTH_SHORT).show();
				}
			}
		}

		public void onPageFinished(WebView view, String url) {
			if(ClearHistory){
				instance.clearHistory();
				ClearHistory = false;
			}
		}

		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			try {
				Toast.makeText(getContext(), R.string.alert_error, Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
			}
		}
	}

	protected class WsWebChromeClient extends WebChromeClient{
		@Override
		public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
			WebView childView = new WsWebView(getContext());
			WebViewTransport transport = (WebViewTransport) resultMsg.obj;
			transport.setWebView(childView);
			resultMsg.sendToTarget();
			return true;
		}

		@Override
		public void onExceededDatabaseQuota(String url, String databaseIdentifier, long currentQuota, long estimatedSize, long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
			quotaUpdater.updateQuota(estimatedSize * 2);
		}

		@Override
		public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
			final String myOrigin = origin;
			final Callback myCallback = callback;
			AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
			builder.setMessage("Allow current location?");
			builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
				@SuppressLint("NewApi") public void onClick(DialogInterface dialog, int id) {
					myCallback.invoke(myOrigin , true, false);
				}
			});
			builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
				@SuppressLint("NewApi") public void onClick(DialogInterface dialog, int id) {
					myCallback.invoke(myOrigin, false, false);
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		}

		@Override
		public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result) {
			new AlertDialog.Builder(getContext())
			.setMessage(message)
			.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					result.confirm();
				}
			}).setCancelable(false).create().show();

			return true;
		}

		@Override
		public boolean onJsConfirm(WebView view, String url, String message, final android.webkit.JsResult result) {
			new AlertDialog.Builder(getContext())
			.setMessage(message)
			.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					result.confirm();
				}
			})
			.setNegativeButton(android.R.string.cancel, new AlertDialog.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					result.cancel();
				}
			}).setCancelable(false).create().show();
			return true;
		}

		
		public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
			mUploadMessage = uploadMsg;
			Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
			i.addCategory(Intent.CATEGORY_OPENABLE);
			i.setType("*/*");
			((Activity)getContext()).startActivityForResult(Intent.createChooser(i, "사진을 선택하세요"), FILECHOOSER_RESULTCODE);
		}

		@Override
		public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
			super.onReceivedTouchIconUrl(view, url, precomposed);
		}

		@Override
		public void onRequestFocus(WebView view) {
			super.onRequestFocus(view);
		}

		@SuppressLint("NewApi") @Override
		public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
			return super.onConsoleMessage(consoleMessage);
		}

	}

	@SuppressLint("NewApi") 
	public void onActivityResult(int requestCode, int resultCode,  Intent intent) {

		if(resultCode!=Activity.RESULT_OK){
			mUploadMessage.onReceiveValue(null);
			return;
		}

		if (mUploadMessage==null) {
			reload();
			return;
		}

		switch (requestCode){
		case WsWebView.FILECHOOSER_RESULTCODE:
			mCapturedImageURI= (intent == null || resultCode != Activity.RESULT_OK )? null : intent.getData();
			break;

		case WsWebView.CAMERAREQUEST_RESULTCODE:
			if(mCapturedImageURI==null)
			break;
		}

		mUploadMessage.onReceiveValue(mCapturedImageURI);
		//		mUploadMessage = null;
	}

	public void restoreInstanceState(Bundle inState) {
		String url = inState.getString("KeyWebViewUri");
		loadUrl(url);
	}

	public void saveInstanceState(Bundle outState) {
		outState.putString("KeyWebViewUri", getUrl());
	}

	public void requestClearHistory(){
		this.ClearHistory = true;
	}

	public boolean isThisRequest(int requestCode){
		return (requestCode==CAMERAREQUEST_RESULTCODE || requestCode==FILECHOOSER_RESULTCODE);
	}

	@Override
	public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));
		getContext().startActivity(intent);
	}
	
	public void cancelProgressDialog(){
//		pDialog.dismiss();
	}
	
	public void setOnCancelProgressDialogListener(DialogInterface.OnCancelListener listener){
		mOnCancelListener = listener;
	}

}
