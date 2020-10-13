package com.example.playerapp.ui.player.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.playerapp.R
import com.example.playerapp.extension.loadImage
import com.example.playerapp.models.Song
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_song.*

class PlayerAdapter(private var list: MutableList<Song>): RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_song, parent, false))
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class PlayerViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bind(item: Song) {
            tvSong.text = item.song
            tvArtists.text = item.artists
            tvUrl.text = item.url
            coverImage.loadImage(coverImage.context, R.drawable.not_foto, item.coverImage)
        }
    }

}
