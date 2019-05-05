package com.chupryna.socialapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chupryna.socialapplication.data.album.local.AlbumDao
import com.chupryna.socialapplication.data.model.Album
import com.chupryna.socialapplication.data.model.Photo
import com.chupryna.socialapplication.data.photo.local.PhotoDao

@Database(entities = [Album::class, Photo::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao
    abstract fun photoDao(): PhotoDao

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