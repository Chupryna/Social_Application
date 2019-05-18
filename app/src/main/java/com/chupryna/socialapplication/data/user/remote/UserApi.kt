package com.chupryna.socialapplication.data.user.remote

import com.chupryna.socialapplication.data.model.user.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users")
    fun getUserByID(@Query("id") id: Int): Call<List<User>>
}