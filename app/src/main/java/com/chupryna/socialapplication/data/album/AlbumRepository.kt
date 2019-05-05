package com.chupryna.socialapplication.data.album

import android.content.Context
import com.chupryna.socialapplication.data.album.local.LocalAlbumDataSource
import com.chupryna.socialapplication.data.album.remote.RemoteAlbumDataSource
import com.chupryna.socialapplication.data.model.Album
import com.chupryna.socialapplication.isNetworkAvailable


class AlbumRepository(private val context: Context) : IAlbumDataSource {

    private val localAlbumDS = LocalAlbumDataSource(context)
    private val remoteAlbumDS = RemoteAlbumDataSource()

    override fun getAlbums(callback: IAlbumDataSource.IAlbumCallback) {
        if (context.isNetworkAvailable(context)) {
            loadAlbumsFromRemote(callback)
        } else {
            loadAlbumsFromLocal(callback)
        }
    }

    private fun loadAlbumsFromLocal(callback: IAlbumDataSource.IAlbumCallback) {
        localAlbumDS.getAlbums(object : IAlbumDataSource.IAlbumCallback {
            override fun onAlbumLoaded(list: List<Album>) {
                callback.onAlbumLoaded(list)
            }

            override fun onFailure() {
                callback.onFailure()
            }
        })
    }

    private fun loadAlbumsFromRemote(callback: IAlbumDataSource.IAlbumCallback) {
        remoteAlbumDS.getAlbums(object : IAlbumDataSource.IAlbumCallback {
            override fun onAlbumLoaded(list: List<Album>) {
                callback.onAlbumLoaded(list)
                localAlbumDS.saveToDB(list)
            }

            override fun onFailure() {
                loadAlbumsFromLocal(callback)
            }
        })
    }

    override fun getAlbumsByUserID(id: Int, callback: IAlbumDataSource.IAlbumCallback) {

    }
}