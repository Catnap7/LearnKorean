package com.jjw.learnKorean.korean

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.card_korean_detail.view.*


//이거 나중에는 서버 붙이고 DB 연결해서 koreanContentsList에 하나로 묶어서 param 받아와야됨
class KoreanContentsDetailAdapter(private val context: Context,
                                  private val korean_titlte:Array<String>,private val korean_title_sub:Array<String>,
                                  private val korean_title_diction:Array<String>) : RecyclerView.Adapter<KoreanContentsDetailAdapter.ViewHolder>() {

    private  var selectPosition:Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_korean_detail, parent, false))
    }

    override fun getItemCount(): Int = korean_titlte.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tv_ContentsTitle.text = korean_titlte[position]
        holder.tv_ContentsTitleDiction.text = korean_title_diction[position]
        holder.tv_ContentsTitleSub.text = korean_title_sub[position]

        holder.layout_contents.setOnClickListener{

            if(holder.iv_more_down.visibility == View.GONE){
                holder.iv_more_up.visibility = View.GONE
                holder.iv_more_down.visibility = View.VISIBLE
                holder.tv_ContentsTitleDiction.visibility = View.GONE
                holder.tv_ContentsTitleSub.visibility = View.GONE
                selectPosition = -1
            }else {

            //스르륵 애니메이션
            val animation: Animation = AlphaAnimation(0f, 1f)
            animation.duration = 1000
            holder.tv_ContentsTitleDiction.animation = animation
            holder.tv_ContentsTitleSub.animation = animation
            holder.tv_ContentsTitleDiction.visibility = View.VISIBLE
            holder.tv_ContentsTitleSub.visibility = View.VISIBLE
            holder.iv_more_up.visibility = View.VISIBLE
            holder.iv_more_down.visibility = View.GONE
            selectPosition = position
            }

//            selectPosition = position
        }

    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val tv_ContentsTitle: TextView = view.tv_ContentsTitle
        val tv_ContentsTitleDiction: TextView = view.tv_ContentsTitleDiction
        val tv_ContentsTitleSub: TextView = view.tv_ContentsTitleSub
//        val tv_contents: TextView = view.tv_contents
//        val tv_contentsDiction: TextView = view.tv_contentsDiction
//        val tv_contentsSub: TextView = view.tv_contentsSub
        val layout_contents: ConstraintLayout = view.layout_contents
        val iv_more_down: ImageView = view.iv_more_down
        val iv_more_up: ImageView = view.iv_more_up
//        val layout_contents_detail: ConstraintLayout = view.layout_contents_detail
    }
}