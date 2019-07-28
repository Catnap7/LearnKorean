package com.jjw.learnkorean.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.jjw.learnkorean.R
import kotlinx.android.synthetic.main.fragment_main_tutorial.*
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import kotlinx.android.synthetic.main.fragment_main_tutorial.view.*


class TutorialFragment : Fragment(){

    private val videoID = "N76HNPfI4zs"
    private lateinit var mYoutubePlayerFragment :YouTubePlayerSupportFragment

    private val youtubeListener = object:YouTubePlayer.OnInitializedListener{

        override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
            Toast.makeText(context, "Content load fail..", Toast.LENGTH_SHORT).show()
            }


        override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer, isReady: Boolean) {
            if (!isReady) {
//                mYoutubePlayer = youtubePlayer
                youtubePlayer.setPlaybackEventListener(playbackEventListener)
                //플레이어 스타일 설정 CHROMELESS ( 동영상 진행 progressbar 및 멈춤기능없음), minimal (멈춤이랑 progressbar만 있음)
                youtubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                youtubePlayer.loadVideo(videoID)
                youtubePlayer.setFullscreen(false)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_tutorial,container, false)

        mYoutubePlayerFragment = YouTubePlayerSupportFragment()
        mYoutubePlayerFragment.initialize(resources.getString(R.string.youtube_api_key), youtubeListener)

        view.layout_youtube.setOnClickListener(youtubeClickListener)

        fragmentManager!!.beginTransaction().apply {
            replace(R.id.youtube_fragment, mYoutubePlayerFragment)
            commit()
        }

        activity!!.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //뷰 설정
//        tv_fragment_main_tutorial.text = "fragment_main_tutorial"

    }

    private val youtubeClickListener = View.OnClickListener {
//        mYoutubePlayer.pause()
//        tv_subtitles.text = "fragment_main_tutorial"
    }


    private val  playbackEventListener: YouTubePlayer.PlaybackEventListener = object: YouTubePlayer.PlaybackEventListener{

        override fun onSeekTo(p0: Int) {
        }

        override fun onBuffering(p0: Boolean) {
        }

        override fun onPlaying() {
        }

        override fun onStopped() {
        }

        override fun onPaused() {
        }
    }
    private val playerStateChangeListener: YouTubePlayer.PlayerStateChangeListener = object: YouTubePlayer.PlayerStateChangeListener {

        override fun onAdStarted() {
        }

        override fun onLoading() {
        }

        override fun onVideoStarted() {
        }

        override fun onLoaded(p0: String?) {
        }

        override fun onVideoEnded() {
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }
    }



}