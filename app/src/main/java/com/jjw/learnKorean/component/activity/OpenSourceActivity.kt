package com.jjw.learnKorean.component.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jjw.learnKorean.R

class OpenSourceActivity :AppCompatActivity(){
    var TAG = "OpenSourceActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_opensource)
//        mTitle.text = "내정보"
//        toolbar_title.text = "OpenSource"
    }
}