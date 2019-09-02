package com.jjw.learnKorean.korean

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.card_korean_detail.view.*

class KoreanContentsDetailAdapter(private val context: Context, private val koreanContentsList: Array<String>) : RecyclerView.Adapter<KoreanContentsDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_korean_detail, parent, false))
    }

    override fun getItemCount(): Int = koreanContentsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tv_contents.text = koreanContentsList[position]

        holder.layout_contents.setOnClickListener{

        }
    }



    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val tv_contents: TextView = view.tv_contents
        val layout_contents: ConstraintLayout = view.layout_contents
//        val layout_youtube_thumbnail: YouTubeThumbnailView = view.layout_youtube_thumbnail
    }
}