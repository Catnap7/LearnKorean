package com.jjw.learnKorean.playlist

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
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
import androidx.core.app.ActivityOptionsCompat


class PlaylistAdapter(private val context:Context, private val playlist: Array<String>,private val playlist_title: Array<String>) : RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {

          holder.tv_VideoName.text = playlist_title[position]

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
                    it.putExtra("videoId", playlist[position])
                    it.putExtra("videoTitle", playlist_title[position])
                    it.putExtra("position",position)
                    //클릭이벤트 전환 애니메이션 추가
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, holder.layout_youtube_thumbnail as View, "profile")
                    context.startActivity(it, options.toBundle())
               }
          }
     }

     override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
          return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_playlist, p0, false))
     }

     override fun getItemCount(): Int = playlist.size

     inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

          val tv_VideoName: TextView = view.tv_VideoTitle
          val layout_playlist: ConstraintLayout = view.layout_card_playlist
          val layout_youtube_thumbnail:YouTubeThumbnailView = view.layout_youtube_thumbnail
     }


}


