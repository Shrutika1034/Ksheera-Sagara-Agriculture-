//package com.example.ksheerasagara.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import com.example.ksheerasagara.data.repository.DataRepository
//
//class DashboardViewModel(
//    private val repository: DataRepository
//) : ViewModel() {
//
//    val monthlyIncome: LiveData<Double> =
//        repository.getCurrentMonthIncome()
//
//    val monthlyExpenses: LiveData<Double> =
//        repository.getCurrentMonthExpenses()
//}

package com.example.ksheerasagara.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ksheerasagara.data.repository.DataRepository

class DashboardViewModel(
    private val repository: DataRepository
) : ViewModel() {

    val monthlyIncome: LiveData<Double> =
        repository.getCurrentMonthIncome()

    val monthlyExpenses: LiveData<Double> =
        repository.getCurrentMonthExpenses()
}