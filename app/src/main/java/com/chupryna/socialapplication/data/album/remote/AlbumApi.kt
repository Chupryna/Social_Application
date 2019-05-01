package com.chupryna.socialapplication.data.album.remote

import com.chupryna.socialapplication.data.model.Album
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumApi {
    @GET("albums/1/photos")
    fun getAllAlbums():Call<List<Album>>

    @GET("albums/1/photos")
    fun getAlbumByAlbumId(@Query ("albumId") albumID: Int):Call<List<Album>>
}