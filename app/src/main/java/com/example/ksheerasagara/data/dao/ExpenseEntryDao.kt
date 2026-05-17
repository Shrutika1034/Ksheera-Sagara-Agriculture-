package com.example.ksheerasagara.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ksheerasagara.data.entity.ExpenseEntry

@Dao
interface ExpenseEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expenseEntry: ExpenseEntry)

    @Update
    suspend fun update(expenseEntry: ExpenseEntry)

    @Delete
    suspend fun delete(expenseEntry: ExpenseEntry)

    @Query("SELECT * FROM expense_entries ORDER BY date DESC")
    fun getAllExpenseEntries(): LiveData<List<ExpenseEntry>>

    @Query("SELECT SUM(amount) FROM expense_entries")
    fun getTotalExpenses(): LiveData<Double>

    @Query("SELECT SUM(amount) FROM expense_entries WHERE date BETWEEN :startDate AND :endDate")
    fun getTotalExpensesInRange(startDate: Long, endDate: Long): LiveData<Double>
}