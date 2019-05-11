package com.chupryna.socialapplication.data.photo.download.local

import android.content.Context
import com.chupryna.socialapplication.data.AppDatabase
import com.chupryna.socialapplication.data.model.Photo
import com.chupryna.socialapplication.data.photo.IPhotoDataSource

class LocalPhotoDataSource(private val context: Context) :
    IPhotoDataSource {

    private val db by lazy { AppDatabase.getInstance(context)!! }

    override fun getPhotosByAlbumID(id: Int, callback: IPhotoDataSource.IPhotoDownloadCallback) {
        val cashedPhotos = db.photoDao().getPhotosByAlbumID(id)
        if (cashedPhotos.isNotEmpty())
            callback.onPhotosLoaded(cashedPhotos)
        else
            callback.onFailure()
    }

    fun saveToDB(list: List<Photo>) {
        db.photoDao().insert(list)
    }
}