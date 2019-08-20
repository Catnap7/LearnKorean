package com.jjw.learnKorean.korean

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.card_korean_main.view.*
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.SimpleTarget
import android.graphics.PorterDuff
import android.graphics.Color.parseColor


class KoreanListAdapter(private val context: Context, private  val koreanContentsList: List<String>) : RecyclerView.Adapter<KoreanListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_korean_main, parent, false))
    }

    override fun getItemCount(): Int = koreanContentsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.tv_VideoName.text = koreanContentsList[position]

        val backGroundId = context.resources.getIdentifier("korean_contents_$position", "drawable", context.packageName)

        //배경이미지 삽입
        Glide.with(context).load(backGroundId).into(object : SimpleTarget<Drawable>() {
            override fun onResourceReady(
                resource: Drawable,
                transition: com.bumptech.glide.request.transition.Transition<in Drawable>?){
                holder.layout_contents.background = resource
                holder.layout_contents.background.setColorFilter(parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY)
            }
        })

        holder.layout_contents.setOnClickListener {

            Intent(context, KoreanContentsActivity::class.java).let {
                it.putExtra("videoId", koreanContentsList[position])
                context.startActivity(it)
            }
        }

    }
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val tv_VideoName: TextView = view.textView2
        val layout_contents: ConstraintLayout = view.layout_korean_contents
//        val layout_playlist: ConstraintLayout = view.layout_card_playlist
//        val layout_youtube_thumbnail: YouTubeThumbnailView = view.layout_youtube_thumbnail
    }
}