package com.example.lab_5

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class TimeNotificationService : Service() {

    private var selectedTime = "Время не выбрано"

    companion object {
        const val CHANNEL_ID = "TimeNotificationServiceChannel"
        const val ACTION_UPDATE_TIME = "com.example.lab_5.ACTION_UPDATE_TIME"
        const val EXTRA_TIME = "selectedTime"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(1, getNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_UPDATE_TIME -> {
                selectedTime = intent.getStringExtra(EXTRA_TIME) ?: "Время не выбрано"
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
                "Time Notification Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
    }

    private fun getNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Выбранное время")
            .setContentText("Время: $selectedTime")
            .setSmallIcon(android.R.drawable.ic_menu_info_details)
            .build()
    }

    private fun updateNotification() {
        val notification = getNotification()
        val manager = getSystemService(NotificationManager::class.java)
        manager?.notify(1, notification)

        // Отправка широковещательного сообщения об обновлении времени
        val intent = Intent("UPDATE_TIME")
        intent.putExtra("selectedTime", selectedTime)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
}
