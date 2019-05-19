package com.chupryna.socialapplication.data.todo

import com.chupryna.socialapplication.data.model.ToDo

interface IToDoDataSource {

    interface IToDoCallback {
        fun onToDoLoaded(list: List<ToDo>)
        fun onFailure()
    }

    fun getToDoByUserID(id: Int, callback: IToDoCallback)
}