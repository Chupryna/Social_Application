package com.chupryna.socialapplication.data.comment.remote

import com.chupryna.socialapplication.BuildConfig
import com.chupryna.socialapplication.data.comment.ICommentDataSource
import com.chupryna.socialapplication.data.model.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteCommentDataSource : ICommentDataSource {

    private val commentApi: CommentApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        commentApi = retrofit.create(CommentApi::class.java)
    }

    override fun getCommentsByPostID(id: Int, callback: ICommentDataSource.ICommentCallback) {
        commentApi.getCommentsByPostID(id).enqueue(object: Callback<List<Comment>> {
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                callback.onFailure()
            }

            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.body() != null)
                    callback.onCommentLoaded(response.body()!!)
                else
                    callback.onFailure()
            }
        })
    }
}