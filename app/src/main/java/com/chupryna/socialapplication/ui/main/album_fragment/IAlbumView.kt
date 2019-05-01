package com.chupryna.socialapplication.ui.main.album_fragment

import com.chupryna.socialapplication.data.model.Album

interface IAlbumView {
    fun setAdapter(list: List<Album>)
}