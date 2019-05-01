package com.chupryna.socialapplication.ui.main.album_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.Album
import com.chupryna.socialapplication.ui.main.adapter.RVAdapterAlbums
import kotlinx.android.synthetic.main.fragment_album.*

class AlbumFragment : Fragment(), IAlbumView {

    private val presenter by lazy { AlbumPresenter(this, this.context!!) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onStart() {
        super.onStart()
        initView()
        presenter.onLoadAlbums()
    }

    private fun initView() {
        albumsRv.layoutManager = GridLayoutManager(this.context, 2)
    }

    override fun setAdapter(list: List<Album>) {
        albumsRv.adapter = RVAdapterAlbums(list)
    }
}