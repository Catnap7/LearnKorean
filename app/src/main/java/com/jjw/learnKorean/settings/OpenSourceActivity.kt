package com.jjw.learnKorean.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jjw.learnKorean.R
import com.jjw.learnKorean.common.ToolBarActivity
import kotlinx.android.synthetic.main.activity_settings_opensource.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class OpenSourceActivity :AppCompatActivity(){
    var TAG = "OpenSourceActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_opensource)
//        mTitle.text = "내정보"
//        toolbar_title.text = "OpenSource"
    }
}