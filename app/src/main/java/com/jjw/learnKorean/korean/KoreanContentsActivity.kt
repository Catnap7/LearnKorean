package com.jjw.learnKorean.korean

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.activity_koren_koreancontents.*

class KoreanContentsActivity :AppCompatActivity(){

    private var iFiltering:String = "0"
    private var koreanContentsList:ArrayList<String>? = null
    private lateinit var koreanContentsDetailAdapter:KoreanContentsDetailAdapter

    //컨텐츠의 메인 단어들
    private val contentsList = arrayListOf<String>("")

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
        setContentView(R.layout.activity_koren_koreancontents)

        filter_brand.setOnClickListener(onClickListener)
        koreanContentsDetailAdapter = KoreanContentsDetailAdapter(this,contentsList)

        val layoutManager = LinearLayoutManager(this)
        rv_koreanContents_detail.layoutManager = layoutManager
        rv_koreanContents_detail.adapter = koreanContentsDetailAdapter

        if(intent.hasExtra("iFiltering")) {
            iFiltering = intent.getStringExtra("iFiltering")
        }
        if(intent.hasExtra("koreanContentsList")) {
            koreanContentsList = intent.getStringArrayListExtra("koreanContentsList")
        }

        filter_brand.text = koreanContentsList?.get(Integer.parseInt(iFiltering)) ?: "Contents List"

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            2 -> if (resultCode == Activity.RESULT_OK){
                iFiltering = data!!.getStringExtra("iFiltering")
//                val filteringList = java.util.ArrayList<ResultTireModel>()


//                koreanContentsDetailAdapter.clear()
//                    resultTireModelFilteredList.addAll(filteringList)
//                    resultTireModelAdapter!!.notifyDataSetChanged()
//                    setTireResultSorting(iSorting)
                filter_brand.isEnabled = true
                }
//                filter_brand.text = data.getStringExtra("tire_brand_name")

            }
        }
}