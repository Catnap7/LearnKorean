package com.jjw.learnKorean;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.widget.LinearLayout;

import com.jjw.learnKorean.common.ToolBarActivity;
import com.jjw.learnKorean.common.WsApplication;
import com.jjw.learnKorean.common.WsWebView;
import com.jjw.learnKorean.http.WsHttpClient;


/**
 * Created by zinang on 2018. 1. 11..
 */

public class WebViewActivity extends ToolBarActivity {

    private final String TAG = this.getClass().getSimpleName();
    private static final String FinishActivity = "MainActivity";
    private WsWebView webview;

    private String path = "", title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_webview);
        super.onCreate(savedInstanceState);

        path = getIntent().getStringExtra("path");
        title = getIntent().getStringExtra("title");

        boolean margin = getIntent().getBooleanExtra("margin", false);

        mTitle.setText(title);
        sScreenName = title + ":WebViewActivity";
        sTag = TAG;

        WsHttpClient.getThreadSafeClient();

        webview = findViewById(R.id.webview);
        webview.addJavascriptInterface(new JsCallAndroid(), "Android");

        if(margin) {
            LinearLayout.LayoutParams params =  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

            int default_margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
            params.setMargins(default_margin,0,default_margin,0);
            webview.setLayoutParams(params);
        }

        webview.loadUrl(path);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (WsApplication.ExitApp) {
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.anim_slide_out_right);
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (webview.isThisRequest(requestCode))
            webview.onActivityResult(requestCode, resultCode, intent);
    }

    private void webGoBack() {
        webview.stopLoading();
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.anim_slide_out_right);
        }
    }

    // --------------------------------------------
    // Back버튼 Home버튼
    // --------------------------------------------
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:

                if (webview.canGoBack()) {
                    webview.goBack();
                } else {
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.anim_slide_out_right);
                }
                return false;
            default:
                return false;
        }
    }

    private class JsCallAndroid {
        @JavascriptInterface
        public void callAndroid(final String arg) {
            if (arg.equals(FinishActivity)) {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.anim_slide_out_right);
            }

            if (arg.equals("filechooser")) {
            }
        }
    }
}
