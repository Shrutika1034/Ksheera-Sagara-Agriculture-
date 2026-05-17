package com.example.ksheerasagara.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ksheerasagara.data.entity.MilkEntry
import com.example.ksheerasagara.data.repository.DataRepository
import kotlinx.coroutines.launch

class IncomeLogViewModel(
    private val repository: DataRepository
) : ViewModel() {

    val allMilkEntries: LiveData<List<MilkEntry>> =
        repository.getAllMilkEntries()

    fun insertMilkEntry(
        entry: MilkEntry
    ) {

        viewModelScope.launch {

            repository.insertMilkEntry(entry)
        }
    }

    fun deleteMilkEntry(
        entry: MilkEntry
    ) {

        viewModelScope.launch {

            repository.deleteMilkEntry(entry)
        }
    }
}