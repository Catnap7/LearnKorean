package com.jjw.learnKorean.playlist

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.jjw.learnKorean.R
import com.jjw.learnKorean.common.Subtitles
import kotlinx.android.synthetic.main.activity_playlist_video.*

class VideoActivity  : AppCompatActivity() {

    private lateinit var mYoutubePlayerFragment : YouTubePlayerSupportFragment
    private var threadStopflag = true
    private var handler = Handler()
    private var timer:Int = -3
    private lateinit var videoId:String

    private val youtubeListener = object: YouTubePlayer.OnInitializedListener{

        override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
            Toast.makeText(this@VideoActivity, "Content load fail..", Toast.LENGTH_SHORT).show()
        }

        override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer, isReady: Boolean) {
            if (!isReady) {
                youtubePlayer.setPlaybackEventListener(playbackEventListener)
                youtubePlayer.setPlayerStateChangeListener(playerStateChangeListener)
                //플레이어 스타일 설정 CHROMELESS ( 동영상 진행 progressbar 및 멈춤기능없음), minimal (멈춤이랑 progressbar만 있음)
                youtubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                youtubePlayer.cueVideo(videoId)

                //전체화면 버튼 숨김
                youtubePlayer.setShowFullscreenButton(false)
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist_video)

         videoId = if(intent.hasExtra("videoId")) {
            intent.getStringExtra("videoId")
        }else "XsX3ATc3FbA" //작은 것들을 위한 시

        mYoutubePlayerFragment = YouTubePlayerSupportFragment()
        mYoutubePlayerFragment.initialize(resources.getString(R.string.youtube_api_key), youtubeListener)

        supportFragmentManager!!.beginTransaction().apply {
            replace(R.id.youtube_fragment, mYoutubePlayerFragment)
            commit()
        }
    }


    private fun startSubtitles(){
        val sub = Subtitles()
        var subIndex = 0

        //sub.N76HNPfI4zs
        val subVal = "sub.$videoId"

        var tv_resID = resources.getIdentifier(subVal, "Array", "com.jjw.learnKorean")

        val koreanSub = tv_resID
        val koreanSubTime = sub.N76HNPfI4zs_time

        val thread = Thread(Runnable {
            while (threadStopflag) {
                try {
                    handler.post {

                        tv_VideoName.text=timer.toString()
                        if(koreanSubTime.contains(timer)) {
                            tv_koreanSubtitles.text = koreanSub[subIndex]
                            subIndex++
                        }
                    }
                    timer++

                    Thread.sleep(1000)

                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        })

        thread.start()
    }

    private val  playbackEventListener: YouTubePlayer.PlaybackEventListener = object: YouTubePlayer.PlaybackEventListener{

        override fun onSeekTo(p0: Int) {}

        override fun onBuffering(p0: Boolean) {}

        override fun onPlaying() {
            threadStopflag = true
        }

        override fun onStopped() {
        }

        override fun onPaused() {
            threadStopflag = false
        }
    }
    private val playerStateChangeListener: YouTubePlayer.PlayerStateChangeListener = object: YouTubePlayer.PlayerStateChangeListener {

        override fun onAdStarted() {
        }

        override fun onLoading() {
        }

        override fun onVideoStarted() {
            startSubtitles()
        }

        override fun onLoaded(p0: String?) {
        }

        override fun onVideoEnded() {
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }
    }


}