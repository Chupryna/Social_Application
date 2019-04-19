package com.chupryna.socialapplication.ui.authorization

import androidx.fragment.app.Fragment
import com.chupryna.socialapplication.ui.authorization.signin_fragment.SignInFragment

class AuthorizationPresenter(private val view: IAuthorizationView) {

    fun onLoadActivity() {
        view.addFragment(SignInFragment())
    }

    fun onChangeFragment(fragment: Fragment) {
        view.replaceFragment(fragment)
    }

}