package com.chupryna.socialapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.Photo
import com.chupryna.socialapplication.loadPhoto
import com.chupryna.socialapplication.ui.main.photo_fragment.PhotoOverlayView
import com.stfalcon.imageviewer.StfalconImageViewer
import kotlinx.android.synthetic.main.fragment_item_photo.view.*

class RVAdapterPhotos(private val photosList: List<Photo>) : RecyclerView.Adapter<RVAdapterPhotos.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.photoIv.loadPhoto(photosList[position].thumbnailUrl)

        holder.itemView.setOnClickListener {
            val builder = StfalconImageViewer.Builder<Photo>(holder.itemView.context, photosList) { imageView, photo ->
                imageView.loadPhoto(photo.url)
            }

            val photoOverlayView = PhotoOverlayView(holder.itemView.context).apply { update(photosList[position] )}
            builder.withStartPosition(position)
            builder.withOverlayView(photoOverlayView)
            builder.withImageChangeListener { position ->  photoOverlayView.update(photosList[position]) }
            builder.show()
        }
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoIv: ImageView = itemView.photoImageView
    }
}