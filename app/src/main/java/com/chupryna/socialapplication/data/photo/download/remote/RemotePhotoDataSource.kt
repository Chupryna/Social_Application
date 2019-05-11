package com.chupryna.socialapplication.data.photo.download.remote

import com.chupryna.socialapplication.BuildConfig
import com.chupryna.socialapplication.data.model.Photo
import com.chupryna.socialapplication.data.photo.IPhotoDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemotePhotoDataSource : IPhotoDataSource {

    private val photoApi: PhotoApi

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
        photoApi = retrofit.create(PhotoApi::class.java)
    }

    override fun getPhotosByAlbumID(id: Int, callback: IPhotoDataSource.IPhotoDownloadCallback) {
        val call = photoApi.getPhotosByAlbumID(id)
        call.enqueue(object: Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                callback.onFailure()
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.body() != null)
                    callback.onPhotosLoaded(response.body()!!)
                else
                    callback.onFailure()
            }
        })
    }
}