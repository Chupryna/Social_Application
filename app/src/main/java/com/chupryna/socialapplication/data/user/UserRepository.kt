package com.chupryna.socialapplication.data.user

import android.content.Context
import com.chupryna.socialapplication.data.model.user.User
import com.chupryna.socialapplication.data.user.local.LocalUserDataSource
import com.chupryna.socialapplication.data.user.remote.RemoteUserDataSource

class UserRepository(context: Context) : IUserDataSource {

    private val remoteUserDS = RemoteUserDataSource()
    private val localUserDS = LocalUserDataSource(context)

    override fun getUsers(callback: IUserDataSource.IUserCallback) {
        loadUsersFromRemote(callback)
    }

    private fun loadUsersFromRemote(callback: IUserDataSource.IUserCallback) {
        remoteUserDS.getUsers(object : IUserDataSource.IUserCallback {
            override fun onUserLoaded(list: List<User>) {
                callback.onUserLoaded(list)
                localUserDS.saveToDB(list)
            }

            override fun onFailure(msg: String) {
                callback.onFailure(msg)
                loadUsersFromLocal(callback)
            }
        })
    }

    private fun loadUsersFromLocal(callback: IUserDataSource.IUserCallback) {
        localUserDS.getUsers(object: IUserDataSource.IUserCallback {
            override fun onUserLoaded(list: List<User>) {
                callback.onUserLoaded(list)
            }

            override fun onFailure(msg: String) {
                callback.onFailure(msg)
            }
        })
    }

    override fun getUserById(id: Int, iUserCallback: IUserDataSource.IUserCallback) {
        loadUserByIDFromRemote(id, iUserCallback)
    }

    private fun loadUserByIDFromRemote(id: Int, callback: IUserDataSource.IUserCallback) {
        remoteUserDS.getUserById(id, object: IUserDataSource.IUserCallback {
            override fun onUserLoaded(list: List<User>) {
                callback.onUserLoaded(list)
            }

            override fun onFailure(msg: String) {
                callback.onFailure(msg)
                loadUserByIDFromLocal(id, callback)
            }
        })
    }

    private fun loadUserByIDFromLocal(id: Int, callback: IUserDataSource.IUserCallback) {
        localUserDS.getUserById(id, object: IUserDataSource.IUserCallback {
            override fun onUserLoaded(list: List<User>) {
                callback.onUserLoaded(list)
            }

            override fun onFailure(msg: String) {
                callback.onFailure(msg)
            }
        })
    }
}