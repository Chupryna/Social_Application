package com.chupryna.socialapplication.data.user.local

import android.content.Context
import com.chupryna.socialapplication.data.AppDatabase
import com.chupryna.socialapplication.data.model.user.User
import com.chupryna.socialapplication.data.user.IUserDataSource

class LocalUserDataSource(private val context: Context) : IUserDataSource {

    private val db by lazy { AppDatabase.getInstance(context)!! }

    override fun getUsers(callback: IUserDataSource.IUserCallback) {
        val cachedUsers = db.userDao().getUsers()
        if (cachedUsers.isNotEmpty())
            callback.onUserLoaded(cachedUsers)
        else
            callback.onFailure("")
    }

    override fun getUserById(id: Int, iUserCallback: IUserDataSource.IUserCallback) {
        val cachedUser = db.userDao().getUserByID(id)
        if (cachedUser != null)
            iUserCallback.onUserLoaded(listOf(cachedUser))
        else
            iUserCallback.onFailure("")
    }

    fun saveToDB(list: List<User>) {
        db.userDao().deleteAll()
        db.userDao().insert(list)
    }
}