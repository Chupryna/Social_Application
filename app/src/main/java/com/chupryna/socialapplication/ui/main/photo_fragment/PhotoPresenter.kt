package com.chupryna.socialapplication.ui.main.photo_fragment

import android.content.Context
import com.chupryna.socialapplication.data.model.Photo
import com.chupryna.socialapplication.data.photo.download.IPhotoDataSource
import com.chupryna.socialapplication.data.photo.download.PhotoRepository

class PhotoPresenter(
    private val view: IPhotoView,
    private val context: Context) {

    private val model by lazy { PhotoRepository(context) }

    fun onLoadPhoto(albumID: Int) {
        view.showProgress()
        model.getPhotosByAlbumID(albumID, object: IPhotoDataSource.IPhotoCallback {
            override fun onPhotosLoaded(list: List<Photo>) {
                view.setAdapter(list)
                view.hideProgress()
            }

            override fun onFailure() {
                view.hideProgress()
            }
        })
    }
}