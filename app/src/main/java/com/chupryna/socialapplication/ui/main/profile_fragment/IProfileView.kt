package com.chupryna.socialapplication.ui.main.profile_fragment

import android.content.Intent

interface IProfileView {

    fun setFullName(name: String)

    fun setUserName(name: String)

    fun setWebsite(website: String)

    fun setPhone(phone: String)

    fun setEmail(email: String)

    fun setCompany(company: String)

    fun setAddress(address: String)

    fun setZipCode(code: String)

    fun showActivity(intent: Intent)
}