package com.chupryna.socialapplication.data.post

import android.content.Context
import com.chupryna.socialapplication.data.model.Post
import com.chupryna.socialapplication.data.post.remote.RemotePostDataSource

class PostRepository(private val context: Context) : IPostDataSource {

    private val remotePostDS = RemotePostDataSource()

    override fun getPosts(callback: IPostDataSource.IPostCallback) {
        loadFromRemote(callback)
    }

    private fun loadFromRemote(callback: IPostDataSource.IPostCallback) {
        remotePostDS.getPosts(object : IPostDataSource.IPostCallback {
            override fun onPostLoaded(list: List<Post>) {
                callback.onPostLoaded(list)
            }

            override fun onFailure() {
                callback.onFailure()
            }
        })
    }

    override fun getPostsByUserID(id: Int, callback: IPostDataSource.IPostCallback) {

    }
}