package com.example.ksheerasagara.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ksheerasagara.data.repository.DataRepository

class ViewModelFactory(
    private val repository: DataRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {

            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
                DashboardViewModel(repository) as T
            }

            modelClass.isAssignableFrom(IncomeLogViewModel::class.java) -> {
                IncomeLogViewModel(repository) as T
            }

            modelClass.isAssignableFrom(ExpenseLogViewModel::class.java) -> {
                ExpenseLogViewModel(repository) as T
            }

            else -> {
                throw IllegalArgumentException("Unknown ViewModel")
            }
        }
    }
}