package com.chupryna.socialapplication.data.user.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.chupryna.socialapplication.data.model.user.User

@Dao
abstract class UserDao {

    @Query("SELECT * FROM user WHERE id = :id")
    abstract fun getUserByID(id: Int): User?

    @Query("SELECT * FROM user")
    abstract fun getUsers(): List<User>

    @Insert
    abstract fun insert(list: List<User>): List<Long>

    @Delete
    abstract fun delete(list: List<User>): Int

    @Query("DELETE FROM user")
    abstract fun deleteAll()
}