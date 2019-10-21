package com.jjw.learnKorean.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jjw.learnKorean.R
import com.jjw.learnKorean.common.ToolBarActivity

class OpenSourceActivity :ToolBarActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_opensource)
        mTitle.text = "내정보"
    }
}