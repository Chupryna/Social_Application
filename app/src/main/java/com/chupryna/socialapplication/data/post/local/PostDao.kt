package com.chupryna.socialapplication.data.post.local

import androidx.room.*
import com.chupryna.socialapplication.data.model.Post

@Dao
abstract class PostDao {

    @Query("SELECT * FROM Post")
    abstract fun getPosts(): List<Post>

    @Query("SELECT * FROM Post WHERE userId = :userID")
    abstract fun getPostsByUserID(userID: Int): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(list: List<Post>): List<Long>

    @Delete
    abstract fun delete(list: List<Post>): Int
}