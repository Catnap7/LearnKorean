package com.jjw.learnKorean

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.jjw.learnKorean.korean.KoreanFragment
import com.jjw.learnKorean.main.MainFragment
import com.jjw.learnKorean.notice.NoticeFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.jjw.learnKorean.settings.SettingsFragment
import com.uxcam.UXCam
import com.google.android.gms.ads.AdListener



class MainActivity : AppCompatActivity() {

    lateinit var mAdView : AdView
    lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO UXCam 트래킹 시작
        UXCam.startWithKey(resources.getString(R.string.UXCam_app_key))

        //구글 애드몹 광고 추가
        MobileAds.initialize(this) {}
        //하단 배너광고
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        //전면광고
        mInterstitialAd = InterstitialAd(this)
        //TODO 전면광고 _for_test
        mInterstitialAd.adUnitId = resources.getString(R.string.Interstitial_ad_unit_id)
        mInterstitialAd.loadAd(adRequest)

        //첫화면 지정
        MainFragment().let {
            supportFragmentManager.beginTransaction().add(R.id.contentContainer, it).commitAllowingStateLoss()
        }

        bottomBar.setOnNavigationItemSelectedListener { item ->

            val settingsFragment = SettingsFragment()
            val mainFragment = MainFragment()
            val koreanFragment = KoreanFragment()
//            val noticeFragment = NoticeFragment()
            val transaction = supportFragmentManager.beginTransaction()

            when(item.itemId){
                R.id.tab_home -> transaction.replace(R.id.contentContainer,mainFragment).commitAllowingStateLoss()
                R.id.tab_korean -> transaction.replace(R.id.contentContainer, koreanFragment).commitAllowingStateLoss()
//                R.id.tab_bts -> transaction.replace(R.id.contentContainer, btsFragment).commitAllowingStateLoss()
                R.id.tab_settings -> transaction.replace(R.id.contentContainer, settingsFragment).commitAllowingStateLoss()
            }
            return@setOnNavigationItemSelectedListener true
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
            mInterstitialAd.adListener = object : AdListener() {
                override fun onAdClosed() {
                    // 사용자가 광고를 닫으면 뒤로가기 이벤트를 발생시킨다.
                    finish()
                }
            }
        } else {
            super.onBackPressed()
        }
    }

}
