package com.example.nycschools.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.models.SchoolSatScoresList
import com.example.nycschools.repository.JsonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: JsonRepository): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSatScoreData()
        }
    }

    val jsonDataSatScores: LiveData<SchoolSatScoresList> get()= repository.satScores

    val showProgress: LiveData<Boolean> get() = repository.progress
    val errorMessage: LiveData<String> get() = repository.error
}