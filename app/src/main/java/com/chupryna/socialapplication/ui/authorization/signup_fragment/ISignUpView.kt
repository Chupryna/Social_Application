package com.chupryna.socialapplication.ui.authorization.signup_fragment

interface ISignUpView {

    fun showFullNameError(msg: String)

    fun showEmailError(msg: String)

    fun showPasswordError(msg: String)

    fun hideFullNameError()

    fun hideEmailError()

    fun hidePasswordError()

    fun showToast(msg: String)

    fun hideKeyboard()

    fun showSignIn()

    fun showProgress()

    fun hideProgress()

    fun checkPermission()
}