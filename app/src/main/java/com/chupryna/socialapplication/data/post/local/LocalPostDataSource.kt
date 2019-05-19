package com.chupryna.socialapplication.data.post.local

import android.content.Context
import com.chupryna.socialapplication.data.AppDatabase
import com.chupryna.socialapplication.data.model.Post
import com.chupryna.socialapplication.data.post.IPostDataSource

class LocalPostDataSource(private val context: Context) : IPostDataSource {

    private val db by lazy { AppDatabase.getInstance(context)!! }

    override fun getPosts(callback: IPostDataSource.IPostCallback) {
        val cashedPosts = db.postDao().getPosts()
        if (cashedPosts.isNotEmpty())
            callback.onPostLoaded(cashedPosts)
        else
            callback.onFailure("")
    }

    override fun getPostsByUserID(id: Int, callback: IPostDataSource.IPostCallback) {

    }

    fun saveToDB(list: List<Post>) {
        db.postDao().insert(list)
    }
}