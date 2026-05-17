package com.example.ksheerasagara.notifications

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class ReminderWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {

        NotificationHelper.showNotification(
            applicationContext,
            "Milk Entry Reminder",
            "Add today's milk entry"
        )

        return Result.success()
    }
}