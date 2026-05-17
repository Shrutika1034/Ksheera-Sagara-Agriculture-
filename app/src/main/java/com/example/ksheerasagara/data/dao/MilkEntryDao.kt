package com.example.ksheerasagara.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ksheerasagara.data.entity.MilkEntry

@Dao
interface MilkEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(milkEntry: MilkEntry)

    @Update
    suspend fun update(milkEntry: MilkEntry)

    @Delete
    suspend fun delete(milkEntry: MilkEntry)

    @Query("SELECT * FROM milk_entries WHERE id = :id")
    suspend fun getById(id: Int): MilkEntry?

    @Query("SELECT * FROM milk_entries ORDER BY date DESC")
    fun getAllMilkEntries(): LiveData<List<MilkEntry>>

    @Query("SELECT * FROM milk_entries WHERE cowId = :cowId ORDER BY date DESC")
    fun getMilkEntriesByCow(cowId: Int): LiveData<List<MilkEntry>>

    @Query("SELECT SUM(totalPayment) FROM milk_entries")
    fun getTotalIncome(): LiveData<Double>

    @Query("SELECT SUM(totalPayment) FROM milk_entries WHERE date BETWEEN :startDate AND :endDate")
    fun getTotalIncomeInRange(startDate: Long, endDate: Long): LiveData<Double>
}