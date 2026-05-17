package com.example.ksheerasagara.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ksheerasagara.data.entity.ExpenseEntry
import com.example.ksheerasagara.data.repository.DataRepository
import kotlinx.coroutines.launch

class ExpenseLogViewModel(
    private val repository: DataRepository
) : ViewModel() {

    val allExpenses: LiveData<List<ExpenseEntry>> =
        repository.getAllExpenseEntries()

    fun insertExpense(
        entry: ExpenseEntry
    ) {

        viewModelScope.launch {

            repository.insertExpenseEntry(entry)
        }
    }

    fun deleteExpense(
        entry: ExpenseEntry
    ) {

        viewModelScope.launch {

            repository.deleteExpenseEntry(entry)
        }
    }
}