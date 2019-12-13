package com.jjw.learnKorean.main


import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.jjw.learnKorean.R
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.jjw.learnKorean.common.Subtitles
import com.jjw.learnKorean.playlist.VideoActivity
import kotlinx.android.synthetic.main.fragment_main_tutorial.*
import kotlinx.android.synthetic.main.fragment_main_tutorial.view.*

@Suppress("UNCHECKED_CAST")
class TutorialFragment : androidx.fragment.app.Fragment() {

    private lateinit var videoID:String
    private lateinit var videoTitle :String
    private lateinit var mYoutubePlayerFragment: YouTubePlayerSupportFragment
    private var threadStopflag = true
    private var handler = Handler()
    private var timer: Int = -2
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var koreanSub:ArrayList<String>
    private lateinit var koreanSubTime:ArrayList<String>
    private lateinit var koreanSubtitlesDiction:ArrayList<String>
    private lateinit var subtitles:ArrayList<String>

    private val youtubeListener = object : YouTubePlayer.OnInitializedListener {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        getFirestoreTutorial()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val tutorialView = inflater.inflate(R.layout.fragment_main_tutorial, container, false)

        //화면 클릭하면 멈추고 뭐 이런거 달려고 했는데 굳이 안해도 될듯
//        view.layout_youtube.setOnClickListener(youtubeClickListener)
        init()
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

    /*
        title도 firebase 에서 받아오도록 설정할랬는데 메인스레드에서 너무 많은 일을 한다고해서 일단 보류함
    private fun getFirestoreTutorial() {

        db.collection("LearnKorean").document("Videos").let {
            it.get().addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")

                    videoID = document.data!!["tutorial"] as String
                    videoTitle = document.data!!["tutorial_title"] as String

                } else {
                    Log.d(TAG, "No such document")
                }
            }
        }

        db.collection("LearnKorean").document("Videos").collection("XsX3ATc3FbA").document("Subtitle").let{
            it.get().addOnSuccessListener { document ->

                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")

                    subtitles = document.data!!["eng_sub"] as ArrayList<String>
                    koreanSubtitlesDiction = document.data!!["eng_dic"] as ArrayList<String>
                    koreanSub  = document.data!!["kor_sub"] as ArrayList<String>
                    koreanSubTime = document.data!!["time"] as ArrayList<String>
                } else {
                    Log.d(TAG, "No such document")
                }
            }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }
        }
    }*/

        private fun init() {

            mYoutubePlayerFragment = YouTubePlayerSupportFragment()
            mYoutubePlayerFragment.initialize(resources.getString(R.string.youtube_api_key), youtubeListener)

            fragmentManager!!.beginTransaction().apply {
                replace(R.id.youtube_fragment, mYoutubePlayerFragment as Fragment)
                commit()
            }

            activity!!.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        private fun startSubtitles() {
            var subIndex = 0

            val thread = Thread(Runnable {
                while (threadStopflag) {
                    try {
                        handler.post {

                            if (koreanSubTime.contains("$timer")) {
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

        private val playbackEventListener: YouTubePlayer.PlaybackEventListener =
            object : YouTubePlayer.PlaybackEventListener {

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
        private val playerStateChangeListener: YouTubePlayer.PlayerStateChangeListener =
            object : YouTubePlayer.PlayerStateChangeListener {

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

        override fun onResume() {
            super.onResume()
        }

        companion object { const val TAG = "PlaylistFragment" }

    }

