package com.example.lab_5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_5.models.Task
import com.example.lab_5.adapters.TaskAdapter

class Task6 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task6)

        // Данные: список задач на неделю
        val tasks = listOf(
            Task("2024-10-12", "Сделать домашнюю работу по программированию"),
            Task("2024-10-12", "Сходить в спортзал"),
            Task("2024-10-13", "Проверить почту"),
            Task("2024-10-13", "Посетить лекцию по информатике"),
            Task("2024-10-14", "Купить продукты"),
            Task("2024-10-15", "Встретиться с друзьями"),
            Task("2024-10-16", "Закончить проект")
        )

        // Инициализация ListView
        val taskListView: ListView = findViewById(R.id.taskListView)
        val adapter = TaskAdapter(this, tasks)
        taskListView.adapter = adapter
        val NextBut= findViewById<Button>(R.id.nextBut)
        NextBut.setOnClickListener{
            val intent = Intent(this, Task7::class.java)
            startActivity(intent)
        }
    }
}
