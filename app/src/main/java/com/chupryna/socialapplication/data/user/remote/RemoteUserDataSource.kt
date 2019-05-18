package com.chupryna.socialapplication.data.user.remote

import com.chupryna.socialapplication.BuildConfig
import com.chupryna.socialapplication.data.model.user.User
import com.chupryna.socialapplication.data.user.IUserDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteUserDataSource : IUserDataSource {

    private val userApi: UserApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        userApi = retrofit.create(UserApi::class.java)
    }

    override fun getUsers(callback: IUserDataSource.IUserCallback) {
        userApi.getUsers().enqueue(object: Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                callback.onFailure()
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.body() != null)
                    callback.onUserLoaded(response.body()!!)
                else
                    callback.onFailure()
            }
        })
    }
}