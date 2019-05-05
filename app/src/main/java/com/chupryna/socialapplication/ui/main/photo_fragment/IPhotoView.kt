package com.chupryna.socialapplication.ui.main.photo_fragment

import com.chupryna.socialapplication.data.model.Photo

interface IPhotoView {

    fun showProgress()

    fun hideProgress()

    fun setAdapter(list: List<Photo>)
}