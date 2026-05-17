package com.example.ksheerasagara.notifications

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

object AlarmScheduler {

    fun scheduleReminder(
        context: Context
    ) {

        val request =
            PeriodicWorkRequestBuilder<ReminderWorker>(
                24,
                TimeUnit.HOURS
            ).build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "daily_reminder",
                ExistingPeriodicWorkPolicy.UPDATE,
                request
            )
    }
}