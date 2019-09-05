package com.jjw.learnKorean.korean

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.activity_koren_koreancontents.*
import android.view.WindowManager
import android.widget.TextView
import com.jjw.learnKorean.common.KoreanContents


class KoreanContentsActivity :AppCompatActivity(){

    private var iFiltering:String = "0"
    private var koreanContentsList:ArrayList<String>? = null
    private lateinit var koreanContentsDetailAdapter:KoreanContentsDetailAdapter
//    arrayList_PointHistory = ArrayList<PointHistory>()

    //컨텐츠의 메인 단어들
//    private lateinit var contentsList:ArrayList<String>

    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {

            R.id.filter_brand-> {
                filter_brand.isEnabled = false
                val filterIntent = Intent(this, KoreanContentsFilterActivity::class.java)
                filterIntent.putExtra("koreanContentsList", koreanContentsList)
                filterIntent.putExtra("iFiltering", iFiltering)
                startActivityForResult(filterIntent,2)
                this@KoreanContentsActivity.overridePendingTransition(R.anim.slide_in_half, R.anim.fade_out)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //상태바 없앰
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_koren_koreancontents)

        if(intent.hasExtra("iFiltering")) {
            iFiltering = intent.getStringExtra("iFiltering")
        }
        if(intent.hasExtra("koreanContentsList")) {
            koreanContentsList = intent.getStringArrayListExtra("koreanContentsList")
        }

        filter_brand.setOnClickListener(onClickListener)

        val koreanContents = KoreanContents()

//        val tv_ratingID = resources.getIdentifier("koreanContents_$iFiltering", "id", "com.jjw.learnKorean")

        val korean_titlte = koreanContents.korean_title_2
        val korean_title_sub = koreanContents.korean_title_sub_2
        val korean_title_diction = koreanContents.korean_title_diction_2
        val korean_contents = koreanContents.korean_contents_2
        val korean_contents_sub = koreanContents.korean_contents_sub_2
        val korean_contents_diction = koreanContents.korean_contents_diction_2

        koreanContentsDetailAdapter = KoreanContentsDetailAdapter(this,korean_titlte,korean_title_sub,korean_title_diction,korean_contents,korean_contents_sub,korean_contents_diction)

        val layoutManager = LinearLayoutManager(this)
        rv_koreanContents_detail.layoutManager = layoutManager
        rv_koreanContents_detail.adapter = koreanContentsDetailAdapter



        filter_brand.text = koreanContentsList?.get(Integer.parseInt(iFiltering)) ?: "Contents List"

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            2 -> if (resultCode == Activity.RESULT_OK){
                iFiltering = data!!.getStringExtra("iFiltering")
//                val filteringList = java.util.ArrayList<ResultTireModel>()

                filter_brand.text = data.getStringExtra("koreanContentsTitle")
//                koreanContentsDetailAdapter.clear()
//                    resultTireModelFilteredList.addAll(filteringList)
//                    resultTireModelAdapter!!.notifyDataSetChanged()
//                    setTireResultSorting(iSorting)

                }
//                filter_brand.text = data.getStringExtra("tire_brand_name")

            }
        filter_brand.isEnabled = true
        }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                finish()
                overridePendingTransition(R.anim.stay, R.anim.anim_slide_out_right)
                false
            }
            else -> false
        }
    }
}