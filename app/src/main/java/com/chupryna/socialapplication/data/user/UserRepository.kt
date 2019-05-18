package com.chupryna.socialapplication.data.user

import android.content.Context
import com.chupryna.socialapplication.data.model.user.User
import com.chupryna.socialapplication.data.user.local.LocalUserDataSource
import com.chupryna.socialapplication.data.user.remote.RemoteUserDataSource

class UserRepository(private val context: Context) : IUserDataSource {

    private val remoteUserDS = RemoteUserDataSource()
    private val localUserDS = LocalUserDataSource(context)

    override fun getUsers(callback: IUserDataSource.IUserCallback) {
        loadFromRemote(callback)
    }

    private fun loadFromRemote(callback: IUserDataSource.IUserCallback) {
        remoteUserDS.getUsers(object : IUserDataSource.IUserCallback {
            override fun onUserLoaded(list: List<User>) {
                callback.onUserLoaded(list)
                localUserDS.saveToDB(list)
            }

            override fun onFailure() {
                loadFromLocal(callback)
            }
        })
    }

    private fun loadFromLocal(callback: IUserDataSource.IUserCallback) {
        localUserDS.getUsers(object: IUserDataSource.IUserCallback {
            override fun onUserLoaded(list: List<User>) {
                callback.onUserLoaded(list)
            }

            override fun onFailure() {
                callback.onFailure()
            }
        })
    }
}