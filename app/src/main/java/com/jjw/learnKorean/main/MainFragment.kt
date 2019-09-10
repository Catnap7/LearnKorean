package com.jjw.learnKorean.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.activity_main_main.view.*


class MainFragment : androidx.fragment.app.Fragment() {

    private val adapter by lazy { MainAdapter(activity!!.supportFragmentManager) }
    private lateinit var tapLayout:TabLayout
    private lateinit var vpMainActivity:ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         val mainView = inflater.inflate(R.layout.activity_main_main,container, false)

//        vpMainActivity = mainView.vpMainActivity
//        vpMainActivity.adapter = adapter

        mainView.vpMainActivity.adapter = adapter

        // 탭 레이아웃에 뷰페이저 연결
//        tapLayout = mainView.tabLayout
        mainView.tabLayout.setupWithViewPager(mainView.vpMainActivity)

        return mainView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        // 탭 레이아웃에 뷰페이저 연결
//        tapLayout.setupWithViewPager(vpMainActivity)
    }
}