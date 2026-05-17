package com.example.ksheerasagara.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ksheerasagara.data.entity.Cow

@Dao
interface CowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cow: Cow)

    @Update
    suspend fun update(cow: Cow)

    @Delete
    suspend fun delete(cow: Cow)

    @Query("SELECT * FROM cows ORDER BY name ASC")
    fun getAllCows(): LiveData<List<Cow>>
}