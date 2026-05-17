package com.example.ksheerasagara.data.entity

data class MonthlySummary(
    val month: String,
    val year: Int,
    val totalIncome: Double = 0.0,
    val totalExpenses: Double = 0.0,
    val netProfit: Double = 0.0,
    val profitMargin: Double = 0.0,
    val expensesByCategory: Map<String, Double> = emptyMap(),
    val cowwiseProfitability: Map<String, Double> = emptyMap(),
    val daysTracked: Int = 0
)