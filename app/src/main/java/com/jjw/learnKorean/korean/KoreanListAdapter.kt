package com.jjw.learnKorean.korean

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color.parseColor
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.card_korean_main.view.*
import com.google.firebase.storage.FirebaseStorage
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener


class KoreanListAdapter(private val context: Context, private val koreanContentsList: ArrayList<String>) : RecyclerView.Adapter<KoreanListAdapter.ViewHolder>(){

    private lateinit var activity:Activity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_korean_main, parent, false))
    }

    override fun getItemCount(): Int = koreanContentsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        activity = context as Activity
        holder.tv_VideoName.text = koreanContentsList[position]
/*
        이 방법은 FirebaseStorage 에서 이미지 받아와서 그려주는방법인데 조금 느림
        val fs = FirebaseStorage.getInstance()
        val imagesRef = fs.reference.child("KoreanContentsList/korean_contents_$position.jpg")

        //imagesRef에서 파일 다운로드 URL 가져옴
        imagesRef.downloadUrl.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Glide 이용하여 이미지뷰에 로딩
                Glide.with(activity)
                    .load(task.result)
                    .into(
                        object : SimpleTarget<Drawable>() {
                        override fun onResourceReady(
                            resource: Drawable,
                            transition: com.bumptech.glide.request.transition.Transition<in Drawable>?) {
                            holder.layout_contents.background = resource
                            holder.layout_contents.background.setColorFilter(
                                parseColor("#BDBDBD"),
                                PorterDuff.Mode.MULTIPLY
                            )
                        }
                    })
            } else {
                // URL을 가져오지 못하면 토스트 메세지
                Toast.makeText(activity, task.exception!!.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }*/

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