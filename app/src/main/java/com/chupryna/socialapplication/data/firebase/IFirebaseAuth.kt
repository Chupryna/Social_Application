package com.chupryna.socialapplication.data.firebase

interface IFirebaseAuth {

    fun attemptSignIn(email: String, password: String, callback: AuthorizationCallback)

    fun createNewAccount(): Boolean

    interface AuthorizationCallback {
        fun onSuccess()

        fun onFailure(msg: String)
    }
}