package com.chupryna.socialapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chupryna.socialapplication.data.album.local.AlbumDao
import com.chupryna.socialapplication.data.comment.local.CommentDao
import com.chupryna.socialapplication.data.model.Album
import com.chupryna.socialapplication.data.model.Comment
import com.chupryna.socialapplication.data.model.Photo
import com.chupryna.socialapplication.data.model.Post
import com.chupryna.socialapplication.data.model.user.User
import com.chupryna.socialapplication.data.photo.download.local.PhotoDao
import com.chupryna.socialapplication.data.post.local.PostDao
import com.chupryna.socialapplication.data.user.local.UserDao

@Database(entities = [Album::class, Photo::class, Post::class, User::class, Comment::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao
    abstract fun photoDao(): PhotoDao
    abstract fun postDao(): PostDao
    abstract fun userDao(): UserDao
    abstract fun commentDao(): CommentDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null)
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "social")
                    .allowMainThreadQueries()
                    .build()
            return instance
        }
    }
}