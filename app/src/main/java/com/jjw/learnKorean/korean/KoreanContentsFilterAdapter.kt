package com.jjw.learnKorean.korean

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.card_korean_filter.view.*

class KoreanContentsFilterAdapter(private val context: Context, private val  contentList: ArrayList<String>, private var iFiltering:Int) : RecyclerView.Adapter<KoreanContentsFilterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_korean_filter, parent, false))
    }

    override fun getItemCount(): Int = contentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val iPosition: Int = if (holder.adapterPosition != RecyclerView.NO_POSITION) {
            holder.adapterPosition
        } else 0

        val mTextMedium = Typeface.createFromAsset(context.assets, "fonts/NotoSansKR-Medium-Hestia.otf")
        val brand = contentList[iPosition]

        holder.tvBrand.text = brand

        if (iFiltering == position) {
            holder.tvBrand.typeface = mTextMedium
            holder.tvBrand.setTextColor(ContextCompat.getColor(context, R.color.bts_boyWithLove_line_color))
            holder.iv_filter.visibility = View.VISIBLE
        }

        holder.layout_filter.setOnClickListener {

            holder.tvBrand.typeface = mTextMedium
            holder.tvBrand.setTextColor(ContextCompat.getColor(context, R.color.bts_boyWithLove_line_color))
            holder.iv_filter.visibility = View.VISIBLE
            val activity = context as KoreanContentsFilterActivity
            activity.setBrandFilter(position.toString(), brand)
            activity.overridePendingTransition(R.anim.stay, R.anim.anim_slide_out_bottom)

            if (position !=iFiltering){
                holder.tvBrand.setTypeface(null, Typeface.NORMAL)
                holder.tvBrand.setTextColor(ContextCompat.getColor(context, R.color.bts_boyWithLove_line_color))
                holder.iv_filter.visibility = View.INVISIBLE
            }
        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvBrand: TextView = view.tv_brand
        val layout_filter: ConstraintLayout = view.layout_filter
        val iv_filter: ImageView = view.iv_filter
    }

}
