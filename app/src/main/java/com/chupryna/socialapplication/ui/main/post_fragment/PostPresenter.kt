package com.chupryna.socialapplication.ui.main.post_fragment

import android.content.Context
import com.chupryna.socialapplication.data.model.Post
import com.chupryna.socialapplication.data.post.IPostDataSource
import com.chupryna.socialapplication.data.post.PostRepository

class PostPresenter(
    private val view: IPostView,
    private val context: Context) {

    private val model by lazy { PostRepository(context) }

    fun onPostLoad() {
        view.showProgress()
        model.getPosts(object: IPostDataSource.IPostCallback {
            override fun onPostLoaded(list: List<Post>) {
                view.setAdapter(list)
                view.hideProgress()
            }

            override fun onFailure(msg: String) {
                view.hideProgress()
                if (msg.isNotEmpty())
                    view.showToast(msg)
            }
        })
    }

    fun onPostUpdate() {
        view.showRefreshing()
        model.getPosts(object: IPostDataSource.IPostCallback {
            override fun onPostLoaded(list: List<Post>) {
                view.updateAdapter(list)
                view.hideRefreshing()
            }

            override fun onFailure(msg: String) {
                view.hideRefreshing()
                if (msg.isNotEmpty())
                    view.showToast(msg)
            }
        })
    }
}