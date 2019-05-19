package com.chupryna.socialapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.Album
import com.chupryna.socialapplication.ui.main.album_fragment.IAlbumView
import com.chupryna.socialapplication.ui.main.photo_fragment.PhotoFragment
import kotlinx.android.synthetic.main.fragment_item_album.view.*

class RVAdapterAlbums(albums: List<Album>, iAlbumView: IAlbumView) : RecyclerView.Adapter<RVAdapterAlbums.AlbumsViewHolder>() {

    private val albumsList = albums
    private val view = iAlbumView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_album, parent, false)
        return AlbumsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.title.text = albumsList[position].title

        holder.itemView.setOnClickListener { view.replaceFragment(PhotoFragment(albumsList[position])) }
    }

    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val image: ImageView = itemView.albumItemImageView
        val title: TextView = itemView.albumTitleTv
    }
}