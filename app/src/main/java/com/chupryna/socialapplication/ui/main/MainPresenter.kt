package com.chupryna.socialapplication.ui.main

import com.chupryna.socialapplication.data.firebase.FirebaseAuthorization
import com.chupryna.socialapplication.data.firebase.IFirebaseAuth
import com.chupryna.socialapplication.ui.main.album_fragment.AlbumFragment
import com.chupryna.socialapplication.ui.main.friend_fragment.FriendFragment
import com.chupryna.socialapplication.ui.main.post_fragment.PostFragment
import com.chupryna.socialapplication.ui.main.todo_fragment.ToDoFragment

class MainPresenter(private val view: IMainView) {

    private val model by lazy { FirebaseAuthorization() }

    fun onQuit() {
        model.signOut(object: IFirebaseAuth.FirebaseCallback {
            override fun onSuccess() {
                view.loadAuthActivity()
            }

            override fun onFailure(msg: String) {

            }
        })
    }

    fun onProfile() {
        view.closeDrawer()
      //  view.replaceFragment(ProfileFragment())
      //  view.setTitle("Профіль")
    }

    fun onAlbums() {
        view.closeDrawer()
        view.replaceFragment(AlbumFragment())
        view.setTitle("Альбоми")
    }

    fun onPosts() {
        view.closeDrawer()
        view.replaceFragment(PostFragment())
        view.setTitle("Новини")
    }

    fun onFriends() {
        view.closeDrawer()
        view.replaceFragment(FriendFragment())
        view.setTitle("Друзі")
    }

    fun onToDo() {
        view.closeDrawer()
        view.replaceFragment(ToDoFragment())
        view.setTitle("To Do")
    }
}