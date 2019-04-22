package com.chupryna.socialapplication.ui.authorization

import android.content.Context
import android.net.ConnectivityManager
import androidx.fragment.app.Fragment
import com.chupryna.socialapplication.ui.authorization.signin_fragment.SignInFragment

class AuthorizationPresenter(private val view: IAuthorizationView) {

    fun onLoadActivity() {
        view.addFragment(SignInFragment())
    }

    fun onChangeFragment(fragment: Fragment) {
        view.replaceFragment(fragment)
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networks = connectivityManager.allNetworks
        if (networks.isNotEmpty())
            return true

        return false
    }

}