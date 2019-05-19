package com.chupryna.socialapplication.data.post

import com.chupryna.socialapplication.data.model.Post

interface IPostDataSource {
    interface IPostCallback {
        fun onPostLoaded(list: List<Post>)
        fun onFailure(msg: String)
    }

    fun getPosts(callback: IPostCallback)

    fun getPostsByUserID(id: Int, callback: IPostCallback)
}