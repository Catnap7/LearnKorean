package com.jjw.learnKorean.korean

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.activity_koren_koreancontents.*

class KoreanContentsActivity :AppCompatActivity(){

    private val contentsList = arrayListOf<String>("")

    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {

            R.id.filter_brand-> {
                filter_brand.isEnabled = false
                val filterIntent = Intent(this, KoreanContentsFilterActivity::class.java)
//                filterIntent.putExtra("iFiltering", iFiltering)
                startActivityForResult(filterIntent,2)
                this@KoreanContentsActivity.overridePendingTransition(R.anim.slide_in_half, R.anim.fade_out)
            }

           /* R.id.filter_price -> {
                filter_price.isEnabled= false
                val filterIntent = Intent(this, ResultTIreModelSortingActivity::class.java)
                filterIntent.putExtra("iSorting", iSorting)
                startActivityForResult(filterIntent,1)
                this@ResultTireModelActivity.overridePendingTransition(R.anim.slide_in_half, R.anim.fade_out)
            }*/
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koren_koreancontents)

        val layoutManager = LinearLayoutManager(this)
        rv_koreanContents_detail.layoutManager = layoutManager
        rv_koreanContents_detail.adapter = KoreanContentsDetailAdapter(this,contentsList)

    }
}