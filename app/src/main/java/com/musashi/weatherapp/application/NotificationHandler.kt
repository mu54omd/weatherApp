package com.musashi.weatherapp.application

import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.musashi.weatherapp.R
import kotlin.random.Random


class NotificationHandler(private val context: Context) {
    @RequiresApi(Build.VERSION_CODES.M)
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    private val notificationChannelID = "notification_channel_id"

    // SIMPLE NOTIFICATION
    @RequiresApi(Build.VERSION_CODES.N)
    fun showSimpleNotification(city: String, country: String, temperature: Double) {
        val notification = NotificationCompat.Builder(context, notificationChannelID)
            .setContentTitle("$country: $city")
            .setContentText("Current temperature is: ${temperature}°C")
            .setSmallIcon(R.drawable.clear_sky)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()  // finalizes the creation

        notificationManager.notify(Random.nextInt(), notification)
    }
}