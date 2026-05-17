package com.example.ksheerasagara.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent?
    ) {

        NotificationHelper.showNotification(
            context,
            "Ksheera Reminder",
            "Don't forget today's records"
        )
    }
}