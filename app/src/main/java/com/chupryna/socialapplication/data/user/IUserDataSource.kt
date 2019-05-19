package com.chupryna.socialapplication.data.user

import com.chupryna.socialapplication.data.model.user.User

interface IUserDataSource {

    interface IUserCallback {
        fun onUserLoaded(list: List<User>)
        fun onFailure(msg: String)
    }

    fun getUsers(callback: IUserCallback)
    fun getUserById(id: Int, iUserCallback: IUserCallback)
}