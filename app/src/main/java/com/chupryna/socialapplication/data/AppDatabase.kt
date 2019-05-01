package com.chupryna.socialapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chupryna.socialapplication.data.album.local.AlbumDao
import com.chupryna.socialapplication.data.model.Album

@Database(entities = [Album::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null)
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "weather")
                    .allowMainThreadQueries()
                    .build()
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}