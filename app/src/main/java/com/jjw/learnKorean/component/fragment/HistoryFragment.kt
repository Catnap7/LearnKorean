package com.jjw.learnKorean.component.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.fragment_main_history.*

class HistoryFragment : androidx.fragment.app.Fragment(){

    //문화에 대해서 소개. 예를들어 얘네가 먹방에서 보여준 떡볶이 라던지 음식 패션 등등
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_history,container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //뷰 설정
        tv_history.text = resources.getString(R.string.coming_soon)

    }
}