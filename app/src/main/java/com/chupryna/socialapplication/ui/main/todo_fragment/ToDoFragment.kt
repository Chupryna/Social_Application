package com.chupryna.socialapplication.ui.main.todo_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.ToDo
import com.chupryna.socialapplication.ui.main.MainActivity
import com.chupryna.socialapplication.ui.main.adapter.RVAdapterToDo
import kotlinx.android.synthetic.main.fragment_todo.*

class ToDoFragment : Fragment(), IToDoView {

    private val presenter by lazy { ToDoPresenter(this, context!!) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onStart() {
        super.onStart()
        initView()
        presenter.loadToDo(1)
    }

    private fun initView() {
        todoRv.layoutManager = LinearLayoutManager(context)
    }

    override fun setAdapter(list: List<ToDo>) {
        todoRv.adapter = RVAdapterToDo(list)
    }

    override fun showProgress() {
        (activity as MainActivity).showProgress()
    }

    override fun hideProgress() {
        (activity as MainActivity).hideProgress()
    }

    override fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}