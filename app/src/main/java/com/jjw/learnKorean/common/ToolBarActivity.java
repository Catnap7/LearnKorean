package com.jjw.learnKorean.common;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jjw.learnKorean.R;

import java.util.Objects;

public abstract class ToolBarActivity extends AppCompatActivity{

//    protected Mng_Cache cache;
    private static Typeface mTypeface = null;
//    protected Tracker mTracker;
    protected String sScreenName = "";
    protected String sTag = "";
    protected TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 케쉬데이타초기화
//        cache = Mng_Cache.getInstance();

//        TirebidsApplication application = (TirebidsApplication) getApplication();
//        mTracker = application.getDefaultTracker();
//        mTracker.enableAdvertisingIdCollection(true);

        // TOP Menu
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_top_prev);

        // 상태바 아이콘 색상변경
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        cache.putPrefString("ACTIVITY_NAME", sTag);
//        mTracker.setScreenName(sScreenName);
//        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.slide_out_right);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.slide_out_right);
            return false;
        }
        return false;
    }
}
