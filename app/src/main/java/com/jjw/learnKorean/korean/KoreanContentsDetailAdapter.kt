package com.jjw.learnKorean.korean

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
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
import java.util.*

//TODO 이거 나중에는 서버 붙이고 DB 연결해서 koreanContentsList에 하나로 묶어서 param 받아와야됨
class KoreanContentsDetailAdapter(private val context: Context, private val korean_titlte:Array<String>,private val korean_title_sub:Array<String>, private val korean_title_diction:Array<String>) : RecyclerView.Adapter<KoreanContentsDetailAdapter.ViewHolder>() {

    private  lateinit var textToSpeech : TextToSpeech
    private  var selectPosition:Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_korean_detail, parent, false))
    }

    override fun getItemCount(): Int = korean_titlte.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tv_ContentsTitle.text = korean_titlte[position]
        holder.tv_ContentsTitleDiction.text = korean_title_diction[position]
        holder.tv_ContentsTitleSub.text = korean_title_sub[position]

        holder.layout_more.setOnClickListener{

            //스르륵 애니메이션
            val animation: Animation = AlphaAnimation(0f, 1f)

            if(holder.iv_more_down.visibility == View.VISIBLE){

                animation.duration = 1000
                holder.tv_ContentsTitleDiction.animation = animation
                holder.tv_ContentsTitle.animation = animation
                holder.tv_ContentsTitleDiction.visibility = View.VISIBLE
                holder.tv_ContentsTitle.visibility = View.VISIBLE
                holder.iv_more_up.visibility = View.VISIBLE
                holder.iv_more_down.visibility = View.GONE
                holder.btn_korean_voice.visibility = View.VISIBLE
                selectPosition = position

            }else {

                animation.duration = 0
                holder.tv_ContentsTitleDiction.animation = animation
                holder.tv_ContentsTitle.animation = animation
                holder.iv_more_up.visibility = View.GONE
                holder.iv_more_down.visibility = View.VISIBLE
                holder.tv_ContentsTitleDiction.visibility = View.INVISIBLE
                holder.tv_ContentsTitle.visibility = View.GONE
                holder.btn_korean_voice.visibility = View.GONE

                selectPosition = -1
            }
        }

         textToSpeech = TextToSpeech(context, OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                //사용할 언어를 설정
                val result: Int = textToSpeech.setLanguage(Locale.KOREA)
                //언어 데이터가 없거나 혹은 언어가 지원하지 않으면
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                   /* Toast.makeText(context, "이 언어는 지원하지 않습니다.", Toast.LENGTH_SHORT)
                        .show()*/
                } else {
                    holder.btn_korean_voice.isEnabled = true
                    //음성 톤
                    textToSpeech.setPitch(0.8f)
                    //읽는 속도
                    textToSpeech.setSpeechRate(1.0f)
                }
            }
        })

        holder.btn_korean_voice.setOnClickListener {
            speech(holder.tv_ContentsTitle.text.toString())
        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val tv_ContentsTitle: TextView = view.tv_ContentsTitle
        val tv_ContentsTitleDiction: TextView = view.tv_ContentsTitleDiction
        val tv_ContentsTitleSub: TextView = view.tv_ContentsTitleSub
        val layout_more: ConstraintLayout = view.layout_more
        val iv_more_down: ImageView = view.iv_more_down
        val iv_more_up: ImageView = view.iv_more_up
        val btn_korean_voice: ImageView = view.btn_korean_voice
    }

    private fun speech(contents:String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) textToSpeech.speak(
            contents,
            TextToSpeech.QUEUE_FLUSH,
            null,
            null
        ) else textToSpeech.speak(contents, TextToSpeech.QUEUE_FLUSH, null)
    }

}