package com.jjw.learnKorean

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jjw.learnKorean.korean.KoreanFragment
import com.jjw.learnKorean.main.MainAdapter
import com.jjw.learnKorean.main.MainFragment
import com.jjw.learnKorean.notice.NoticeFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.jjw.learnKorean.setting.SettingsFragment
import com.roughike.bottombar.BottomBar


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val settingsFragment = SettingsFragment()
        val mainFragment = MainFragment()
        val koreanFragment = KoreanFragment()
        val noticeFragment = NoticeFragment()

//         val roughikeBottomBar = BottomBar(this)
//        roughikeBottomBar.isIconsOnlyMode = true

        bottomBar.setOnTabSelectListener { tabId ->
            val transaction = supportFragmentManager.beginTransaction()

            when (tabId) {
                R.id.tab_home -> transaction.replace(R.id.contentContainer,mainFragment).commit()
                R.id.tab_korean -> transaction.replace(R.id.contentContainer, koreanFragment).commit()
                R.id.tab_notice -> transaction.replace(R.id.contentContainer, noticeFragment).commit()
                R.id.tab_settings -> transaction.replace(R.id.contentContainer, settingsFragment).commit()
            }
        }
    }
}
