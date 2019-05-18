package com.chupryna.socialapplication.data.comment.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.chupryna.socialapplication.data.model.Comment

@Dao
abstract class CommentDao {

    @Query("SELECT * FROM comment WHERE postId = :postID")
    abstract fun getCommentsByPostID(postID: Int): List<Comment>

    @Insert
    abstract fun insert(list: List<Comment>): List<Long>

    @Query("DELETE FROM Comment WHERE postId = :postID")
    abstract fun deleteByPostID(postID: Int): Int
}