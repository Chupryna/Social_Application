package com.chupryna.socialapplication.data.album.remote

import com.chupryna.socialapplication.BuildConfig
import com.chupryna.socialapplication.data.album.IAlbumDataSource
import com.chupryna.socialapplication.data.model.Album
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteAlbumDataSource : IAlbumDataSource {

    private val albumApi: AlbumApi

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            addInterceptor(interceptor)
        }.retryOnConnectionFailure(true).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        albumApi = retrofit.create(AlbumApi::class.java)
    }

    override fun getAlbums(callback: IAlbumDataSource.IAlbumCallback) {
        val call: Call<List<Album>> = albumApi.getAllAlbums()
        executeCall(call, callback)
    }

    override fun getAlbumsByID(id: Int, callback: IAlbumDataSource.IAlbumCallback) {
        val call: Call<List<Album>> = albumApi.getAlbumByAlbumId(id)
        executeCall(call, callback)
    }

    private fun executeCall(call: Call<List<Album>>, callback: IAlbumDataSource.IAlbumCallback) {
        call.enqueue(object : Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                if (response.body() != null)
                    callback.onAlbumLoaded(response.body()!!)
                else
                    callback.onFailure()
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                callback.onFailure()
            }
        })
    }
}