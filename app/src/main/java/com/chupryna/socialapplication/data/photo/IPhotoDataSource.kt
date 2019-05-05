package com.chupryna.socialapplication.data.photo

import com.chupryna.socialapplication.data.model.Photo

interface IPhotoDataSource {

    interface IPhotoCallback {
        fun onPhotosLoaded(list: List<Photo>)
        fun onFailure()
    }

    fun getPhotosByAlbumID(id: Int, callback: IPhotoCallback)
}