package com.chupryna.socialapplication.data.post.remote

import com.chupryna.socialapplication.data.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts")
    fun getPostsByUserID(@Query("userId") userID: Int): Call<List<Post>>
}