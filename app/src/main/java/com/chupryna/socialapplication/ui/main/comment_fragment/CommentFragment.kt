package com.chupryna.socialapplication.ui.main.comment_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.Comment
import com.chupryna.socialapplication.data.model.Post
import com.chupryna.socialapplication.ui.main.MainActivity
import com.chupryna.socialapplication.ui.main.adapter.RVAdapterComment
import kotlinx.android.synthetic.main.fragment_comment.*

class CommentFragment(private val post: Post) : Fragment(), ICommentView {

    private val presenter by lazy { CommentPresenter(this, context!!) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_comment, container, false)
    }

    override fun onStart() {
        super.onStart()
        initView()
        presenter.onCommentLoad(post.id)
    }

    private fun initView() {
        commentRv.layoutManager = LinearLayoutManager(context)
    }

    override fun setAdapter(list: List<Comment>) {
        commentRv.adapter = RVAdapterComment(list)
    }

    override fun showProgress() {
        (activity as MainActivity).showProgress()
    }

    override fun hideProgress() {
        (activity as MainActivity).hideProgress()
    }

    override fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}