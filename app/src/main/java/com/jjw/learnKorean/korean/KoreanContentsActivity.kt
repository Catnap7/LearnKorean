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
    private lateinit var korean_title:Array<String>
    private lateinit var korean_title_sub:Array<String>
    private lateinit var korean_title_diction:Array<String>

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
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_koren_koreancontents)

        if(intent.hasExtra("iFiltering")) {
            iFiltering = intent.getStringExtra("iFiltering").toString()
        }
        if(intent.hasExtra("koreanContentsList")) {
            koreanContentsList = intent.getStringArrayListExtra("koreanContentsList")
        }

        filter_brand.setOnClickListener(onClickListener)

        initRecyclerView()

        filter_brand.text = koreanContentsList?.get(Integer.parseInt(iFiltering)) ?: "Contents List"

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            2 -> if (resultCode == Activity.RESULT_OK){
                iFiltering = data!!.getStringExtra("iFiltering").toString()
                filter_brand.text = data.getStringExtra("koreanContentsTitle")
                initRecyclerView()
                }
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

   private fun initRecyclerView(){

        val koreanContents = KoreanContents()

        //TODO 컨텐츠 가져올때 좀 더 효율적으로 들고와야됨 지금은 일단 임시방편임
        when(iFiltering){

            //실용
            "0" -> {
                korean_title = koreanContents.korean_title_0
                korean_title_sub = koreanContents.korean_title_sub_0
                korean_title_diction = koreanContents.korean_title_diction_0
            }
            //속담
            "1" -> {
                korean_title = koreanContents.korean_title_1
                korean_title_sub = koreanContents.korean_title_sub_1
                korean_title_diction = koreanContents.korean_title_diction_1
            }
            //날씨
            "2" -> {
                korean_title = koreanContents.korean_title_2
                korean_title_sub = koreanContents.korean_title_sub_2
                korean_title_diction = koreanContents.korean_title_diction_2
            }
            //맛
            "3" -> {
                korean_title = koreanContents.korean_title_3
                korean_title_sub = koreanContents.korean_title_sub_3
                korean_title_diction = koreanContents.korean_title_diction_3
            }
            //식당
            "4" -> {
                korean_title = koreanContents.korean_title_4
                korean_title_sub = koreanContents.korean_title_sub_4
                korean_title_diction = koreanContents.korean_title_diction_4
            }
            //???
            "5" -> {
                korean_title = koreanContents.korean_title_4
                korean_title_sub = koreanContents.korean_title_sub_4
                korean_title_diction = koreanContents.korean_title_diction_4
            }

        }
        koreanContentsDetailAdapter = KoreanContentsDetailAdapter(this,korean_title,korean_title_sub,korean_title_diction)

        LinearLayoutManager(this).let{
            rv_koreanContents_detail.layoutManager = it
            rv_koreanContents_detail.adapter = koreanContentsDetailAdapter
        }
    }
}