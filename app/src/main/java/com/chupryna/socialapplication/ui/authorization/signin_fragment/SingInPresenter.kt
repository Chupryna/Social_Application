package com.chupryna.socialapplication.ui.authorization.signin_fragment

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

class SingInPresenter(private val view: ISignInView) {

    companion object {
        private const val PASSWORD_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}"
    }

    fun onChangeCheckedShowPassword(isChecked: Boolean) {
        if (isChecked)
            view.showPassword()
        else
            view.hidePassword()
    }

    fun onSignIn(email: String, password: String) {
        if (isDataValid(email, password))
            view.attemptAuth()
    }

    private fun isDataValid(email: String, password: String): Boolean {
        view.hideEmailError()
        view.hidePasswordError()

        if (TextUtils.isEmpty(email)) {
            view.showEmailError("Заповніть поле")
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showEmailError("Некоректний формат e-mail")
            return false
        }

        if (TextUtils.isEmpty(password)) {
            view.showPasswordError("Заповніть поле")
            return false
        }

        if(!Pattern.compile(PASSWORD_PATTERN).matcher(password).matches()) {
            view.showPasswordError("Пароль занадто слабкий")
            return false
        }

        return true
    }
}