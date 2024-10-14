package com.example.lab_5

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class Task11 : AppCompatActivity() {

    private var count = 0  // Переменная для хранения счёта
    private lateinit var tvCounter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task11)

        // Инициализация элементов интерфейса
        tvCounter = findViewById(R.id.tvCounter)
        val btnIncrease: Button = findViewById(R.id.btnIncrease)
        val btnReset: Button = findViewById(R.id.btnReset)

        // Запускаем сервис
        startCounterService()

        // Кнопка для увеличения счёта
        btnIncrease.setOnClickListener {
            // В этом случае увеличение будет обрабатываться через уведомление
            // Вызов метода увеличения в сервисе
            val intent = Intent(this, CounterService::class.java).apply {
                action = CounterService.ACTION_INCREASE
            }
            startService(intent)
        }

        // Кнопка для сброса счёта
        btnReset.setOnClickListener {
            // Вызов метода сброса в сервисе
            val intent = Intent(this, CounterService::class.java).apply {
                action = CounterService.ACTION_RESET
            }
            startService(intent)
        }

        // Регистрация приемника для обновлений счётчика
        LocalBroadcastManager.getInstance(this).registerReceiver(counterReceiver, IntentFilter("UPDATE_COUNTER"))
    }

    // Приемник для получения обновлений счётчика
    private val counterReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            count = intent?.getIntExtra("count", 0) ?: 0
            tvCounter.text = "Счёт: $count"
        }
    }

    private fun startCounterService() {
        val serviceIntent = Intent(this, CounterService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Отменить регистрацию приемника при уничтожении активности
        LocalBroadcastManager.getInstance(this).unregisterReceiver(counterReceiver)
    }
}
