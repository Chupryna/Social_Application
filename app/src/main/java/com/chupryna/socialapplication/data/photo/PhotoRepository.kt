package com.chupryna.socialapplication.data.photo

import com.chupryna.socialapplication.data.model.Photo
import com.chupryna.socialapplication.data.photo.remote.RemotePhotoDataSource

class PhotoRepository : IPhotoDataSource {

    private val remotePhotoDS = RemotePhotoDataSource()

    override fun getPhotosByAlbumID(id: Int, callback: IPhotoDataSource.IPhotoCallback) {
        remotePhotoDS.getPhotosByAlbumID(id, object: IPhotoDataSource.IPhotoCallback {
            override fun onPhotosLoaded(list: List<Photo>) {
                callback.onPhotosLoaded(list)
            }

            override fun onFailure() {
                callback.onFailure()
            }
        })
    }
}