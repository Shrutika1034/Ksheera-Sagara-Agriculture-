package com.example.ksheerasagara.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cow_expenses")
data class CowExpense(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val cowId: Int,

    val expenseAmount: Double,

    val category: String,

    val date: Long = System.currentTimeMillis()
)