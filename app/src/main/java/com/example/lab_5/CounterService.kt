package com.example.lab_5

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class CounterService : Service() {

    private var count = 0

    companion object {
        const val CHANNEL_ID = "CounterServiceChannel"
        const val ACTION_INCREASE = "com.example.lab_5.ACTION_INCREASE"
        const val ACTION_RESET = "com.example.lab_5.ACTION_RESET"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(1, getNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_INCREASE -> {
                count++
                updateNotification()
            }
            ACTION_RESET -> {
                count = 0
                updateNotification()
            }
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Counter Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
    }

    private fun getNotification(): Notification {
        val increaseIntent = Intent(this, CounterService::class.java).apply {
            action = ACTION_INCREASE
        }
        val resetIntent = Intent(this, CounterService::class.java).apply {
            action = ACTION_RESET
        }

        val increasePendingIntent = PendingIntent.getService(
            this,
            0,
            increaseIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val resetPendingIntent = PendingIntent.getService(
            this,
            1,
            resetIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Счётчик")
            .setContentText("Счёт: $count")
            .setSmallIcon(android.R.drawable.ic_menu_info_details) // Системная иконка
            .addAction(android.R.drawable.ic_input_add, "Добавить 1", increasePendingIntent)
            .addAction(android.R.drawable.ic_delete, "Сбросить", resetPendingIntent)
            .build()
    }

    private fun updateNotification() {
        val notification = getNotification()
        val manager = getSystemService(NotificationManager::class.java)
        manager?.notify(1, notification)

        // Отправка широковещательного сообщения об обновлении счетчика
        val intent = Intent("UPDATE_COUNTER")
        intent.putExtra("count", count)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
}
