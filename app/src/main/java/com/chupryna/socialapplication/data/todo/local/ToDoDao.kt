package com.chupryna.socialapplication.data.todo.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.chupryna.socialapplication.data.model.ToDo

@Dao
abstract class ToDoDao {

    @Query("SELECT * FROM ToDo WHERE userId = :userID")
    abstract fun getToDoByUSerID(userID: Int): List<ToDo>

    @Insert
    abstract fun insert(list: List<ToDo>): List<Long>

    @Delete
    abstract fun delete(list: List<ToDo>): Int

    @Query("DELETE FROM ToDo WHERE userId = :userID")
    abstract fun deleteByUSerID(userID: Int): Int
}