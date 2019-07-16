package com.jjw.learnkorean.main

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.jjw.learnkorean.R
import kotlinx.android.synthetic.main.fragment_main_tutorial.*
import android.util.Log


class TutorialFragment : Fragment(), SurfaceHolder.Callback {

    private lateinit var surfaceView: SurfaceView
    private lateinit var surfaceHolder: SurfaceHolder
    private lateinit var mediaPlayer: MediaPlayer

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {

        if (mediaPlayer != null) {
            mediaPlayer.release()
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
        } else {
            mediaPlayer.reset()
        }

        try {

            val path = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
            mediaPlayer.setDataSource(path)

            //mediaPlayer.setVolume(0, 0); //볼륨 제거
            mediaPlayer.setDisplay(surfaceHolder) // 화면 호출
            mediaPlayer.prepare() // 비디오 load 준비

            //mediaPlayer.setOnCompletionListener(completionListener); // 비디오 재생 완료 리스너

            mediaPlayer.start()

        } catch (e: Exception) {
            Log.e("MyTag", "surface view error : " + e.message)
        }


    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_tutorial,container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        surfaceHolder = surfaceView.holder
        surfaceHolder.addCallback(this)
        //뷰 설정
        tv_fragment_main_tutorial.text = "fragment_main_tutorial"
    }
}