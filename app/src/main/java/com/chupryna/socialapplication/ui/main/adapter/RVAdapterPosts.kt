package com.chupryna.socialapplication.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.Post
import com.chupryna.socialapplication.ui.main.comment_fragment.CommentFragment
import com.chupryna.socialapplication.ui.main.post_fragment.IPostView
import kotlinx.android.synthetic.main.fragment_item_post.view.*

class RVAdapterPosts(
    private var postsList: List<Post>,
    private val view: IPostView,
    private val context: Context
) : RecyclerView.Adapter<RVAdapterPosts.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = postsList[position].title
        holder.body.text = postsList[position].body

        holder.likeContainer.setOnClickListener { onLikeClick(holder) }
        holder.commentContainer.setOnClickListener { onCommentClick(position) }
        holder.shareContainer.setOnClickListener { onShareClick(position) }
    }

    private fun onLikeClick(holder: PostViewHolder) {
        if (holder.likeCb.isChecked) {
            holder.likeCb.isChecked = false
            holder.likeCb.setTextColor(ContextCompat.getColor(context, android.R.color.black))
        } else {
            holder.likeCb.isChecked = true
            holder.likeCb.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        }
    }

    private fun onCommentClick(position: Int) {
        view.replaceFragment(CommentFragment(postsList[position]))
    }

    private fun onShareClick(position: Int) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            val text = "${postsList[position].title} \n\n ${postsList[position].body}"
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plane"
        }
        context.startActivity(Intent.createChooser(intent, "Надіслати до"))
    }

    fun updateList(list: List<Post>) {
        postsList = list
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val userPhoto: ImageView = itemView.userPhotoInPostIv
        val title: TextView = itemView.postTitleTv
        val body: TextView = itemView.postBodyTv
        var likeCb: CheckBox = itemView.likeCb
        val likeContainer: View = itemView.postLikeContainer
        val commentContainer: View = itemView.postCommentContainer
        val shareContainer: View = itemView.postShareContainer
    }
}