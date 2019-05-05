package com.chupryna.socialapplication.ui.main.photo_view_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.Photo
import com.chupryna.socialapplication.loadPhoto
import kotlinx.android.synthetic.main.fragment_photo_view.*

class PhotoViewFragment(private val photo: Photo) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo_view, container, false)
    }

    override fun onStart() {
        super.onStart()
        bigPhotoIv.loadPhoto(photo.url)
    }
}