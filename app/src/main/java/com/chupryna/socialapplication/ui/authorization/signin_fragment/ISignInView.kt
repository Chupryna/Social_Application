package com.chupryna.socialapplication.ui.authorization.signin_fragment

interface ISignInView {

    fun showPassword()

    fun hidePassword()

    //fun isDataValid(): Boolean

    fun attemptAuth()

    fun showEmailError(msg: String)

    fun showPasswordError(msg: String)

    fun hideEmailError()

    fun hidePasswordError()
}