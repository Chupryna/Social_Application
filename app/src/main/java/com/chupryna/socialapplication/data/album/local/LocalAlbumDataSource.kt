package com.chupryna.socialapplication.data.album.local

import android.content.Context
import com.chupryna.socialapplication.data.AppDatabase
import com.chupryna.socialapplication.data.album.IAlbumDataSource
import com.chupryna.socialapplication.data.model.Album

class LocalAlbumDataSource(private val context: Context) : IAlbumDataSource {

    private val db by lazy { AppDatabase.getInstance(context)!! }

    override fun getAlbums(callback: IAlbumDataSource.IAlbumCallback) {
        val cashedAlbums = db.albumDao().getAlbums()
        if (cashedAlbums.isNotEmpty())
            callback.onAlbumLoaded(cashedAlbums)
        else
            callback.onFailure("")
    }

    override fun getAlbumsByUserID(id: Int, callback: IAlbumDataSource.IAlbumCallback) {
        val cashedAlbums = db.albumDao().getAlbumByUserId(id)
        if (cashedAlbums.isNotEmpty())
            callback.onAlbumLoaded(cashedAlbums)
        else
            callback.onFailure("")
    }

    fun saveToDB(list: List<Album>) {
        db.albumDao().deleteAll()
        db.albumDao().insert(list)
    }
}