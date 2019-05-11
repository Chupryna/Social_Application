package com.chupryna.socialapplication.data.photo.download.remote

import com.chupryna.socialapplication.data.model.Photo
import retrofit2.Call
import retrofit2.http.*

interface PhotoApi {
    @GET("albums/1/photos")
    fun getPhotosByAlbumID(@Query("albumId") albumID: Int): Call<List<Photo>>
}