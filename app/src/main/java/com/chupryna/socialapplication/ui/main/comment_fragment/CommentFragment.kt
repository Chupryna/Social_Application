package com.chupryna.socialapplication.ui.main.comment_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.Comment
import com.chupryna.socialapplication.data.model.Post
import com.chupryna.socialapplication.ui.main.MainActivity

class CommentFragment(private val post: Post) : Fragment(), ICommentView {

    private val presenter by lazy { CommentPresenter(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_comment, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter.onCommentLoad(post.id)
    }

    override fun setAdapter(list: List<Comment>) {

    }

    override fun showProgress() {
        (activity as MainActivity).showProgress()
    }

    override fun hideProgress() {
        (activity as MainActivity).hideProgress()
    }
}