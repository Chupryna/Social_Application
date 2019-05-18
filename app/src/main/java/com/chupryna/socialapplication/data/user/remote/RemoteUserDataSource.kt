package com.chupryna.socialapplication.data.user.remote

import com.chupryna.socialapplication.BuildConfig
import com.chupryna.socialapplication.data.model.user.User
import com.chupryna.socialapplication.data.user.IUserDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteUserDataSource : IUserDataSource {

    private val userApi: UserApi

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
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

    override fun getUserById(id: Int, iUserCallback: IUserDataSource.IUserCallback) {
        userApi.getUserByID(id).enqueue(object: Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                iUserCallback.onFailure()
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
               if (response.body() != null)
                   iUserCallback.onUserLoaded(response.body()!!)
                else
                   iUserCallback.onFailure()
            }
        })
    }
}