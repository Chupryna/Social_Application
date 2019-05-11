package com.chupryna.socialapplication.data.photo

import com.chupryna.socialapplication.data.model.ImageUploadResponse
import com.chupryna.socialapplication.data.model.Photo

interface IPhotoDataSource {

    interface IPhotoDownloadCallback {
        fun onPhotosLoaded(list: List<Photo>)
        fun onFailure()
    }

    interface IPhotoUploadCallback {
        fun onSuccess(response: ImageUploadResponse)
        fun onFailure()
    }

    fun getPhotosByAlbumID(id: Int, callback: IPhotoDownloadCallback)
}