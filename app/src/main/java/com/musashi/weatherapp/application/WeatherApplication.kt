package com.musashi.weatherapp.application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.musashi.weatherapp.utils.Constants.NOTIFICATION_CHANNEL_ID
import com.musashi.weatherapp.utils.Constants.NOTIFICATION_NAME
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }

    }

}