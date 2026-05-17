package com.example.ksheerasagara.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ksheerasagara.data.entity.CowProduction

@Dao
interface CowProductionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(
        cowProduction: CowProduction
    )

    @Query(
        "SELECT * FROM cow_production WHERE cowId = :cowId"
    )
    fun getProductionByCow(
        cowId: Int
    ): LiveData<List<CowProduction>>
}