package com.jjw.learnKorean.korean

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.activity_koren_koreancontents.*

class KoreanContentsActivity :AppCompatActivity(){

    val contentsList = arrayListOf<String>("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koren_koreancontents)

        val layoutManager = LinearLayoutManager(this)
        rv_koreanContents_detail.layoutManager = layoutManager
        rv_koreanContents_detail.adapter = KoreanContentsDetailAdapter(this,playList)

    }
}