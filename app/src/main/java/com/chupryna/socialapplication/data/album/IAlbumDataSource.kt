package com.chupryna.socialapplication.data.album

import com.chupryna.socialapplication.data.model.Album

interface IAlbumDataSource {

    interface IAlbumCallback {
        fun onAlbumLoaded(list: List<Album>)
        fun onFailure()
    }

    fun getAlbums(callback: IAlbumCallback)

    fun getAlbumsByID(id: Int, callback: IAlbumCallback)
}