package com.chupryna.socialapplication.ui.main.post_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.Post
import com.chupryna.socialapplication.ui.main.MainActivity
import com.chupryna.socialapplication.ui.main.adapter.RVAdapterPosts
import kotlinx.android.synthetic.main.fragment_post.*

class PostFragment : Fragment(), IPostView {

    private val presenter by lazy { PostPresenter(this, context!!) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onStart() {
        super.onStart()
        initView()
        initListeners()
        presenter.onPostLoad()
    }

    private fun initListeners() {
        postSwipeRefreshLayout.setOnRefreshListener { presenter.onPostUpdate() }
    }

    private fun initView() {
        postsRV.layoutManager = LinearLayoutManager(context)
    }

    override fun showProgress() {
        (activity as MainActivity).showProgress()
    }

    override fun hideProgress() {
        (activity as MainActivity).hideProgress()
    }

    override fun showRefreshing() {
        postSwipeRefreshLayout.isRefreshing = true
    }

    override fun hideRefreshing() {
        postSwipeRefreshLayout.isRefreshing = false
    }

    override fun setAdapter(list: List<Post>) {
        postsRV.adapter = RVAdapterPosts(list, this, context!!)
    }

    override fun updateAdapter(list: List<Post>) {
        (postsRV.adapter as RVAdapterPosts).updateList(list)
    }

    override fun replaceFragment(fragment: Fragment) {
        (activity as MainActivity).replaceFragment(fragment)
    }
}