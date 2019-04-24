package com.chupryna.socialapplication.ui.authorization.reset_password_fragment

import android.text.TextUtils
import android.util.Patterns
import com.chupryna.socialapplication.data.firebase.FirebaseAuthorization
import com.chupryna.socialapplication.data.firebase.IFirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ResetPasswordPresenter(private val view: IResetPasswordView) {

    private val model by lazy { FirebaseAuthorization() }

    fun onResetPassword(email: String) {
        view.hideKeyboard()
        if (isEmailValid(email)) {
            view.showProgress()
            model.sendPasswordReset(email, object: IFirebaseAuth.FirebaseCallback {
                override fun onSuccess() {
                    view.hideProgress()
                    view.showToast("Перевірте свій e-mail")
                    view.goToSignIn()
                }

                override fun onFailure(msg: String) {
                    view.hideProgress()
                    view.showToast(msg)
                }
            })
        }
    }

    private fun isEmailValid(email: String): Boolean {
        view.hideEmailError()
        if (TextUtils.isEmpty(email)) {
            view.showEmailError("Заповніть поле")
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showEmailError("Некоректний формат e-mail")
            return false
        }
        return true
    }
}