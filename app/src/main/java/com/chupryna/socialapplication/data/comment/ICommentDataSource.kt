package com.chupryna.socialapplication.data.comment

import com.chupryna.socialapplication.data.model.Comment

interface ICommentDataSource {

    interface ICommentCallback {
        fun onCommentLoaded(list: List<Comment>)
        fun onFailure(msg: String)
    }

    fun getCommentsByPostID(id: Int, callback: ICommentCallback)
}