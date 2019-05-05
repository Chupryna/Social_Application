package com.chupryna.socialapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.Photo
import kotlinx.android.synthetic.main.fragment_item_photo.view.*

class RVAdapterPhotos(private val listPhotos: List<Photo>) : RecyclerView.Adapter<RVAdapterPhotos.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPhotos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoIv = itemView.photoImageView
    }
}