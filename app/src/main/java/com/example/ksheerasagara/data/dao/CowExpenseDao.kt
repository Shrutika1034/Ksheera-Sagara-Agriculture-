package com.example.ksheerasagara.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ksheerasagara.data.entity.CowExpense

@Dao
interface CowExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: CowExpense)

    @Update
    suspend fun update(expense: CowExpense)

    @Delete
    suspend fun delete(expense: CowExpense)

    @Query(
        "SELECT * FROM cow_expenses WHERE cowId = :cowId"
    )
    fun getExpensesByCow(
        cowId: Int
    ): LiveData<List<CowExpense>>

    @Query("SELECT * FROM cow_expenses")
    fun getAllCowExpenses(): LiveData<List<CowExpense>>
}