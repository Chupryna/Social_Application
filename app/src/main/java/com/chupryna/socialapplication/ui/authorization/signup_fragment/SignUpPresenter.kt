package com.chupryna.socialapplication.ui.authorization.signup_fragment

import android.text.TextUtils
import android.util.Patterns
import com.chupryna.socialapplication.data.firebase.FirebaseAuthorization
import com.chupryna.socialapplication.data.firebase.IFirebaseAuth
import com.chupryna.socialapplication.data.model.User
import com.google.firebase.auth.FirebaseUser
import java.util.regex.Pattern

class SignUpPresenter(private val view: ISignUpView) {

    companion object {
        private const val PASSWORD_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}"
    }

    private val model by lazy { FirebaseAuthorization() }

    fun onSignUp(fullName: String, email: String, password: String, confirmPassword: String) {
        if (!view.checkNetwork()) {
            view.showSignUpFailed("Відсутнє з'єднання з мережею")
            return
        }

        view.hideKeyboard()
        view.showProgress()
        if (isDataValid(fullName, email, password,confirmPassword)) {
            model.createNewAccount(User(fullName, email, password, null), object: IFirebaseAuth.FirebaseUserCallback {
                override fun onSuccess(user: FirebaseUser) {
                    view.hideProgress()
                    onSignIn()
                }

                override fun onFailure(msg: String) {
                    view.hideProgress()
                    view.showSignUpFailed(msg)
                    view.showEmailError(msg)
                }
            })
        }
    }

    fun onSignIn() {
        view.showSignIn()
    }

    private fun isDataValid(fullName: String, email: String, password: String, confirmPassword: String): Boolean {
        view.hideFullNameError()
        view.hideEmailError()
        view.hidePasswordError()

        if (TextUtils.isEmpty(fullName)) {
            view.showFullNameError("Заповніть поле")
            return false
        }

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

        if (password != confirmPassword) {
            view.showPasswordError("Паролі не співпадають")
            return false
        }

        return true
    }
}