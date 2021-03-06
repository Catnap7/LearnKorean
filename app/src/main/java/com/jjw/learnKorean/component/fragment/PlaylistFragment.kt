package com.jjw.learnKorean.component.fragment

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import com.jjw.learnKorean.R
import com.jjw.learnKorean.adapter.PlaylistAdapter
import kotlinx.android.synthetic.main.fragment_main_playlist.*
import kotlinx.android.synthetic.main.fragment_main_playlist.view.rv_youtubePlaylist

class PlaylistFragment : androidx.fragment.app.Fragment(){

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var playlistAdapter: PlaylistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val playlistView:View =  inflater.inflate(R.layout.fragment_main_playlist,container, false)

        val rv_youtubePlaylist = playlistView.rv_youtubePlaylist

        rv_youtubePlaylist.isNestedScrollingEnabled = false
        rv_youtubePlaylist.layoutManager = LinearLayoutManager(activity)

        getFirestoreVideos()

        return playlistView
    }

    // 유튜브 동영상 URI id랑 이름 받아와서 어뎁터에 저장
    fun getFirestoreVideos(){

        val docRefPlayList = db.collection("LearnKorean").document("Videos")

        docRefPlayList.get().addOnSuccessListener { document ->
            if (document != null) {
                Log.e(TAG, "DocumentSnapshot data: ${document.data}")

                val playList:ArrayList<String> = document.data!!["playList"] as ArrayList<String>
                val playList_title:ArrayList<String> = document.data!!["title"] as ArrayList<String>
                playlistAdapter = PlaylistAdapter(
                    requireActivity(),
                    playList,
                    playList_title
                )
                rv_youtubePlaylist.adapter = playlistAdapter

            } else {
                Log.e(TAG, "No such document")
            }
        }

        /*val docRef = db.collection("LearnKorean").document("Videos").collection("N76HNPfI4zs").document("Subtitle")

        docRef.get().addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }*/
    }
    companion object { var TAG = "PlaylistFragment" }
}
