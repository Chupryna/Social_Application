package com.chupryna.socialapplication.ui.main.comment_fragment

import android.content.Context
import com.chupryna.socialapplication.data.comment.CommentRepository
import com.chupryna.socialapplication.data.comment.ICommentDataSource
import com.chupryna.socialapplication.data.model.Comment

class CommentPresenter(private val view: ICommentView,
                       context: Context) {

    private val model by lazy { CommentRepository(context) }

    fun onCommentLoad(id: Int) {
        view.showProgress()
        model.getCommentsByPostID(id, object: ICommentDataSource.ICommentCallback {
            override fun onCommentLoaded(list: List<Comment>) {
                view.setAdapter(list)
                view.hideProgress()
            }

            override fun onFailure() {
                view.hideProgress()
            }
        })
    }
}