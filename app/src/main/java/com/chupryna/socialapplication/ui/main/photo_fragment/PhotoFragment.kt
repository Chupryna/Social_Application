package com.chupryna.socialapplication.ui.main.photo_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.Album
import com.chupryna.socialapplication.data.model.Photo
import com.chupryna.socialapplication.ui.main.MainActivity
import com.chupryna.socialapplication.ui.main.adapter.RVAdapterPhotos
import kotlinx.android.synthetic.main.fragment_photo.*

class PhotoFragment(private val album: Album) : Fragment(), IPhotoView {

    private val presenter by lazy { PhotoPresenter(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onStart() {
        super.onStart()
        initView()
        presenter.onLoadPhoto(album.userId)
    }

    private fun initView() {
        albumNameTv.text = album.title
        photoRv.layoutManager = GridLayoutManager(context, 4)
    }

    override fun showProgress() {
        (activity as MainActivity).showProgress()
    }

    override fun hideProgress() {
        (activity as MainActivity).hideProgress()
    }


    override fun setAdapter(list: List<Photo>) {
        photoRv.adapter = RVAdapterPhotos(list)
    }
}