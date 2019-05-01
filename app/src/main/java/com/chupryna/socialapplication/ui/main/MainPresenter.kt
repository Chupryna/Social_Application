package com.chupryna.socialapplication.ui.main

import com.chupryna.socialapplication.data.firebase.FirebaseAuthorization
import com.chupryna.socialapplication.data.firebase.IFirebaseAuth
import com.chupryna.socialapplication.ui.main.album_fragment.AlbumFragment
import com.chupryna.socialapplication.ui.main.profile_fragment.ProfileFragment

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
        view.replaceFragment(ProfileFragment())
        view.setTitle("Профіль")
    }

    fun onAlbums() {
        view.closeDrawer()
        view.replaceFragment(AlbumFragment())
        view.setTitle("Альбоми")
    }
}