package com.chupryna.socialapplication.ui.authorization.reset_password_fragment

interface IResetPasswordView {

    fun showEmailError(msg: String)

    fun hideEmailError()

    fun hideKeyboard()

    fun showToast(msg: String)

    fun showProgress()

    fun hideProgress()

    fun goToSignIn()
}