package com.chupryna.socialapplication.ui.main.comment_fragment

import com.chupryna.socialapplication.data.model.Comment

interface ICommentView {
    fun setAdapter(list: List<Comment>)

    fun showProgress()

    fun hideProgress()

    fun showToast(msg: String)
}