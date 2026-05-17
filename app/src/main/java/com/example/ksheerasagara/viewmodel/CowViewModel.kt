package com.example.ksheerasagara.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ksheerasagara.data.entity.Cow
import com.example.ksheerasagara.data.repository.DataRepository
import kotlinx.coroutines.launch

class CowViewModel(
    private val repository: DataRepository
) : ViewModel() {

    val allCows: LiveData<List<Cow>> =
        repository.getAllCows()

    fun insertCow(
        cow: Cow
    ) {

        viewModelScope.launch {

            repository.insertCow(cow)
        }
    }

    fun deleteCow(
        cow: Cow
    ) {

        viewModelScope.launch {

            repository.deleteCow(cow)
        }
    }
}