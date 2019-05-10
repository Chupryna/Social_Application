package com.chupryna.socialapplication.data.post

import android.content.Context
import com.chupryna.socialapplication.data.model.Post
import com.chupryna.socialapplication.data.post.local.LocalPostDataSource
import com.chupryna.socialapplication.data.post.remote.RemotePostDataSource
import com.chupryna.socialapplication.utils.isNetworkAvailable

class PostRepository(private val context: Context) : IPostDataSource {

    private val remotePostDS = RemotePostDataSource()
    private val localPostDS = LocalPostDataSource(context)

    override fun getPosts(callback: IPostDataSource.IPostCallback) {
        if (context.isNetworkAvailable())
            loadFromRemote(callback)
        else
            loadFromLocal(callback)
    }

    private fun loadFromRemote(callback: IPostDataSource.IPostCallback) {
        remotePostDS.getPosts(object : IPostDataSource.IPostCallback {
            override fun onPostLoaded(list: List<Post>) {
                callback.onPostLoaded(list)
                localPostDS.saveToDB(list)
            }

            override fun onFailure() {
                loadFromLocal(callback)
            }
        })
    }

    private fun loadFromLocal(callback: IPostDataSource.IPostCallback) {
        localPostDS.getPosts(object: IPostDataSource.IPostCallback {
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