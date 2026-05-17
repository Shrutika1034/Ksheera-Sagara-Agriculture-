package com.example.ksheerasagara.analytics

object CowAnalyticsManager {

    fun calculateProfit(
        income: Float,
        expense: Float
    ): Float {

        return income - expense
    }

    fun profitabilityStatus(
        profit: Float
    ): String {

        return if (profit >= 0f) {
            "PROFITABLE"
        } else {
            "LOSS"
        }
    }
}