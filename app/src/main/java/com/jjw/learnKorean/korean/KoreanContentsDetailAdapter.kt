package com.jjw.learnKorean.korean

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jjw.learnKorean.R

class KoreanContentsDetailAdapter(private val context: Context, private val koreanContentsList: List<String>) : RecyclerView.Adapter<KoreanContentsDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_korean_detail, parent, false))
    }

    override fun getItemCount(): Int = koreanContentsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }



    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

//        val tv_VideoName: TextView = view.textView2
//        val layout_contents: ConstraintLayout = view.layout_korean_contents
//        val layout_playlist: ConstraintLayout = view.layout_card_playlist
//        val layout_youtube_thumbnail: YouTubeThumbnailView = view.layout_youtube_thumbnail
    }
}