package com.chupryna.socialapplication.ui.main.friend_fragment

import android.content.Context
import com.chupryna.socialapplication.data.model.user.User
import com.chupryna.socialapplication.data.user.IUserDataSource
import com.chupryna.socialapplication.data.user.UserRepository

class FriendPresenter(private val view: IFriendView,
                      private val context: Context) {

    private val model by lazy { UserRepository(context) }

    fun onLoadFriend() {
        view.showProgress()
        model.getUsers(object: IUserDataSource.IUserCallback {
            override fun onUserLoaded(list: List<User>) {
                view.setAdapter(list)
                view.hideProgress()
            }

            override fun onFailure(msg: String) {
                view.hideProgress()
                if (msg.isNotEmpty())
                    view.showToast(msg)
            }
        })
    }
}