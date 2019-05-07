package com.chupryna.socialapplication.ui.authorization

import android.content.Context
import android.net.ConnectivityManager
import androidx.fragment.app.Fragment
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

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networks = connectivityManager.allNetworks
        if (networks.isNotEmpty())
            return true

        return false
    }
}