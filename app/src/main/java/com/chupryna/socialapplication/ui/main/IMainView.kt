package com.chupryna.socialapplication.ui.main

import androidx.fragment.app.Fragment

interface IMainView {

    fun replaceFragment(fragment: Fragment)

    fun addFragment(fragment: Fragment)

    fun showProgress()

    fun hideProgress()

    fun loadAuthActivity()

    fun closeDrawer()

    fun setTitle(title: String)
}