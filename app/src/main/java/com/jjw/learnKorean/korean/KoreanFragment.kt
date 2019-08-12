package com.jjw.learnKorean.korean

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjw.learnKorean.R

 class KoreanFragment : androidx.fragment.app.Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val koreanView = inflater.inflate(R.layout.fragment_korean_main,container, false)


        return koreanView


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }




}
