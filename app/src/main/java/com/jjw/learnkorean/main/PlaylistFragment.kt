package com.jjw.learnkorean.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayerView
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import com.jjw.learnkorean.R
import com.jjw.learnkorean.playlist.PlaylistAdapter
import kotlinx.android.synthetic.main.fragment_main_playlist.view.*

class PlaylistFragment : Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val playlistView:View =  inflater.inflate(R.layout.fragment_main_playlist,container, false)

        val playList = arrayOf<String>("N76HNPfI4zs","XsX3ATc3FbA","mw-JgIonmD8")

        val rv_youtubePlaylist = playlistView.rv_youtubePlaylist

        rv_youtubePlaylist.isNestedScrollingEnabled = false
        rv_youtubePlaylist.layoutManager = LinearLayoutManager(activity)
        rv_youtubePlaylist.itemAnimator = DefaultItemAnimator()

        val playlistAdapter = PlaylistAdapter(activity!!,playList)

        rv_youtubePlaylist.adapter = playlistAdapter

        return playlistView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}
