package com.chupryna.socialapplication.data.photo.upload

import com.chupryna.socialapplication.data.model.ImageUploadResponse
import com.chupryna.socialapplication.data.photo.IPhotoDataSource
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.MultipartBody

class UploadImageDataSource {

    private val uploadImageApi: UploadImageApi

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            addInterceptor(interceptor)
        }.retryOnConnectionFailure(true).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://android-test.ho.ua/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        uploadImageApi = retrofit.create(UploadImageApi::class.java)
    }

    fun uploadImage(file: File, callback: IPhotoDataSource.IPhotoUploadCallback) {
        val requestBody = RequestBody.create(MediaType.parse("image/jpg"), file)
        val fileToUpload = MultipartBody.Part.createFormData("file", file.name, requestBody)
        val filename = RequestBody.create(MediaType.parse("text/plain"), file.name)
        uploadImageApi.uploadImage(fileToUpload, filename).enqueue(object: Callback<ImageUploadResponse> {
            override fun onFailure(call: Call<ImageUploadResponse>, t: Throwable) {
                callback.onFailure()
            }

            override fun onResponse(call: Call<ImageUploadResponse>, response: Response<ImageUploadResponse>) {
                if (response.body() != null && response.body()!!.success)
                    callback.onSuccess(response.body()!!)
                else
                    callback.onFailure()
            }
        })
    }
}