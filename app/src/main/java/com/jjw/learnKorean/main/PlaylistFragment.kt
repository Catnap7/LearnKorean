package com.jjw.learnKorean.main

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.jjw.learnKorean.R
import com.jjw.learnKorean.korean.KoreanContentsFilterActivity.Companion.TAG
import com.jjw.learnKorean.playlist.PlaylistAdapter
import kotlinx.android.synthetic.main.fragment_main_playlist.view.rv_youtubePlaylist


class PlaylistFragment : androidx.fragment.app.Fragment(){

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

//    private val playList = arrayOf("N76HNPfI4zs","RoepaDKQ0PQ","wksUmcAx7z4","4lKqFR-67RI","3-FXW0CW_8o","gqvEO6o1Mx8","FsEGaURjZ8w","fgja5tdRB8o")
    private var playList = ArrayList<String>()
    private val playlist_title = arrayOf("[BANGTAN BOMB] JK's Heart (for ARMY♥︎) - BTS (방탄소년단)",
        "[BANGTAN BOMB] Arm wrestling! WHO IS THE WINNER?! - BTS (방탄소년단)","[BANGTAN BOMB] RM’s Surprise(?) Birthday Party - BTS (방탄소년단)","[BANGTAN BOMB] '풍경' Special LIVE - BTS (방탄소년단)",
        "[BANGTAN BOMB] V&Jungkook Singing at standby time - BTS (방탄소년단)","[BANGTAN BOMB] Let's Pizza Party! - BTS (방탄소년단)","[BANGTAN BOMB] The secret of BTS' beard - BTS (방탄소년단)",
        "[BANGTAN BOMB] JK & V experienced a new job! - BTS (방탄소년단)")

    private lateinit var playlistAdapter:PlaylistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val playlistView:View =  inflater.inflate(R.layout.fragment_main_playlist,container, false)

        val rv_youtubePlaylist = playlistView.rv_youtubePlaylist

        rv_youtubePlaylist.isNestedScrollingEnabled = false
        rv_youtubePlaylist.layoutManager = LinearLayoutManager(activity)

        getFirestoreVideos()

        playlistAdapter = PlaylistAdapter(activity!!,playList,playlist_title)
        rv_youtubePlaylist.adapter = playlistAdapter

        return playlistView
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStop() {
        super.onStop()
    }

    fun getFirestoreVideos(){

        val docRefPlayList = db.collection("LearnKorean").document("Videos")

        docRefPlayList.get().addOnSuccessListener { document ->
            if (document != null) {
                Log.d(TAG, "DocumentSnapshot data: ${document.data}")

                var size:Int = document.data!!.size()
                for(i in size){

                }
                val dc = document.toObject(ArrayList::class.java)
                playList.addAll(document.data.get(""))

//                for (i in 0 until document.data.size()) {
//                    val brandName = item.getJSONObject(i)
//                    brandList.add(brandName.getString("name"))
//                }
//                val types = document.toObject(ArrayList::class.java)
//                playList.addAll(types)
            } else {
                Log.d(TAG, "No such document")
            }
        }

        val docRef = db.collection("LearnKorean").document("Videos").collection("N76HNPfI4zs").document("Subtitle")

        docRef.get().addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }
    companion object { var TAG = "PlaylistFragment" }
}
