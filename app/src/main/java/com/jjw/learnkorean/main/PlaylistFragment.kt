package com.jjw.learnkorean.main

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjw.learnkorean.R
import com.jjw.learnkorean.playlist.PlaylistAdapter
import kotlinx.android.synthetic.main.fragment_main_history.*
import kotlinx.android.synthetic.main.fragment_main_playlist.*
import kotlinx.android.synthetic.main.fragment_main_playlist.view.*

class PlaylistFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val playlistView:View =  inflater.inflate(R.layout.fragment_main_playlist,container, false)


        val playList = arrayListOf<String>("N76HNPfI4zs","XsX3ATc3FbA")
        val rv_youtubePlaylist = playlistView.rv_youtubePlaylist
        val layoutManager = LinearLayoutManager(activity)
        rv_youtubePlaylist.layoutManager = layoutManager
        rv_youtubePlaylist.itemAnimator = DefaultItemAnimator()

        val playlistAdapter = PlaylistAdapter(activity!!,playList)

        rv_youtubePlaylist.adapter = playlistAdapter



        return playlistView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //뷰 설정
        tv_history.text = "fragment_main_goal"
    }
}
