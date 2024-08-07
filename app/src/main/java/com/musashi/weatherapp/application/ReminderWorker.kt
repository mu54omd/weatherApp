package com.musashi.weatherapp.application

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class ReminderWorker(
    context: Context,
    workerParameters: WorkerParameters
): Worker(context, workerParameters) {

    private val notificationHandler = NotificationHandler(context)

    override fun doWork(): Result {

        notificationHandler.showAnotherSimpleNotification()

        return Result.success()
    }
}