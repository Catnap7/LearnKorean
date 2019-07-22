package com.jjw.learnkorean

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jjw.learnkorean.main.MainAdapter
import com.jjw.learnkorean.main.TutorialFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.jjw.learnkorean.setting.SettingsFragment
import com.roughike.bottombar.BottomBar
import com.roughike.bottombar.BottomBarTab
import com.roughike.bottombar.OnTabReselectListener
import com.roughike.bottombar.OnTabSelectListener
import com.roughike.bottombar.TabSelectionInterceptor


class MainActivity : AppCompatActivity() {

    private val adapter by lazy { MainAdapter(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vpMainActivity.adapter = adapter

        // 탭 레이아웃에 뷰페이저 연결
        tabLayout.setupWithViewPager(vpMainActivity)

        val settingsFragment = SettingsFragment()
        val tutorialFragment = TutorialFragment()

//        val bottomBar = findViewById<BottomBar>(R.id.bottomBar)
        bottomBar.setOnTabSelectListener { tabId ->
            val transaction = supportFragmentManager.beginTransaction()

            when (tabId) {
//                R.id.tab_call_log -> transaction.replace(R.id.contentContainer, tutorialFragment).commit()
//                R.id.tab_contacts -> transaction.replace(R.id.contentContainer, settingsFragment).commit()
//                R.id.tab_macro_setting -> transaction.replace(R.id.contentContainer, setMacroFragment).commit()
            }
        }



    }
}
