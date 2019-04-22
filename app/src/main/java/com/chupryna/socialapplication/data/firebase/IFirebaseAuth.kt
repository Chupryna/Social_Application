package com.chupryna.socialapplication.data.firebase

import com.chupryna.socialapplication.data.model.User
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser

interface IFirebaseAuth {

    fun attemptSignIn(email: String, password: String, callback: FirebaseCallback)

    fun createNewAccount(user: User, callback: FirebaseCallback)

    interface FirebaseCallback {
        fun onSuccess(user: FirebaseUser)

        fun onFailure(msg: String)
    }

    fun attemptSignInWithGoogle(account: GoogleSignInAccount, callback: FirebaseCallback)
}