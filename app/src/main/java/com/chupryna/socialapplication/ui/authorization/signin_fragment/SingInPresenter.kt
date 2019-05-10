package com.chupryna.socialapplication.ui.authorization.signin_fragment

import android.content.Context
import android.net.ConnectivityManager
import android.text.TextUtils
import android.util.Patterns
import com.chupryna.socialapplication.data.firebase.FirebaseAuthorization
import com.chupryna.socialapplication.data.firebase.IFirebaseAuth
import com.chupryna.socialapplication.data.firebase.MethodAuthorization
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser

class SingInPresenter(private val view: ISignInView) {

    private val model by lazy { FirebaseAuthorization() }

    fun onChangeCheckedShowPassword(isChecked: Boolean) {
        if (isChecked)
            view.showPassword()
        else
            view.hidePassword()
    }

    fun onSignIn(email: String, password: String) {
        if (!isNetworkAvailable()) {
            view.showAuthFailed("Відсутнє з'єднання з мережею")
            return
        }

        view.hidePassword()
        view.hideKeyboard()
        if (isDataValid(email, password)) {
            view.showProgress()
            model.attemptSignIn(email, password, object : IFirebaseAuth.FirebaseUserCallback {
                override fun onSuccess(user: FirebaseUser) {
                    view.hideProgress()
                    if (user.isEmailVerified)
                        view.startMainActivity(user)
                    else {
                        view.showSnackBarSendEmail("Підтвердіть e-mail", "Відправити лист повторно", user)
                        view.showEmailError("Підтвердіть e-mail")
                    }
                }

                override fun onFailure(msg: String) {
                    view.hideProgress()
                    view.showAuthFailed(msg)
                }
            })
        }
    }

    fun onSignInWithFacebook(loginButton: LoginButton, callbackManager: CallbackManager) {
        val methodAuthorization = MethodAuthorization()
        methodAuthorization.signInWithFacebook(loginButton, callbackManager, object: IFirebaseAuth.FirebaseFacebookCallback {
            override fun onSuccess(loginResult: LoginResult) {
                firebaseSignInWithFacebook(loginResult.accessToken)
            }

            override fun onCancel() {
                view.showAuthFailed("Скасування операції")
            }

            override fun onError(error: FacebookException) {
                view.showAuthFailed(error.localizedMessage)
            }
        })
    }

    fun firebaseSignInWithGoogle(account: GoogleSignInAccount) {
        view.showProgress()
        model.attemptSignInWithGoogle(account, object: IFirebaseAuth.FirebaseUserCallback {
            override fun onSuccess(user: FirebaseUser) {
                view.startMainActivity(user)
                view.hideProgress()
            }

            override fun onFailure(msg: String) {
                view.hideProgress()
                view.showAuthFailed(msg)
            }
        })
    }

    fun firebaseSignInWithFacebook(token: AccessToken) {
        view.showProgress()
        model.attemptSignInWithFacebook(token, object: IFirebaseAuth.FirebaseUserCallback {
            override fun onSuccess(user: FirebaseUser) {
                view.startMainActivity(user)
                view.hideProgress()
            }

            override fun onFailure(msg: String) {
                view.hideProgress()
                view.showAuthFailed(msg)
            }
        })
    }

    fun onSignUp() {
        view.showSignUp()
    }

    fun onResetPassword() {
        view.showResetPassword()
    }

    fun sendEmailVerification(user: FirebaseUser) {
        model.sendEmailVerification(user, object: IFirebaseAuth.FirebaseUserCallback {
            override fun onSuccess(user: FirebaseUser) {
                view.showAuthFailed("Лист відправлено. Перевірте свій e-mail")
            }

            override fun onFailure(msg: String) {
                view.showAuthFailed(msg)
            }
        })
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = view.getFragmentContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networks = connectivityManager.allNetworks
        if (networks.isNotEmpty())
            return true

        return false
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

        if(password.length < 6) {
            view.showPasswordError("Пароль занадто короткий")
            return false
        }

        return true
    }
}