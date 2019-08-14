package com.jjw.learnKorean.korean

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.fragment_korean_main.*
import kotlinx.android.synthetic.main.fragment_korean_main.view.*

class KoreanFragment : androidx.fragment.app.Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val koreanView = inflater.inflate(R.layout.fragment_korean_main,container, false)


//        koreanView.layout_bts_expression.setOnClickListener(onClickListener)
        val koreanContentsList = ArrayList<String>(1)

        koreanView.rv_koreanContents.layoutManager = LinearLayoutManager(activity)
        koreanView.rv_koreanContents.adapter = KoreanListAdapter(activity!!,koreanContentsList)

        return koreanView

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private val onClickListener = View.OnClickListener { v ->

        val id = v.id
        val reservationIntent = Intent(context , KoreanContentsActivity::class.java)
        reservationIntent.putExtra("koreanContents", id)
        startActivity(reservationIntent)

    }

}
