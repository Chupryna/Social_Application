package com.chupryna.socialapplication.data.album.remote

import com.chupryna.socialapplication.data.model.Album
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumApi {
    @GET("users/1/albums")
    fun getAllAlbums():Call<List<Album>>

    @GET("users/1/albums")
    fun getAlbumByUserId(@Query ("userId") userID: Int):Call<List<Album>>
}