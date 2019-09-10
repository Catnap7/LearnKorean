package com.jjw.learnKorean.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jjw.learnKorean.R
import com.jjw.learnKorean.playlist.PlaylistAdapter
import kotlinx.android.synthetic.main.fragment_main_playlist.*
import kotlinx.android.synthetic.main.fragment_main_playlist.view.*
import kotlinx.android.synthetic.main.fragment_main_playlist.view.rv_youtubePlaylist

class PlaylistFragment : androidx.fragment.app.Fragment(){

    private val playList = arrayOf("N76HNPfI4zs","XsX3ATc3FbA","mw-JgIonmD8","wksUmcAx7z4","4lKqFR-67RI","3-FXW0CW_8o","gqvEO6o1Mx8","FsEGaURjZ8w","fgja5tdRB8o")
    private val playlist_title = arrayOf("[BANGTAN BOMB] JK's Heart (for ARMY♥︎) - BTS (방탄소년단)","BTS (방탄소년단) '작은 것들을 위한 시 (Boy With Luv) feat. Halsey' Official MV",
        "[BANGTAN BOMB] RM reading a book - BTS (방탄소년단)","[BANGTAN BOMB] RM’s Surprise(?) Birthday Party - BTS (방탄소년단)","[BANGTAN BOMB] '풍경' Special LIVE - BTS (방탄소년단)",
        "[BANGTAN BOMB] V&Jungkook Singing at standby time - BTS (방탄소년단)","[BANGTAN BOMB] Let's Pizza Party! - BTS (방탄소년단)","[BANGTAN BOMB] The secret of BTS' beard - BTS (방탄소년단)",
        "[BANGTAN BOMB] JK & V experienced a new job! - BTS (방탄소년단)")

    private lateinit var playlistAdapter:PlaylistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val playlistView:View =  inflater.inflate(R.layout.fragment_main_playlist,container, false)



        val rv_youtubePlaylist = playlistView.rv_youtubePlaylist

        rv_youtubePlaylist.isNestedScrollingEnabled = false
        rv_youtubePlaylist.layoutManager = LinearLayoutManager(activity)

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
}
