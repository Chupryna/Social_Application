package com.chupryna.socialapplication.data.firebase

import android.content.Context
import android.content.Intent
import com.chupryna.socialapplication.R
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class MethodAuthorization {

    fun signInWithGoogle(context: Context): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        return googleSignInClient.signInIntent
    }

    fun signInWithFacebook(loginButton: LoginButton, callbackManager: CallbackManager, callback: IFirebaseAuth.FirebaseFacebookCallback) {
        loginButton.setReadPermissions("email", "public_profile")
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                callback.onSuccess(loginResult)
            }

            override fun onCancel() {
                callback.onCancel()
            }

            override fun onError(error: FacebookException) {
                callback.onError(error)
            }
        })
    }
}