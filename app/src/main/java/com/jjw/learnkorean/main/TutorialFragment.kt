package com.jjw.learnkorean.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.android.youtube.player.YouTubePlayerView
import com.jjw.learnkorean.R
import kotlinx.android.synthetic.main.fragment_main_tutorial.*
import com.google.android.youtube.player.YouTubePlayerSupportFragment




class TutorialFragment : Fragment(){

    private val videoID = "N76HNPfI4zs"

    private val youtubeListener = object:YouTubePlayer.OnInitializedListener{

        override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        }

        override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer, isReady: Boolean) {
            if (!isReady) {
                val playKey = "AIzaSyAAa3Sq_2x4VJi7rUinIZc0lYbjCcnoukE"
//                youtubePlayer!!.cueVideo(playKey)
                youtubePlayer.loadVideo(playKey)
                youtubePlayer.play()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_tutorial,container, false)

//        val youTubePlayerView =
//        youTubePlayerView.initialize( videoID,youtubeListener)

        val youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance()


//        var youtubeFragment = activity!!.supportFragmentManager.findFragmentById(R.id.youtube_fragment)


        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //뷰 설정
        tv_fragment_main_tutorial.text = "fragment_main_tutorial"
    }
}