package com.jjw.learnKorean.main

import android.graphics.Color.parseColor
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.google.firebase.storage.FirebaseStorage
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.fragment_main_tutorial.*
import java.util.*

class TutorialFragment : androidx.fragment.app.Fragment() {

    val QuizNum = 1
    val progressValue = 0

//    이 방법은 FirebaseStorage 에서 이미지 받아와서 그려주는방법인데 조금 느림
    val fs = FirebaseStorage.getInstance()
    val imagesRef = fs.reference.child("TutorialQuiz/quiz_$QuizNum.PNG")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val tutorialView:View =  inflater.inflate(R.layout.fragment_main_tutorial,container, false)

        getQuizImg()

        progress()
        return tutorialView
    }

    fun progress() {
        Thread(Runnable {
            for (i in 0..5) {
                Runnable {
                    // 화면에 변경하는 작업을 구현
                    quiz_progressBar.progress = progressValue
                }
                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }).start()
    }



    fun getQuizImg(){
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
    }

        companion object { const val TAG = "PlaylistFragment" }
    }

