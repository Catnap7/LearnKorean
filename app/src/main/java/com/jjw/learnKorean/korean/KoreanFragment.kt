package com.jjw.learnKorean.korean

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.fragment_korean_main.view.*

class KoreanFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val koreanView = inflater.inflate(R.layout.fragment_korean_main,container, false)

        val koreanContentsList = arrayListOf("practical expression","a Korean proverb","an expression on the weather","an expression of taste","express used in restaurants")

        koreanView.rv_koreanContents.layoutManager = LinearLayoutManager(activity!!)
        koreanView.rv_koreanContents.adapter = KoreanListAdapter(activity!!,koreanContentsList)

        return koreanView

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}
