package com.chupryna.socialapplication.data.todo

import android.content.Context
import com.chupryna.socialapplication.data.model.ToDo
import com.chupryna.socialapplication.data.todo.local.LocalToDoDataSource
import com.chupryna.socialapplication.data.todo.remote.RemoteToDoDataSource

class ToDoRepository(context: Context) : IToDoDataSource{

    private val remoteToDoDS = RemoteToDoDataSource()
    private val localToDoDS = LocalToDoDataSource(context)

    override fun getToDoByUserID(id: Int, callback: IToDoDataSource.IToDoCallback) {
        loadFromRemote(id, callback)
    }

    private fun loadFromRemote(id: Int, callback: IToDoDataSource.IToDoCallback) {
        remoteToDoDS.getToDoByUserID(id, object: IToDoDataSource.IToDoCallback {
            override fun onToDoLoaded(list: List<ToDo>) {
                callback.onToDoLoaded(list)
                localToDoDS.saveToDB(list)
            }

            override fun onFailure() {
               loadFromLocal(id, callback)
            }
        })
    }

    private fun loadFromLocal(id: Int, callback: IToDoDataSource.IToDoCallback) {
        localToDoDS.getToDoByUserID(id, object: IToDoDataSource.IToDoCallback {
            override fun onToDoLoaded(list: List<ToDo>) {
                callback.onToDoLoaded(list)
            }

            override fun onFailure() {
                callback.onFailure()
            }
        })
    }
}