package com.chupryna.socialapplication.ui.main.comment_fragment

import com.chupryna.socialapplication.data.comment.CommentRepository
import com.chupryna.socialapplication.data.comment.ICommentDataSource
import com.chupryna.socialapplication.data.model.Comment

class CommentPresenter(private val view: ICommentView) {

    private val model by lazy { CommentRepository() }

    fun onCommentLoad(id: Int) {
        view.showProgress()
        model.getCommentsByPostID(id, object: ICommentDataSource.ICommentCallback {
            override fun onCommentLoaded(list: List<Comment>) {
                list.size
                view.hideProgress()
            }

            override fun onFailure() {
                view.hideProgress()
            }
        })
    }
}