package com.jjw.learnKorean.main

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

    //Histtory 말고 멤버별 섹션이라던지 딴걸로 교체해야될듯
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_history,container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //뷰 설정
        tv_history.text = resources.getString(R.string.coming_soon)

    }
}