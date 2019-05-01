package com.chupryna.socialapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.Album
import kotlinx.android.synthetic.main.fragment_item_album.view.*

class RVAdapterAlbums(albums: List<Album>) : RecyclerView.Adapter<RVAdapterAlbums.AlbumsViewHolder>() {

    private val albumsList = albums

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_album, parent, false)
        return AlbumsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.title.text = albumsList[position].title
    }

    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.albumItemImageView
        val title: TextView = itemView.albumTitleTv
    }
}