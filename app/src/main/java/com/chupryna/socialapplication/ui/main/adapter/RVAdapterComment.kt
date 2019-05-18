package com.chupryna.socialapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.Comment
import kotlinx.android.synthetic.main.fragmant_item_comment.view.*

class RVAdapterComment(private val list: List<Comment>) : RecyclerView.Adapter<RVAdapterComment.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragmant_item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.fullName.text = list[position].name
        holder.email.text = list[position].email
        holder.body.text = list[position].body
    }


    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullName: TextView = itemView.fullNameCommentTv
        val email: TextView = itemView.emailCommentTv
        val body: TextView = itemView.bodyCommentTv
    }
}