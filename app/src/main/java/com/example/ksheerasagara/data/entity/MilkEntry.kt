package com.example.ksheerasagara.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "milk_entries")
data class MilkEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Long = System.currentTimeMillis(),
    val cowId: Int,
    val liters: Double,
    val ratePerLiter: Double,
    val totalPayment: Double = liters * ratePerLiter,
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "expense_entries")
data class ExpenseEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Long = System.currentTimeMillis(),
    val category: String,
    val amount: Double,
    val description: String = "",
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "cows")
data class Cow(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val breed: String = "",
    val age: Int = 0,
    val purchaseCost: Double = 0.0,
    val purchaseDate: Long = System.currentTimeMillis(),
    val notes: String = "",
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)

data class FinancialHealth(
    val status: String,
    val color: String,
    val profitAmount: Double,
    val profitMargin: Double
)