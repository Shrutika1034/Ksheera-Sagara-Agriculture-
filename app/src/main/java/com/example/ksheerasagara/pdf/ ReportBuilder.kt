package com.example.ksheerasagara.pdf

import com.example.ksheerasagara.data.entity.ExpenseEntry
import com.example.ksheerasagara.data.entity.MilkEntry

data class ReportBuilder(

    val incomeEntries: List<MilkEntry>,

    val expenseEntries: List<ExpenseEntry>,

    val totalIncome: Double,

    val totalExpense: Double
)