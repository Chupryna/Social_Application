package com.chupryna.socialapplication.data.album

import android.content.Context
import com.chupryna.socialapplication.data.album.local.LocalAlbumDataSource
import com.chupryna.socialapplication.data.album.remote.RemoteAlbumDataSource
import com.chupryna.socialapplication.data.model.Album

class AlbumRepository(context: Context) : IAlbumDataSource {

    private val localAlbumDS = LocalAlbumDataSource(context)
    private val remoteAlbumDS = RemoteAlbumDataSource()

    override fun getAlbums(callback: IAlbumDataSource.IAlbumCallback) {
        loadAlbumsFromRemote(callback)
    }

    private fun loadAlbumsFromLocal(callback: IAlbumDataSource.IAlbumCallback) {
        localAlbumDS.getAlbums(object : IAlbumDataSource.IAlbumCallback {
            override fun onAlbumLoaded(list: List<Album>) {
                callback.onAlbumLoaded(list)
            }

            override fun onFailure(msg: String) {
                callback.onFailure(msg)
            }
        })
    }

    private fun loadAlbumsFromRemote(callback: IAlbumDataSource.IAlbumCallback) {
        remoteAlbumDS.getAlbums(object : IAlbumDataSource.IAlbumCallback {
            override fun onAlbumLoaded(list: List<Album>) {
                callback.onAlbumLoaded(list)
                localAlbumDS.saveToDB(list)
            }

            override fun onFailure(msg: String) {
                callback.onFailure(msg)
                loadAlbumsFromLocal(callback)
            }
        })
    }

    override fun getAlbumsByUserID(id: Int, callback: IAlbumDataSource.IAlbumCallback) {

    }
}