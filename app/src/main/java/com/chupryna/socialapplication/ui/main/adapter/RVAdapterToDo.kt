package com.chupryna.socialapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chupryna.socialapplication.R
import com.chupryna.socialapplication.data.model.ToDo
import kotlinx.android.synthetic.main.fragment_item_todo.view.*

class RVAdapterToDo(private val list: List<ToDo>) : RecyclerView.Adapter<RVAdapterToDo.ToDoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_todo, parent, false)
        return ToDoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.completed.isChecked = list[position].completed
    }

    class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title:TextView = itemView.titleToDoTv
        val completed: CheckBox = itemView.completedToDoCb
    }
}