package com.chupryna.socialapplication.data.todo.remote

import com.chupryna.socialapplication.data.model.ToDo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ToDoApi {

    @GET("todos")
    fun getToDoByUserID(@Query("userId") userID: Int): Call<List<ToDo>>
}