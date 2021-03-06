package com.chupryna.socialapplication.data.album

import com.chupryna.socialapplication.data.model.Album

interface IAlbumDataSource {

    interface IAlbumCallback {
        fun onAlbumLoaded(list: List<Album>)
        fun onFailure(msg: String)
    }

    fun getAlbums(callback: IAlbumCallback)

    fun getAlbumsByUserID(id: Int, callback: IAlbumCallback)
}