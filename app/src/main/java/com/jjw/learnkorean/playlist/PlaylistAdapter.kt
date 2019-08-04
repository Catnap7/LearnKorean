package com.jjw.learnkorean.playlist

import android.content.Context
import android.graphics.Typeface
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jjw.learnkorean.R
import kotlinx.android.synthetic.main.card_playlist.view.*
import java.util.ArrayList


class PlaylistAdapter(val context:Context, private val playlist: ArrayList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

     override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

          //미디움 폰트 (폰트경로 설정해야됨 하다 말았음 )
//          val mTextMedium = Typeface.createFromAsset(context.assets, "fonts/NotoSansKR-Medium-Hestia.otf")


     }

     override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {

          return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.card_playlist, p0, false))
     }

     override fun getItemCount(): Int = playlist.size



     inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

          val tv_VideoName: TextView = view.tv_VideoName
          val layout_filter: ConstraintLayout = view.layout_card_playlist
//          val iv_filter: ImageView = view.iv_filter

//          this.mContext = mContext
//          this.activity = mContext as Activity

//          val tvBrand: TextView = view.tv_brand
//          val layout_filter: ConstraintLayout = view.layout_filter
//          val iv_filter: ImageView = view.iv_filter
     }

}


