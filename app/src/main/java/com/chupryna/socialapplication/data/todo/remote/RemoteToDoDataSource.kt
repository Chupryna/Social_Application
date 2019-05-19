package com.chupryna.socialapplication.data.todo.remote

import com.chupryna.socialapplication.BuildConfig
import com.chupryna.socialapplication.data.model.ToDo
import com.chupryna.socialapplication.data.todo.IToDoDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteToDoDataSource : IToDoDataSource {

    private val toDoApi: ToDoApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        toDoApi = retrofit.create(ToDoApi::class.java)
    }

    override fun getToDoByUserID(id: Int, callback: IToDoDataSource.IToDoCallback) {
        toDoApi.getToDoByUserID(id).enqueue(object: Callback<List<ToDo>> {
            override fun onFailure(call: Call<List<ToDo>>, t: Throwable) {
                callback.onFailure("Не вдалося отримати оновлені дані. Перевірте з'єднання")
            }

            override fun onResponse(call: Call<List<ToDo>>, response: Response<List<ToDo>>) {
                if (response.body() != null)
                    callback.onToDoLoaded(response.body()!!)
                else
                    callback.onFailure("Не вдалося отримати оновлені дані. Перевірте з'єднання")
            }
        })
    }
}