package com.jjw.learnKorean.main


import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.jjw.learnKorean.R
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.jjw.learnKorean.common.Subtitles
import kotlinx.android.synthetic.main.fragment_main_tutorial.*
import kotlinx.android.synthetic.main.fragment_main_tutorial.view.*


class TutorialFragment : androidx.fragment.app.Fragment(){


    //진정국 하트
//    private val videoID = "N76HNPfI4zs"
    //작은 것들을 위한 시
    private val videoID= "XsX3ATc3FbA"
    private val videoTitle = "BTS (방탄소년단) '작은 것들을 위한 시 (Boy With Luv) feat. Halsey' Official MV"
    private lateinit var mYoutubePlayerFragment :YouTubePlayerSupportFragment
    private var threadStopflag = true
    private var handler = Handler()
    private var timer:Int = -2


    private val youtubeListener = object:YouTubePlayer.OnInitializedListener{

        override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
            Toast.makeText(context, "Content load fail..", Toast.LENGTH_SHORT).show()
            }

        override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer, isReady: Boolean) {
            if (!isReady) {
                youtubePlayer.setPlaybackEventListener(playbackEventListener)
                youtubePlayer.setPlayerStateChangeListener(playerStateChangeListener)
                //플레이어 스타일 설정 CHROMELESS ( 동영상 진행 progressbar 및 멈춤기능없음), minimal (멈춤이랑 progressbar만 있음)
                youtubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                youtubePlayer.cueVideo(videoID)

                //전체화면 버튼 숨김
                youtubePlayer.setShowFullscreenButton(false)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val tutorialView = inflater.inflate(R.layout.fragment_main_tutorial,container, false)

        mYoutubePlayerFragment = YouTubePlayerSupportFragment()
        mYoutubePlayerFragment.initialize(resources.getString(R.string.youtube_api_key), youtubeListener)

        //화면 클릭하면 멈추고 뭐 이런거 달려고 했는데 굳이 안해도 될듯
//        view.layout_youtube.setOnClickListener(youtubeClickListener)

        fragmentManager!!.beginTransaction().apply {
            replace(R.id.youtube_fragment, mYoutubePlayerFragment as Fragment)
            commit()
        }

        activity!!.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        tutorialView.tv_VideoTitle.text = videoTitle

        return tutorialView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //뷰 설정

    }

    private val youtubeClickListener = View.OnClickListener {
//        mYoutubePlayer.pause()
//        tv_subtitles.text = "fragment_main_tutorial"
    }

    private fun startSubtitles(){
        val sub = Subtitles()
        var subIndex = 0
        val koreanSub = sub.XsX3ATc3FbA
        val koreanSubDiction = sub.XsX3ATc3FbA_diction
        val koreanSubTime = sub.XsX3ATc3FbA_time

        val thread = Thread(Runnable {
            while (threadStopflag) {
                try {
                    handler.post {

//                        tv_VideoName.text=timer.toString()
                        if(koreanSubTime.contains(timer)) {
                            tv_koreanSubtitles.text = koreanSub[subIndex]
                            tv_koreanSubtitlesDiction.text = koreanSubDiction[subIndex]
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