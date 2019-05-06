package com.chupryna.socialapplication.ui.main.post_fragment

import androidx.fragment.app.Fragment
import com.chupryna.socialapplication.data.model.Post

interface IPostView {

    fun showProgress()

    fun hideProgress()

    fun setAdapter(list: List<Post>)

    fun replaceFragment(fragment: Fragment)
}