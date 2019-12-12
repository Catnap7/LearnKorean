package com.jjw.learnKorean.playlist

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.activity_playlist_video.*

@Suppress("UNCHECKED_CAST")
class VideoActivity  : AppCompatActivity() {

    private lateinit var mYoutubePlayerFragment : YouTubePlayerSupportFragment
    private var threadStopflag = true
    private var handler = Handler()
    private var timer:Int = -2
    private var position:Int = 0
    private lateinit var videoId:String
    private val db:FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var koreanSub:ArrayList<String>
    private lateinit var koreanSubTime:ArrayList<String>
    private lateinit var koreanSubtitlesDiction:ArrayList<String>
    private lateinit var subtitles:ArrayList<String>

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

        getSubtitle()

    }

    private fun getSubtitle(){

        val docRef = db.collection("LearnKorean").document("Videos").collection(videoId).document("Subtitle")

        docRef.get().addOnSuccessListener { document ->

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

    private fun startSubtitles(){

        var subIndex = 0
        val thread = Thread(Runnable {
            while (threadStopflag) {
                try {
                    handler.post {

//                        tv_VideoName.text=timer.toString()
                        if(koreanSubTime.contains("$timer")) {
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

    companion object { var TAG = "VideoActivity" }
}