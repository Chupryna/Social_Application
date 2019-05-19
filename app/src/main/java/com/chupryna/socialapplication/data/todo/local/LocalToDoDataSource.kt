package com.chupryna.socialapplication.data.todo.local

import android.content.Context
import com.chupryna.socialapplication.data.AppDatabase
import com.chupryna.socialapplication.data.model.ToDo
import com.chupryna.socialapplication.data.todo.IToDoDataSource

class LocalToDoDataSource(context: Context) : IToDoDataSource {

    private val db by lazy { AppDatabase.getInstance(context)!! }

    override fun getToDoByUserID(id: Int, callback: IToDoDataSource.IToDoCallback) {
        val cachedToDo = db.toDoDao().getToDoByUSerID(id)
        if (cachedToDo.isNotEmpty())
            callback.onToDoLoaded(cachedToDo)
        else
            callback.onFailure("")
    }

    fun saveToDB(list: List<ToDo>) {
        db.toDoDao().deleteByUSerID(list[0].userId)
        db.toDoDao().insert(list)
    }
}