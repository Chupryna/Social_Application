package com.chupryna.socialapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.user.User
import com.chupryna.socialapplication.ui.main.friend_fragment.IFriendView
import com.chupryna.socialapplication.ui.main.profile_fragment.ProfileFragment
import kotlinx.android.synthetic.main.fragment_item_friend.view.*

class RVAdapterFriends(private val listFriends: List<User>,
                       private val view: IFriendView) : RecyclerView.Adapter<RVAdapterFriends.FriendViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_friend, parent, false)
        return FriendViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFriends.size
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.fullName.text = String.format("%s (%s)", listFriends[position].name, listFriends[position].username)
        holder.city.text = listFriends[position].address.city

        holder.itemView.setOnClickListener { view.replaceFragment(ProfileFragment()) }
    }

    class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullName: TextView = itemView.fullNameFriendTv
        val city: TextView = itemView.cityFriendTv
    }
}