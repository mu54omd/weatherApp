package com.musashi.weatherapp.application

import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.musashi.weatherapp.utils.Constants.NOTIFICATION_CHANNEL_ID
import com.musashi.weatherapp.utils.Constants.NOTIFICATION_ID


class NotificationHandler(private val context: Context) {
    @RequiresApi(Build.VERSION_CODES.M)
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    private val notificationChannelID = NOTIFICATION_CHANNEL_ID

    // SIMPLE NOTIFICATION
    fun showSimpleNotification(
        title: String,
        content: String,
        weatherCode: Int,
    ) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val notification = NotificationCompat.Builder(context, notificationChannelID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(weatherCode)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setColorized(true)
                .setColor(0xFF33454)
                .setAutoCancel(true)
                .setOngoing(true)
                .build()  // finalizes the creation

            notificationManager.notify(NOTIFICATION_ID, notification)
        }
    }
}