//// AppDatabase.kt
//// Location: app/src/main/java/com/mindmatrix/ksheera_sagara/data/database/
//
//package com.example.ksheerasagara.data.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.room.migration.Migration
//import androidx.sqlite.db.SupportSQLiteDatabase
//import com.example.ksheerasagara.data.dao.*
//import com.example.ksheerasagara.data.entity.*
//
//@Database(
//    entities = [
//        MilkEntry::class,
//        ExpenseEntry::class,
//        Cow::class,
//        CowProduction::class
//    ],
//    version = 1,
//    exportSchema = true
//)
//abstract class AppDatabase : RoomDatabase() {
//
//    abstract fun milkEntryDao(): MilkEntryDao
//    abstract fun expenseEntryDao(): ExpenseEntryDao
//    abstract fun cowDao(): CowDao
//    abstract fun cowProductionDao(): CowProductionDao
//
//    companion object {
//        @Volatile
//        private var instance: AppDatabase? = null
//        private val LOCK = Any()
//
//        fun getInstance(context: Context): AppDatabase {
//            return instance ?: synchronized(LOCK) {
//                instance ?: createDatabase(context).also { instance = it }
//            }
//        }
//
//        private fun createDatabase(context: Context): AppDatabase {
//            return Room.databaseBuilder(
//                context.applicationContext,
//                AppDatabase::class.java,
//                "ksheera_sagara_db"
//            )
//                .addCallback(databaseCallback)
//                .fallbackToDestructiveMigration()
//                .build()
//        }
//
//        private val databaseCallback = object : RoomDatabase.Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                // Initialize database with seed data if needed
//            }
//        }
//    }
//}
package com.example.ksheerasagara.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ksheerasagara.data.dao.*
import com.example.ksheerasagara.data.entity.*
import com.example.ksheerasagara.data.entity.CowExpense
import com.example.ksheerasagara.data.dao.CowExpenseDao

@Database(
    entities = [
        MilkEntry::class,
        ExpenseEntry::class,
        Cow::class,
        CowProduction::class,
        CowExpense::class

    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun milkEntryDao(): MilkEntryDao

    abstract fun cowExpenseDao(): CowExpenseDao

    abstract fun expenseEntryDao(): ExpenseEntryDao

    abstract fun cowDao(): CowDao

    abstract fun cowProductionDao(): CowProductionDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ksheera_sagara_database"
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}