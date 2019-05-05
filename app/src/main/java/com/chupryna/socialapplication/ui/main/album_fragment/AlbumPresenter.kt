package com.chupryna.socialapplication.ui.main.album_fragment

import android.content.Context
import com.chupryna.socialapplication.data.album.AlbumRepository
import com.chupryna.socialapplication.data.album.IAlbumDataSource
import com.chupryna.socialapplication.data.model.Album

class AlbumPresenter(
    private val view: IAlbumView,
    private val context: Context) {

    private val model by lazy { AlbumRepository(context) }

    fun onLoadAlbums() {
        view.showProgress()
        model.getAlbums(object: IAlbumDataSource.IAlbumCallback {
            override fun onAlbumLoaded(list: List<Album>) {
                view.setAdapter(list)
                view.hideProgress()
            }

            override fun onFailure() {
                view.hideProgress()
            }
        })
    }
}