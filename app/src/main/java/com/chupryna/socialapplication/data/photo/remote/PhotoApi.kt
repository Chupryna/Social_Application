package com.chupryna.socialapplication.data.photo.remote

import com.chupryna.socialapplication.data.model.Photo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    @GET("albums/1/photos")
    fun getPhotosByAlbumID(@Query("albumId") albumID: Int): Call<List<Photo>>
}