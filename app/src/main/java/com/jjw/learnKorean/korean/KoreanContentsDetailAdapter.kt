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
import android.view.MotionEvent
import android.view.GestureDetector


//이거 나중에는 서버 붙이고 DB 연결해서 koreanContentsList에 하나로 묶어서 param 받아와야됨
class KoreanContentsDetailAdapter(private val context: Context,
                                  private val korean_titlte:Array<String>,private val korean_title_sub:Array<String>,
                                  private val korean_title_diction:Array<String>,private val korean_content:Array<String>,
                                  private val korean_contents_sub:Array<String>,private val korean_contents_diction:Array<String>) : RecyclerView.Adapter<KoreanContentsDetailAdapter.ViewHolder>() {

    private  var selectPosition:Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_korean_detail, parent, false))
    }

    override fun getItemCount(): Int = korean_titlte.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tv_ContentsTitle.text = korean_titlte[position]
        holder.tv_ContentsTitleDiction.text = korean_title_diction[position]
        holder.tv_ContentsTitleSub.text = korean_title_sub[position]
        holder.tv_contents.text = korean_content[position]
        holder.tv_contentsDiction.text = korean_contents_diction[position]
        holder.tv_contentsSub.text = korean_contents_sub[position]

        holder.layout_contents.setOnClickListener{

            if(position == selectPosition){
                holder.layout_contents_detail.visibility = View.GONE
            }else selectPosition = position

            /*if(holder.layout_contents_detail.visibility==View.VISIBLE) {
                holder.layout_contents_detail.visibility = View.GONE
            }*/
            if(holder.layout_contents_detail.visibility==View.GONE) {
                holder.layout_contents_detail.visibility = View.VISIBLE
            } else holder.layout_contents_detail.visibility = View.GONE
        }

    }



    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val tv_ContentsTitle: TextView = view.tv_ContentsTitle
        val tv_ContentsTitleDiction: TextView = view.tv_ContentsTitleDiction
        val tv_ContentsTitleSub: TextView = view.tv_ContentsTitleSub
        val tv_contents: TextView = view.tv_contents
        val tv_contentsDiction: TextView = view.tv_contentsDiction
        val tv_contentsSub: TextView = view.tv_contentsSub
        val layout_contents: ConstraintLayout = view.layout_contents
        val layout_contents_detail: ConstraintLayout = view.layout_contents_detail
    }
}