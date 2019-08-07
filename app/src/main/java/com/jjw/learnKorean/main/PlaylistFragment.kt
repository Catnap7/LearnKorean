package com.jjw.learnKorean.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjw.learnKorean.R
import com.jjw.learnKorean.playlist.PlaylistAdapter
import kotlinx.android.synthetic.main.fragment_main_playlist.view.*

class PlaylistFragment : Fragment(){

    private lateinit var playlistAdapter:PlaylistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val playlistView:View =  inflater.inflate(R.layout.fragment_main_playlist,container, false)

        val playList = arrayOf<String>("N76HNPfI4zs","XsX3ATc3FbA","mw-JgIonmD8")

        val rv_youtubePlaylist = playlistView.rv_youtubePlaylist

        rv_youtubePlaylist.isNestedScrollingEnabled = false
        rv_youtubePlaylist.layoutManager = LinearLayoutManager(activity)
//        rv_youtubePlaylist.itemAnimator = DefaultItemAnimator()

        playlistAdapter = PlaylistAdapter(activity!!,playList)

        rv_youtubePlaylist.adapter = playlistAdapter

        return playlistView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onStop() {
        super.onStop()
    }
}
