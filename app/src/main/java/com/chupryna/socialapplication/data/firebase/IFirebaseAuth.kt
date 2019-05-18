package com.chupryna.socialapplication.data.firebase

import com.chupryna.socialapplication.data.model.UserFirebase
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import com.facebook.AccessToken
import com.facebook.FacebookException
import com.facebook.login.LoginResult

interface IFirebaseAuth {

    interface FirebaseUserCallback {
        fun onSuccess(user: FirebaseUser)

        fun onFailure(msg: String)
    }

    interface FirebaseCallback {
        fun onSuccess()

        fun onFailure(msg: String)
    }

    interface FirebaseFacebookCallback {
        fun onSuccess(loginResult: LoginResult)

        fun onCancel()

        fun onError(error: FacebookException)
    }

    fun attemptSignIn(email: String, password: String, callback: FirebaseUserCallback)

    fun attemptSignInWithGoogle(account: GoogleSignInAccount, callback: FirebaseUserCallback)

    fun attemptSignInWithFacebook(token: AccessToken, callback: FirebaseUserCallback)

    fun createNewAccount(user: UserFirebase, callback: FirebaseUserCallback)

    fun sendPasswordReset(email: String, callback: FirebaseCallback)

    fun getCurrentUser(): FirebaseUser?

    fun signOut(callback: FirebaseCallback)
}