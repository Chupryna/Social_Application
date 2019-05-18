package com.chupryna.socialapplication.ui.main.friend_fragment

import androidx.fragment.app.Fragment
import com.chupryna.socialapplication.data.model.user.User

interface IFriendView {
    fun setAdapter(list: List<User>)

    fun replaceFragment(fragment: Fragment)

    fun showProgress()

    fun hideProgress()
}