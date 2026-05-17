//// DashboardViewModel.kt
//// Location: app/src/main/java/com/example/ksheerasagara/viewmodel/
//
//package com.example.ksheerasagara.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.liveData
//import androidx.lifecycle.viewModelScope
//import com.example.ksheerasagara.data.entity.*
//import com.example.ksheerasagara.data.repository.DataRepository
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import java.util.*
//
//class DashboardViewModel(private val repository: DataRepository) : ViewModel() {
//
//    private val calendar = Calendar.getInstance()
//
//    // Current Month's data
//    val monthlyIncome: LiveData<Double> by lazy {
//        fetchMonthlyIncome()
//    }
//
//    val monthlyExpenses: LiveData<Double> by lazy {
//        fetchMonthlyExpenses()
//    }
//
//    val monthlyProfit: LiveData<Double> = liveData {
//        val income = repository.getTotalIncomeInRange(
//            getMonthStart(),
//            getMonthEnd()
//        )
//        val expenses = repository.getTotalExpensesInRange(
//            getMonthStart(),
//            getMonthEnd()
//        )
//
//        emitSource(liveData {
//            val i = income.value ?: 0.0
//            val e = expenses.value ?: 0.0
//            emit(i - e)
//        })
//    }
//
//    val financialHealth: LiveData<FinancialHealth> by lazy {
//        repository.getFinancialHealth(getMonthStart(), getMonthEnd())
//    }
//
//    val daysTracked: LiveData<Double> = liveData {
//        emitSource(repository.getTotalIncomeInRange(getMonthStart(), getMonthEnd()))
//    }
//
//    val activeCowCount: LiveData<Int> by lazy {
//        repository.getActiveCowCount()
//    }
//
//    private fun fetchMonthlyIncome(): LiveData<Double> {
//        return repository.getTotalIncomeInRange(getMonthStart(), getMonthEnd())
//    }
//
//    private fun fetchMonthlyExpenses(): LiveData<Double> {
//        return repository.getTotalExpensesInRange(getMonthStart(), getMonthEnd())
//    }
//
//    private fun getMonthStart(): Long {
//        calendar.set(Calendar.DAY_OF_MONTH, 1)
//        calendar.set(Calendar.HOUR_OF_DAY, 0)
//        calendar.set(Calendar.MINUTE, 0)
//        calendar.set(Calendar.SECOND, 0)
//        return calendar.timeInMillis
//    }
//
//    private fun getMonthEnd(): Long {
//        calendar.add(Calendar.MONTH, 1)
//        calendar.add(Calendar.DAY_OF_MONTH, -1)
//        calendar.set(Calendar.HOUR_OF_DAY, 23)
//        calendar.set(Calendar.MINUTE, 59)
//        calendar.set(Calendar.SECOND, 59)
//        return calendar.timeInMillis
//    }
//}
//
//// IncomeLogViewModel.kt
//class IncomeLogViewModel(private val repository: DataRepository) : ViewModel() {
//
//    val allMilkEntries: LiveData<List<MilkEntry>> = repository.getAllMilkEntries()
//
//    fun addMilkEntry(cowId: Int, liters: Double, ratePerLiter: Double, notes: String = "") {
//        val milkEntry = MilkEntry(
//            cowId = cowId,
//            liters = liters,
//            ratePerLiter = ratePerLiter,
//            totalPayment = liters * ratePerLiter,
//            notes = notes
//        )
//        viewModelScope.launch {
//            repository.insertMilkEntry(milkEntry)
//        }
//    }
//
//    fun updateMilkEntry(milkEntry: MilkEntry) {
//        viewModelScope.launch {
//            repository.updateMilkEntry(milkEntry)
//        }
//    }
//
//    fun deleteMilkEntry(milkEntry: MilkEntry) {
//        viewModelScope.launch {
//            repository.deleteMilkEntry(milkEntry)
//        }
//    }
//}
//
//// ExpenseLogViewModel.kt
//class ExpenseLogViewModel(private val repository: DataRepository) : ViewModel() {
//
//    val allExpenses: LiveData<List<ExpenseEntry>> = repository.getAllExpenseEntries()
//
//    val expenseCategories = listOf("Feed", "Medicine", "Labor", "Other")
//
//    fun addExpense(category: String, amount: Double, description: String = "") {
//        val expense = ExpenseEntry(
//            category = category,
//            amount = amount,
//            description = description
//        )
//        viewModelScope.launch {
//            repository.insertExpenseEntry(expense)
//        }
//    }
//
//    fun updateExpense(expense: ExpenseEntry) {
//        viewModelScope.launch {
//            repository.updateExpenseEntry(expense)
//        }
//    }
//
//    fun deleteExpense(expense: ExpenseEntry) {
//        viewModelScope.launch {
//            repository.deleteExpenseEntry(expense)
//        }
//    }
//}
//
//// AnalyticsViewModel.kt
//class AnalyticsViewModel(private val repository: DataRepository) : ViewModel() {
//
//    val monthlyExpensesByCategory: LiveData<Map<String, Double>> = liveData {
//        val categories = listOf("Feed", "Medicine", "Labor", "Other")
//        val result = mutableMapOf<String, Double>()
//
//        for (category in categories) {
//            val total = repository.getTotalExpensesByCategory(
//                category,
//                getMonthStart(),
//                getMonthEnd()
//            ).value ?: 0.0
//            result[category] = total
//        }
//        emit(result)
//    }
//
//    private fun getMonthStart(): Long {
//        val calendar = Calendar.getInstance()
//        calendar.set(Calendar.DAY_OF_MONTH, 1)
//        calendar.set(Calendar.HOUR_OF_DAY, 0)
//        calendar.set(Calendar.MINUTE, 0)
//        calendar.set(Calendar.SECOND, 0)
//        return calendar.timeInMillis
//    }
//
//    private fun getMonthEnd(): Long {
//        val calendar = Calendar.getInstance()
//        calendar.add(Calendar.MONTH, 1)
//        calendar.add(Calendar.DAY_OF_MONTH, -1)
//        calendar.set(Calendar.HOUR_OF_DAY, 23)
//        calendar.set(Calendar.MINUTE, 59)
//        calendar.set(Calendar.SECOND, 59)
//        return calendar.timeInMillis
//    }
//}
//
//// CowViewModel.kt
//class CowViewModel(private val repository: DataRepository) : ViewModel() {
//
//    val allActiveCows: LiveData<List<Cow>> = repository.getAllActiveCows()
//    val allCows: LiveData<List<Cow>> = repository.getAllCows()
//
//    fun addCow(name: String, breed: String = "", age: Int = 0, purchaseCost: Double = 0.0) {
//        val cow = Cow(
//            name = name,
//            breed = breed,
//            age = age,
//            purchaseCost = purchaseCost
//        )
//        viewModelScope.launch {
//            repository.insertCow(cow)
//        }
//    }
//
//    fun updateCow(cow: Cow) {
//        viewModelScope.launch {
//            repository.updateCow(cow)
//        }
//    }
//
//    fun deactivateCow(cow: Cow) {
//        val inactiveCow = cow.copy(isActive = false)
//        viewModelScope.launch {
//            repository.updateCow(inactiveCow)
//        }
//    }
//
//    fun deleteCow(cow: Cow) {
//        viewModelScope.launch {
//            repository.deleteCow(cow)
//        }
//    }
//}
//
//// MonthlySummaryViewModel.kt
//class MonthlySummaryViewModel(private val repository: DataRepository) : ViewModel() {
//
//    val monthlySummary: LiveData<MonthlySummary> by lazy {
//        repository.getMonthlySummary(getMonthStart(), getMonthEnd())
//    }
//
//    private fun getMonthStart(): Long {
//        val calendar = Calendar.getInstance()
//        calendar.set(Calendar.DAY_OF_MONTH, 1)
//        calendar.set(Calendar.HOUR_OF_DAY, 0)
//        calendar.set(Calendar.MINUTE, 0)
//        calendar.set(Calendar.SECOND, 0)
//        return calendar.timeInMillis
//    }
//
//    private fun getMonthEnd(): Long {
//        val calendar = Calendar.getInstance()
//        calendar.add(Calendar.MONTH, 1)
//        calendar.add(Calendar.DAY_OF_MONTH, -1)
//        calendar.set(Calendar.HOUR_OF_DAY, 23)
//        calendar.set(Calendar.MINUTE, 59)
//        calendar.set(Calendar.SECOND, 59)
//        return calendar.timeInMillis
//    }
//}
