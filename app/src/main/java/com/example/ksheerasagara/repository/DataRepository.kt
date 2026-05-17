package com.example.ksheerasagara.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.ksheerasagara.data.dao.CowDao
import com.example.ksheerasagara.data.dao.CowProductionDao
import com.example.ksheerasagara.data.dao.ExpenseEntryDao
import com.example.ksheerasagara.data.dao.MilkEntryDao
import com.example.ksheerasagara.data.entity.Cow
import com.example.ksheerasagara.data.entity.ExpenseEntry
import com.example.ksheerasagara.data.entity.FinancialHealth
import com.example.ksheerasagara.data.entity.MilkEntry
import com.example.ksheerasagara.data.entity.MonthlySummary
import kotlinx.coroutines.Dispatchers
import com.example.ksheerasagara.data.dao.CowExpenseDao
import com.example.ksheerasagara.data.entity.CowExpense

class DataRepository(
    private val milkEntryDao: MilkEntryDao,
    private val expenseEntryDao: ExpenseEntryDao,
    private val cowDao: CowDao,
    private val cowProductionDao: CowProductionDao,
    private val cowExpenseDao: CowExpenseDao
) {

    // ----------------------------
    // Milk Entries
    // ----------------------------

    fun getAllMilkEntries(): LiveData<List<MilkEntry>> {
        return milkEntryDao.getAllMilkEntries()
    }

    suspend fun insertMilkEntry(entry: MilkEntry) {
        milkEntryDao.insert(entry)
    }

    suspend fun updateMilkEntry(entry: MilkEntry) {
        milkEntryDao.update(entry)
    }

    suspend fun deleteMilkEntry(entry: MilkEntry) {
        milkEntryDao.delete(entry)
    }

    // ----------------------------
    // Expense Entries
    // ----------------------------

    fun getAllExpenseEntries(): LiveData<List<ExpenseEntry>> {
        return expenseEntryDao.getAllExpenseEntries()
    }

    suspend fun insertExpenseEntry(entry: ExpenseEntry) {
        expenseEntryDao.insert(entry)
    }

    suspend fun updateExpenseEntry(entry: ExpenseEntry) {
        expenseEntryDao.update(entry)
    }

    suspend fun deleteExpenseEntry(entry: ExpenseEntry) {
        expenseEntryDao.delete(entry)
    }

    // ----------------------------
    // Cows
    // ----------------------------

    fun getAllCows(): LiveData<List<Cow>> {
        return cowDao.getAllCows()
    }

    suspend fun insertCow(cow: Cow) {
        cowDao.insert(cow)
    }

    suspend fun updateCow(cow: Cow) {
        cowDao.update(cow)
    }

    suspend fun deleteCow(cow: Cow) {
        cowDao.delete(cow)
    }

    fun getAllCowExpenses() =
        cowExpenseDao.getAllCowExpenses()

    suspend fun insertCowExpense(
        expense: CowExpense
    ) {
        cowExpenseDao.insert(expense)
    }

    // ----------------------------
    // Dashboard Summary
    // ----------------------------

    fun getMonthlySummary(): LiveData<MonthlySummary> = liveData(Dispatchers.IO) {

        val summary = MonthlySummary(
            month = "May",
            year = 2026,
            totalIncome = 0.0,
            totalExpenses = 0.0,
            netProfit = 0.0,
            profitMargin = 0.0,
            daysTracked = 0
        )

        emit(summary)
    }

    // ----------------------------
    // Financial Health
    // ----------------------------

    fun getFinancialHealth(): LiveData<FinancialHealth> = liveData(Dispatchers.IO) {

        val health = FinancialHealth(
            status = "PROFITABLE",
            color = "GREEN",
            profitAmount = 0.0,
            profitMargin = 0.0
        )

        emit(health)
    }

    // ----------------------------
// Dashboard Totals
// ----------------------------

    fun getCurrentMonthIncome(): LiveData<Double> {

        val startDate = 0L
        val endDate = System.currentTimeMillis()

        return milkEntryDao.getTotalIncomeInRange(
            startDate,
            endDate
        )
    }

    fun getCurrentMonthExpenses(): LiveData<Double> {

        val startDate = 0L
        val endDate = System.currentTimeMillis()

        return expenseEntryDao.getTotalExpensesInRange(
            startDate,
            endDate
        )
    }

}

