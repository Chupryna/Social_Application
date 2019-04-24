package com.chupryna.socialapplication.data.firebase

import com.chupryna.socialapplication.data.model.User
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import com.facebook.AccessToken

interface IFirebaseAuth {

    interface FirebaseCallback {
        fun onSuccess(user: FirebaseUser)

        fun onFailure(msg: String)
    }

    fun attemptSignIn(email: String, password: String, callback: FirebaseCallback)

    fun attemptSignInWithGoogle(account: GoogleSignInAccount, callback: FirebaseCallback)

    fun attemptSignInWithFacebook(token: AccessToken, callback: FirebaseCallback)

    fun createNewAccount(user: User, callback: FirebaseCallback)

    fun getCurrentUser(): FirebaseUser?

    fun sendPasswordReset(email: String, callback: FirebaseCallback)
}