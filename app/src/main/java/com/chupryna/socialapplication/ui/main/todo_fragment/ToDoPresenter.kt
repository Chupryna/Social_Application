package com.chupryna.socialapplication.ui.main.todo_fragment

import android.content.Context
import com.chupryna.socialapplication.data.model.ToDo
import com.chupryna.socialapplication.data.todo.IToDoDataSource
import com.chupryna.socialapplication.data.todo.ToDoRepository

class ToDoPresenter(private val view: IToDoView,
                    context: Context) {

    private val model by lazy { ToDoRepository(context) }

    fun loadToDo() {
        view.showProgress()
        model.getToDoByUserID(1, object: IToDoDataSource.IToDoCallback {
            override fun onToDoLoaded(list: List<ToDo>) {
                view.setAdapter(list)
                view.hideProgress()
            }

            override fun onFailure() {
                view.hideProgress()
            }
        })
    }
}