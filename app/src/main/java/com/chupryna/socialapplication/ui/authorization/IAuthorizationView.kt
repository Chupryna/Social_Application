package com.chupryna.socialapplication.ui.authorization

import androidx.fragment.app.Fragment

interface IAuthorizationView {

    fun replaceFragment(fragment: Fragment)

    fun addFragment(fragment: Fragment)

    fun showProgress()

    fun hideProgress()

}