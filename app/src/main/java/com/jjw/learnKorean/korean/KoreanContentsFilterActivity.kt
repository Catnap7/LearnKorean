package com.jjw.learnKorean.korean

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.activity_korean_filter.*
import java.util.ArrayList

class KoreanContentsFilterActivity :AppCompatActivity(){

        private var iFiltering = "0"

        private val onClickListener = View.OnClickListener { v ->
            when (v.id) {

                R.id.iv_close -> {
                    finish()
                    overridePendingTransition(R.anim.stay, R.anim.anim_slide_out_bottom)
                }
            }
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_korean_filter)
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.setGravity(Gravity.BOTTOM)

            val disp = applicationContext.resources.displayMetrics
            val width = disp.widthPixels
            val height = (disp.heightPixels * 0.7).toInt()
            var contentsList = ArrayList<String>()

            window.attributes.width = width
            window.attributes.height = height

            if(intent.hasExtra("iFiltering")) {
                iFiltering = intent.getStringExtra("iFiltering")
            }
            if(intent.hasExtra("contentsList")) {
                contentsList = intent.getStringArrayListExtra("contentsList")
            }

            val resultTireModelFilterAdapter = ResultTireModelFilterAdapter(this,contentsList,Integer.parseInt(iFiltering))

            recycler_view_brand.isNestedScrollingEnabled = false
            recycler_view_brand.layoutManager = LinearLayoutManager(this)
            recycler_view_brand.adapter = resultTireModelFilterAdapter

            iv_close.setOnClickListener(onClickListener)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                android.R.id.home -> {
                    finish()
                    overridePendingTransition(R.anim.stay, R.anim.anim_slide_out_bottom)
                    return true
                }
            }
            return super.onOptionsItemSelected(item)
        }

        fun setBrandFilter(selectFiltering:String ,contentsTitle: String){

            iFiltering = selectFiltering
            setIntent(contentsTitle)
            finish()
            overridePendingTransition(R.anim.stay, R.anim.anim_slide_out_bottom)
        }

        private fun setIntent(tireBrandName: String){
            Intent(this, KoreanContentsActivity::class.java).let{
                it.putExtra("iFiltering",iFiltering)
                it.putExtra("tire_brand_name",tireBrandName)
                setResult(Activity.RESULT_OK,it)
            }

        }

        override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
            return when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    finish()
                    overridePendingTransition(R.anim.stay, R.anim.anim_slide_out_bottom)
                    false
                }
                else -> false
            }
        }

        companion object { var TAG = "ResultTIreModelFilterActivity" }
    }


