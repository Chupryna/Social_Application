package com.chupryna.socialapplication.ui.authorization.signin_fragment

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
}