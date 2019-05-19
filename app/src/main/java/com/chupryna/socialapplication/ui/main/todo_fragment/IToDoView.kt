package com.chupryna.socialapplication.ui.main.todo_fragment

import com.chupryna.socialapplication.data.model.ToDo

interface IToDoView {
    fun setAdapter(list: List<ToDo>)

    fun showProgress()

    fun hideProgress()
}