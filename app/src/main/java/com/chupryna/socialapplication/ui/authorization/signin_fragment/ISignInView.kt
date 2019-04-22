package com.chupryna.socialapplication.ui.authorization.signin_fragment

import android.content.Context
import com.google.firebase.auth.FirebaseUser

interface ISignInView {

    fun showPassword()

    fun hidePassword()

    fun showProgress()

    fun hideProgress()

    fun showEmailError(msg: String)

    fun showPasswordError(msg: String)

    fun hideEmailError()

    fun hidePasswordError()

    fun showAuthFailed(msg: String)

    fun hideKeyboard()

    fun getFragmentContext(): Context

    fun showSignUp()

    fun showSnackBarSendEmail(msg: String, action: String, user: FirebaseUser)
}