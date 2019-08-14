package com.jjw.learnKorean.korean

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jjw.learnKorean.R

class KoreanListAdapter(private val context: Context, private  val koreanContentsList: ArrayList<String>) : RecyclerView.Adapter<KoreanListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_korean_main, parent, false))
    }

    override fun getItemCount(): Int = koreanContentsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //내용

    }


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

//        val tv_VideoName: TextView = view.tv_VideoTitle
//        val layout_playlist: ConstraintLayout = view.layout_card_playlist
//        val layout_youtube_thumbnail: YouTubeThumbnailView = view.layout_youtube_thumbnail
    }
}