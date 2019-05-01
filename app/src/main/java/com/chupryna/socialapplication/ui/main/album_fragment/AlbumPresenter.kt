package com.chupryna.socialapplication.ui.main.album_fragment

import com.chupryna.socialapplication.data.album.AlbumRepository
import com.chupryna.socialapplication.data.album.IAlbumDataSource
import com.chupryna.socialapplication.data.album.remote.RemoteAlbumDataSource
import com.chupryna.socialapplication.data.model.Album

class AlbumPresenter(private val view: IAlbumView) {

    private val model by lazy { AlbumRepository() }

    fun onLoadAlbums() {
        //val remote = RemoteAlbumDataSource()
        model.getAlbums(object: IAlbumDataSource.IAlbumCallback {
            override fun onAlbumLoaded(list: List<Album>) {
                view.setAdapter(list)
            }

            override fun onFailure() {

            }
        })
    }
}