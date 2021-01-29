package com.jjw.learnKorean.component.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjw.learnKorean.R
import com.jjw.learnKorean.adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main_main.view.*


class MainFragment : androidx.fragment.app.Fragment() {

    private val adapter by lazy {
        MainAdapter(
            requireActivity().supportFragmentManager
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         val mainView = inflater.inflate(R.layout.activity_main_main,container, false)

        mainView.vpMainActivity.adapter = adapter

        // 탭 레이아웃에 뷰페이저 연결
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