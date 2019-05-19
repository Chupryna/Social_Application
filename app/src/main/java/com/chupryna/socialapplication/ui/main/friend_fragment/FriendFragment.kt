package com.chupryna.socialapplication.ui.main.friend_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.user.User
import com.chupryna.socialapplication.ui.main.MainActivity
import com.chupryna.socialapplication.ui.main.adapter.RVAdapterFriends
import kotlinx.android.synthetic.main.fragment_friend.*

class FriendFragment : Fragment(), IFriendView {

    private val presenter by lazy { FriendPresenter(this, context!!) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_friend, container, false)
    }

    override fun onStart() {
        super.onStart()
        initView()
        presenter.onLoadFriend()
    }

    private fun initView() {
        friendRv.layoutManager = LinearLayoutManager(context)
    }

    override fun setAdapter(list: List<User>) {
        friendRv.adapter = RVAdapterFriends(list, this)
    }

    override fun replaceFragment(fragment: Fragment) {
        (activity as MainActivity).replaceFragment(fragment)
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