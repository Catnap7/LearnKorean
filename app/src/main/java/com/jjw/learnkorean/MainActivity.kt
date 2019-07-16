package com.jjw.learnkorean

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jjw.learnkorean.main.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { MainAdapter(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vpMainActivity.adapter = MainActivity@adapter

        // 탭 레이아웃에 뷰페이저 연결
        tabLayout.setupWithViewPager(vpMainActivity)
    }
}
