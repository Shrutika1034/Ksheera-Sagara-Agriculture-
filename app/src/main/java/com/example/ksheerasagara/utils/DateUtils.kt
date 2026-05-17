package com.example.ksheerasagara.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getMonthStart(): Long {

        val calendar = Calendar.getInstance()

        calendar.set(Calendar.DAY_OF_MONTH, 1)

        return calendar.timeInMillis
    }

    fun formatDate(millis: Long): String {

        val formatter = SimpleDateFormat(
            "dd MMM yyyy",
            Locale.getDefault()
        )

        return formatter.format(Date(millis))
    }

    fun formatCurrency(amount: Double): String {

        return "₹${String.format("%.2f", amount)}"
    }
}