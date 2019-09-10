package com.jjw.learnKorean

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jjw.learnKorean.korean.KoreanFragment
import com.jjw.learnKorean.main.MainAdapter
import com.jjw.learnKorean.main.MainFragment
import com.jjw.learnKorean.notice.NoticeFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.jjw.learnKorean.setting.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val settingsFragment = SettingsFragment()
        val mainFragment = MainFragment()
        val koreanFragment = KoreanFragment()
        val noticeFragment = NoticeFragment()

        //첫화면 지정
        supportFragmentManager.beginTransaction().replace(R.id.contentContainer, mainFragment).commitAllowingStateLoss()

        bottomBar.setOnNavigationItemSelectedListener { item ->

            val transaction = supportFragmentManager.beginTransaction()

            when(item.itemId){
                R.id.tab_home -> transaction.replace(R.id.contentContainer,mainFragment).commitAllowingStateLoss()
                R.id.tab_korean -> transaction.replace(R.id.contentContainer, koreanFragment).commitAllowingStateLoss()
                R.id.tab_notice -> transaction.replace(R.id.contentContainer, noticeFragment).commitAllowingStateLoss()
                R.id.tab_settings -> transaction.replace(R.id.contentContainer, settingsFragment).commitAllowingStateLoss()
            }
            return@setOnNavigationItemSelectedListener true
        }

    }
}
