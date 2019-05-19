package com.chupryna.socialapplication.data.comment.remote

import com.chupryna.socialapplication.data.model.Comment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentApi {
    @GET("comments")
    fun getCommentsByPostID(@Query("postId") postId: Int): Call<List<Comment>>
}