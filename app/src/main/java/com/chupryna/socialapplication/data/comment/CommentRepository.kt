package com.chupryna.socialapplication.data.comment

import android.content.Context
import com.chupryna.socialapplication.data.comment.local.LocalCommentDataSource
import com.chupryna.socialapplication.data.comment.remote.RemoteCommentDataSource
import com.chupryna.socialapplication.data.model.Comment

class CommentRepository(context: Context) : ICommentDataSource {

    private val remoteCommentDS = RemoteCommentDataSource()
    private val localCommentDS = LocalCommentDataSource(context)

    override fun getCommentsByPostID(id: Int, callback: ICommentDataSource.ICommentCallback) {
        loadFromRemote(id, callback)
    }

    private fun loadFromRemote(id: Int, callback: ICommentDataSource.ICommentCallback) {
        remoteCommentDS.getCommentsByPostID(id, object: ICommentDataSource.ICommentCallback {
            override fun onCommentLoaded(list: List<Comment>) {
                callback.onCommentLoaded(list)
                localCommentDS.saveToDB(list)
            }

            override fun onFailure(msg: String) {
                callback.onFailure(msg)
                loadFromLocal(id, callback)
            }
        })
    }

    private fun loadFromLocal(id: Int, callback: ICommentDataSource.ICommentCallback) {
        localCommentDS.getCommentsByPostID(id, object: ICommentDataSource.ICommentCallback {
            override fun onCommentLoaded(list: List<Comment>) {
                callback.onCommentLoaded(list)
            }

            override fun onFailure(msg: String) {
                callback.onFailure(msg)
            }
        })
    }
}