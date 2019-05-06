package com.chupryna.socialapplication.data.post.remote

import com.chupryna.socialapplication.BuildConfig
import com.chupryna.socialapplication.data.model.Post
import com.chupryna.socialapplication.data.post.IPostDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemotePostDataSource : IPostDataSource {

    private val postApi: PostApi

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
        postApi = retrofit.create(PostApi::class.java)
    }

    override fun getPosts(callback: IPostDataSource.IPostCallback) {
        val call: Call<List<Post>> = postApi.getPosts()
        executeCall(call, callback)
    }

    override fun getPostsByUserID(id: Int, callback: IPostDataSource.IPostCallback) {
        val call: Call<List<Post>> = postApi.getPostsByUserID(id)
        executeCall(call, callback)
    }


    private fun executeCall(call: Call<List<Post>>, callback: IPostDataSource.IPostCallback) {
        call.enqueue(object: Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                callback.onFailure()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.body() != null)
                    callback.onPostLoaded(response.body()!!)
                else
                    callback.onFailure()
            }
        } )
    }
}