package com.jjw.learnKorean.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.fragment_main_tutorial.*
import android.os.CountDownTimer

class TutorialFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_grade,container, false)
    }

    /*fun OnClickHandler() {
        val builder = AlertDialog.Builder(context)

        builder.setTitle("").setMessage("Get Ready")

        builder.setPositiveButton("START") { _, _ ->
            getQuizImg()
            progress()
        }
        *//* builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id -> Toast.makeText(
                     context, "Cancel Click", Toast.LENGTH_SHORT).show()
             })

         builder.setNeutralButton("Neutral", DialogInterface.OnClickListener { dialog, id ->
                 Toast.makeText(context, "Neutral Click", Toast.LENGTH_SHORT).show()
             })
         *//*
        val alertDialog = builder.create()
        alertDialog.show()
    }*/

    //퀴즈 타이머
    fun progress() {

        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                 pb_quiz.progress = (millisUntilFinished / 1000).toInt()
            }
            override fun onFinish() {
                Toast.makeText(context, "Time out!", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    /*fun getQuizImg(){
        //imagesRef에서 파일 다운로드 URL 가져옴
        imagesRef.downloadUrl.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Glide 이용하여 이미지뷰에 로딩
                Glide.with(context!!)
                    .load(task.result)
                    .into(
                        object : SimpleTarget<Drawable>() {
                            override fun onResourceReady(
                                resource: Drawable,
                                transition: com.bumptech.glide.request.transition.Transition<in Drawable>?) {
                                image_quiz.background = resource
                                image_quiz.background.setColorFilter(
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
        }
    }*/
        companion object { const val TAG = "PlaylistFragment" }
    }
