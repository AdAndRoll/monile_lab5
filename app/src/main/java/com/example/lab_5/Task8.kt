package com.example.lab_5
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.lab_5.adapters.TaskPagerAdapter
import com.example.lab_5.models.Task
import android.content.Intent
import android.widget.Button

class Task8 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task8)

        // Список задач на неделю
        val tasks = listOf(
            Task("2024-10-12", "Сделать домашнюю работу по программированию"),
            Task("2024-10-12", "Сходить в душ"),
            Task("2024-10-13", "Проверить почту"),
            Task("2024-10-13", "Посетить лекцию по информатике"),
            Task("2024-10-14", "Купить продукты"),
            Task("2024-10-15", "Встретиться с друзьями"),
            Task("2024-10-16", "Закончить проект")
        )

        // Инициализация ViewPager2
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val adapter = TaskPagerAdapter(tasks)
        viewPager.adapter = adapter
        val NextBut= findViewById<Button>(R.id.nextBut)
        NextBut.setOnClickListener{
            val intent = Intent(this, Task9::class.java)
            startActivity(intent)
        }
    }
}
