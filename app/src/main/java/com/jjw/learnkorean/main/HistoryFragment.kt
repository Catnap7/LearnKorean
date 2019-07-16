package com.jjw.learnkorean.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjw.learnkorean.R
import kotlinx.android.synthetic.main.fragment_main_tutorial.*

class HistoryFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_tutorial,container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //뷰 설정
        tv_fragment_main_tutorial.text = "fragment_main_history"
    }
}