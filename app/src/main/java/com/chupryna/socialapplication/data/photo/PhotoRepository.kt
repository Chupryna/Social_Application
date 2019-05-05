package com.chupryna.socialapplication.data.photo

import android.content.Context
import com.chupryna.socialapplication.data.model.Photo
import com.chupryna.socialapplication.data.photo.local.LocalPhotoDataSource
import com.chupryna.socialapplication.data.photo.remote.RemotePhotoDataSource
import com.chupryna.socialapplication.isNetworkAvailable

class PhotoRepository(private val context: Context) : IPhotoDataSource {

    private val remotePhotoDS = RemotePhotoDataSource()
    private val localPhotoDS = LocalPhotoDataSource(context)

    override fun getPhotosByAlbumID(id: Int, callback: IPhotoDataSource.IPhotoCallback) {
        loadFromLocal(id, callback)
    }

    private fun loadFromRemote(id: Int, callback: IPhotoDataSource.IPhotoCallback) {
        remotePhotoDS.getPhotosByAlbumID(id, object: IPhotoDataSource.IPhotoCallback {
            override fun onPhotosLoaded(list: List<Photo>) {
                callback.onPhotosLoaded(list)
                localPhotoDS.saveToDB(list)
            }

            override fun onFailure() {
                callback.onFailure()
            }
        })
    }

    private fun loadFromLocal(id: Int, callback: IPhotoDataSource.IPhotoCallback) {
        localPhotoDS.getPhotosByAlbumID(id, object: IPhotoDataSource.IPhotoCallback {
            override fun onPhotosLoaded(list: List<Photo>) {
                callback.onPhotosLoaded(list)
            }

            override fun onFailure() {
                if (context.isNetworkAvailable())
                    loadFromRemote(id, callback)
                else
                    callback.onFailure()
            }
        })
    }
}