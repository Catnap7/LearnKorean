package com.jjw.learnKorean.playlist

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import com.jjw.learnKorean.R
import kotlinx.android.synthetic.main.card_playlist.view.*

class PlaylistAdapter(private val context:Context, private val playlist: Array<String>) : RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

     private lateinit var videoName:String

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {

          videoName = playlist[position]

          holder.tv_VideoName.text = videoName

          holder.layout_youtube_thumbnail.initialize(context.getString(R.string.youtube_api_key),object: YouTubeThumbnailView.OnInitializedListener{

               override fun onInitializationSuccess(p0: YouTubeThumbnailView?, youTubeThumbnailLoader: YouTubeThumbnailLoader) {
                    youTubeThumbnailLoader.setVideo(playlist[position])

                    youTubeThumbnailLoader.setOnThumbnailLoadedListener(object : YouTubeThumbnailLoader.OnThumbnailLoadedListener {
                         override fun onThumbnailLoaded(youTubeThumbnailView: YouTubeThumbnailView, s: String) {
                              youTubeThumbnailLoader.release()
                         }

                         override fun onThumbnailError(youTubeThumbnailView: YouTubeThumbnailView, errorReason: YouTubeThumbnailLoader.ErrorReason) {
                              Log.e(TAG, "Youtube Thumbnail Error")
                         }
                    })

               }

               override fun onInitializationFailure(p0: YouTubeThumbnailView?, p1: YouTubeInitializationResult?) {
                    Log.e(TAG, "Youtube Initialization Failure")
               }
          })

          holder.layout_playlist.setOnClickListener {

               Intent(context, VideoActivity::class.java).let{
                    it.putExtra("videoId",videoName)
                    context.startActivity(it)
               }
          }
     }

     override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
          return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_playlist, p0, false))
     }

     override fun getItemCount(): Int = playlist.size


     inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

          val tv_VideoName: TextView = view.tv_VideoName
          val layout_playlist: ConstraintLayout = view.layout_card_playlist
          val layout_youtube_thumbnail:YouTubeThumbnailView = view.layout_youtube_thumbnail
//          val iv_thumnail:ImageView = view.iv_thumbnail
     }


}


