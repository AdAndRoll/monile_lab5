package com.example.lab_5.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.lab_5.R
import com.example.lab_5.models.Task

class TaskAdapter(private val context: Context, private val tasks: List<Task>) : BaseAdapter() {

    override fun getCount(): Int = tasks.size

    override fun getItem(position: Int): Any = tasks[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val task = tasks[position]
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.task_list_item, parent, false)

        val dateTextView = view.findViewById<TextView>(R.id.dateTextView)
        val descriptionTextView = view.findViewById<TextView>(R.id.descriptionTextView)

        // Группируем задачи по дате
        if (position == 0 || task.date != tasks[position - 1].date) {
            dateTextView.visibility = View.VISIBLE
            dateTextView.text = task.date
        } else {
            dateTextView.visibility = View.GONE
        }

        descriptionTextView.text = task.description

        return view
    }
}
