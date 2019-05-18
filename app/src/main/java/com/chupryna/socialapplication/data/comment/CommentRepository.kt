package com.chupryna.socialapplication.data.comment

import com.chupryna.socialapplication.data.comment.remote.RemoteCommentDataSource
import com.chupryna.socialapplication.data.model.Comment

class CommentRepository : ICommentDataSource {

    private val remoteCommentDS = RemoteCommentDataSource()

    override fun getCommentsByPostID(id: Int, callback: ICommentDataSource.ICommentCallback) {
        loadFromRemote(id, callback)
    }

    private fun loadFromRemote(id: Int, callback: ICommentDataSource.ICommentCallback) {
        remoteCommentDS.getCommentsByPostID(id, object: ICommentDataSource.ICommentCallback {
            override fun onCommentLoaded(list: List<Comment>) {
                callback.onCommentLoaded(list)
            }

            override fun onFailure() {

            }
        })
    }
}