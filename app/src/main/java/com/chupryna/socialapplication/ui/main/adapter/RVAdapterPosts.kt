package com.chupryna.socialapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.Post
import kotlinx.android.synthetic.main.fragment_item_post.view.*

class RVAdapterPosts(private val postsList: List<Post>) : RecyclerView.Adapter<RVAdapterPosts.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = postsList[position].title
        holder.body.text = postsList[position].body
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val userPhoto: ImageView = itemView.userPhotoInPostIv
        val title: TextView = itemView.postTitleTv
        val body: TextView = itemView.postBodyTv
    }
}