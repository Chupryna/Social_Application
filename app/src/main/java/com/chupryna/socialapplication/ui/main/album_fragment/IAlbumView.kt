package com.chupryna.socialapplication.ui.main.album_fragment

import androidx.fragment.app.Fragment
import com.chupryna.socialapplication.data.model.Album

interface IAlbumView {
    fun setAdapter(list: List<Album>)

    fun replaceFragment(fragment: Fragment)

    fun showProgress()

    fun hideProgress()

    fun showToast(msg: String)
}