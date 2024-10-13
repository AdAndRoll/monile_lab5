package com.example.lab_5

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Task11 : AppCompatActivity() {

    private var count = 0  // Переменная для хранения счёта

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task11)

        // Инициализация элементов интерфейса
        val tvCounter: TextView = findViewById(R.id.tvCounter)
        val btnIncrease: Button = findViewById(R.id.btnIncrease)
        val btnReset: Button = findViewById(R.id.btnReset)

        // Кнопка для увеличения счёта
        btnIncrease.setOnClickListener {
            count++
            tvCounter.text = "Счёт: $count"
        }

        // Кнопка для сброса счёта
        btnReset.setOnClickListener {
            count = 0
            tvCounter.text = "Счёт: $count"
        }
    }
}
