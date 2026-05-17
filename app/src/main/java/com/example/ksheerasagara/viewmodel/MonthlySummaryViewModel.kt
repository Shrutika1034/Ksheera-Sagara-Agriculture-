package com.example.ksheerasagara.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ksheerasagara.data.entity.MonthlySummary
import com.example.ksheerasagara.data.repository.DataRepository

class MonthlySummaryViewModel(
    private val repository: DataRepository
) : ViewModel() {

    val monthlySummary: LiveData<MonthlySummary> =
        repository.getMonthlySummary()
}