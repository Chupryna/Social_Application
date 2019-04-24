package com.chupryna.socialapplication.ui.authorization

import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseUser

interface IAuthorizationView {

    fun replaceFragment(fragment: Fragment)

    fun addFragment(fragment: Fragment)

    fun showProgress()

    fun hideProgress()

    fun hideKeyboard(view: View)

    fun showToast(msg: String)

    fun startMainActivity(user: FirebaseUser)
}