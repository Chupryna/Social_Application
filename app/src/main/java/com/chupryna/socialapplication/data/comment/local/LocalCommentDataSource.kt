package com.chupryna.socialapplication.data.comment.local

import android.content.Context
import com.chupryna.socialapplication.data.AppDatabase
import com.chupryna.socialapplication.data.comment.ICommentDataSource
import com.chupryna.socialapplication.data.model.Comment

class LocalCommentDataSource(context: Context) : ICommentDataSource {

    private val db by lazy { AppDatabase.getInstance(context)!! }

    override fun getCommentsByPostID(id: Int, callback: ICommentDataSource.ICommentCallback) {
        val cachedComments = db.commentDao().getCommentsByPostID(id)
        if (cachedComments.isNotEmpty())
            callback.onCommentLoaded(cachedComments)
        else
            callback.onFailure("")
    }

    fun saveToDB(list: List<Comment>) {
        db.commentDao().deleteByPostID(list[0].postId)
        db.commentDao().insert(list)
    }
}