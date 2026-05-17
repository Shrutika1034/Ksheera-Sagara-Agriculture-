package com.example.ksheerasagara.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "cow_production",

    foreignKeys = [

        ForeignKey(
            entity = Cow::class,
            parentColumns = ["id"],
            childColumns = ["cowId"],
            onDelete = ForeignKey.CASCADE
        ),

        ForeignKey(
            entity = MilkEntry::class,
            parentColumns = ["id"],
            childColumns = ["milkEntryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],

    indices = [
        Index(value = ["cowId"]),
        Index(value = ["milkEntryId"])
    ]
)

data class CowProduction(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val cowId: Int,

    val milkEntryId: Int,

    val totalMilk: Double,

    val date: Long = System.currentTimeMillis()
)