package com.chupryna.socialapplication.ui.authorization

import com.chupryna.socialapplication.data.firebase.FirebaseAuthorization
import com.chupryna.socialapplication.ui.authorization.signin_fragment.SignInFragment

class AuthorizationPresenter(private val view: IAuthorizationView) {

    private val model by lazy { FirebaseAuthorization() }

    fun onLoadActivity() {
        val user = model.getCurrentUser()
        if (user == null)
            view.addFragment(SignInFragment())
        else
            view.startMainActivity(user)
    }
}