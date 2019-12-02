package com.jjw.learnKorean.playlist

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.jjw.learnKorean.R
import com.jjw.learnKorean.common.Subtitles
import kotlinx.android.synthetic.main.activity_playlist_video.*

class VideoActivity  : AppCompatActivity() {

    private lateinit var mYoutubePlayerFragment : YouTubePlayerSupportFragment
    private var threadStopflag = true
    private var handler = Handler()
    private var timer:Int = -2
    private var position:Int = 0
    private lateinit var videoId:String
    private val db:FirebaseFirestore  = FirebaseFirestore.getInstance()

    private val youtubeListener = object: YouTubePlayer.OnInitializedListener{

        override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
            Toast.makeText(this@VideoActivity, "Content load fail..", Toast.LENGTH_SHORT).show()
        }

        override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer, isReady: Boolean) {
            if (!isReady) {
                youtubePlayer.setPlaybackEventListener(playbackEventListener)
                youtubePlayer.setPlayerStateChangeListener(playerStateChangeListener)

                //TODO 플레이어 스타일 설정 CHROMELESS ( 동영상 진행 progressbar 및 멈춤기능없음), minimal (멈춤이랑 progressbar만 있음)
                //디버깅용
//                youtubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                //배포용
                youtubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS)

                //TODO cuevideo 동영상만 로드 밑에꺼는 바로 실행됨
                //디버깅용
//                youtubePlayer.cueVideo(videoId)
                //배포용
                youtubePlayer.loadVideo(videoId)

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

        tv_VideoTitle.text = intent.getStringExtra("videoTitle")

        mYoutubePlayerFragment = YouTubePlayerSupportFragment()
        mYoutubePlayerFragment.initialize(resources.getString(R.string.youtube_api_key), youtubeListener)

        supportFragmentManager!!.beginTransaction().apply {
            replace(R.id.youtube_fragment, mYoutubePlayerFragment as Fragment)
            commit()
        }
    }

    private fun startSubtitles(){
        val sub = Subtitles()
        var subIndex = 0
        lateinit var koreanSub:Array<String>
        lateinit var koreanSubTime:Array<Int>
        lateinit var koreanSubtitlesDiction:Array<String>
        lateinit var subtitles:Array<String>

        when(position){
            0 -> {
                koreanSub = sub.N76HNPfI4zs
                koreanSubTime = sub.N76HNPfI4zs_time
                subtitles = sub.N76HNPfI4zs_sub
                koreanSubtitlesDiction = sub.N76HNPfI4zs_diction
            }
            1 -> {
                koreanSub = sub.mw_JgIonmD8
                koreanSubTime = sub.mw_JgIonmD8_time
                subtitles = sub.mw_JgIonmD8_sub
                koreanSubtitlesDiction = sub.mw_JgIonmD8_diction
            }
            2 -> {
                koreanSub = sub.wksUmcAx7z4
                koreanSubTime = sub.wksUmcAx7z4_time
                subtitles = sub.wksUmcAx7z4_sub
                koreanSubtitlesDiction = sub.wksUmcAx7z4_diction
            }
            3 -> {
                koreanSub = sub.A4lKqFR_67RI
                koreanSubTime = sub.A4lKqFR_67RI_time
                subtitles = sub.A4lKqFR_67RI_sub
                koreanSubtitlesDiction = sub.A4lKqFR_67RI_diction
            }
            4 -> {
                koreanSub = sub.A3_FXW0CW_8o
                koreanSubTime = sub.A3_FXW0CW_8o_time
                subtitles = sub.A3_FXW0CW_8o_sub
                koreanSubtitlesDiction = sub.A3_FXW0CW_8o_diction
            }
            5 -> {
                koreanSub = sub.gqvEO6o1Mx8
                koreanSubTime = sub.gqvEO6o1Mx8_time
                subtitles = sub.gqvEO6o1Mx8_sub
                koreanSubtitlesDiction = sub.gqvEO6o1Mx8_diction
            }
            6 -> {
                koreanSub = sub.FsEGaURjZ8w
                koreanSubTime = sub.FsEGaURjZ8w_time
                subtitles = sub.FsEGaURjZ8w_sub
                koreanSubtitlesDiction = sub.FsEGaURjZ8w_diction
            }
            7 -> {
                koreanSub = sub.fgja5tdRB8o
                koreanSubTime = sub.fgja5tdRB8o_time
                subtitles = sub.fgja5tdRB8o_sub
                koreanSubtitlesDiction = sub.fgja5tdRB8o_diction
            }
        }

        val thread = Thread(Runnable {
            while (threadStopflag) {
                try {
                    handler.post {

//                        tv_VideoName.text=timer.toString()
                        if(koreanSubTime.contains(timer)) {
                            tv_koreanSubtitles.text = koreanSub[subIndex]
                            tv_koreanSubtitlesDiction.text = koreanSubtitlesDiction[subIndex]
                            tv_subtitles.text = subtitles[subIndex]
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