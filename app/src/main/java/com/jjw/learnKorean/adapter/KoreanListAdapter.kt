package com.jjw.learnKorean.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.card_korean_main.view.*
import com.jjw.learnKorean.component.activity.KoreanContentsActivity


class KoreanListAdapter(private val context: Context, private val koreanContentsList: ArrayList<String>) : RecyclerView.Adapter<KoreanListAdapter.ViewHolder>(){

    private lateinit var activity:Activity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_korean_main, parent, false))
    }

    override fun getItemCount(): Int = koreanContentsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tv_VideoName.text = koreanContentsList[position]


        /* //배경이미지 삽입

//        val backGroundId = context.resources.getIdentifier("korean_contents_$position", "drawable", context.packageName)

 Glide.with(context).load(backGroundId).into(object : SimpleTarget<Drawable>() {
     override fun onResourceReady(
         resource: Drawable,
         transition: com.bumptech.glide.request.transition.Transition<in Drawable>?){
         holder.layout_contents.background = resource
         holder.layout_contents.background.setColorFilter(parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY)
     }
 })*/


        holder.layout_contents.setOnClickListener {

            Intent(activity, KoreanContentsActivity::class.java).let {
                it.putExtra("iFiltering", position.toString())
                it.putStringArrayListExtra("koreanContentsList", koreanContentsList)
                activity.startActivity(it)
                activity.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.fade_out)
            }
        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val tv_VideoName: TextView = view.textView2
        val layout_contents: ConstraintLayout = view.layout_korean_contents
    }
}