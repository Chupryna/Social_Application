package com.chupryna.socialapplication.data.photo

import android.content.Context
import com.chupryna.socialapplication.data.model.ImageUploadResponse
import com.chupryna.socialapplication.data.model.Photo
import com.chupryna.socialapplication.data.photo.download.local.LocalPhotoDataSource
import com.chupryna.socialapplication.data.photo.download.remote.RemotePhotoDataSource
import com.chupryna.socialapplication.data.photo.upload.UploadImageDataSource
import com.chupryna.socialapplication.utils.isNetworkAvailable
import java.io.File

class PhotoRepository(private val context: Context) : IPhotoDataSource {

    private val remotePhotoDS = RemotePhotoDataSource()
    private val localPhotoDS = LocalPhotoDataSource(context)
    private val uploadImageDS = UploadImageDataSource()

    override fun getPhotosByAlbumID(id: Int, callback: IPhotoDataSource.IPhotoDownloadCallback) {
        loadFromLocal(id, callback)
    }

    private fun loadFromRemote(id: Int, callback: IPhotoDataSource.IPhotoDownloadCallback) {
        remotePhotoDS.getPhotosByAlbumID(id, object:
            IPhotoDataSource.IPhotoDownloadCallback {
            override fun onPhotosLoaded(list: List<Photo>) {
                callback.onPhotosLoaded(list)
                localPhotoDS.saveToDB(list)
            }

            override fun onFailure() {
                callback.onFailure()
            }
        })
    }

    private fun loadFromLocal(id: Int, callback: IPhotoDataSource.IPhotoDownloadCallback) {
        localPhotoDS.getPhotosByAlbumID(id, object:
            IPhotoDataSource.IPhotoDownloadCallback {
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

    fun uploadImage(photoPath: String, callback: IPhotoDataSource.IPhotoUploadCallback) {
        uploadImageDS.uploadImage(File(photoPath), object: IPhotoDataSource.IPhotoUploadCallback {
            override fun onSuccess(response: ImageUploadResponse) {
                callback.onSuccess(response)
            }

            override fun onFailure() {
                callback.onFailure()
            }
        })
    }
}