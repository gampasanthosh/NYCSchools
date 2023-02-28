package com.example.nycschools.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.models.schoolSatScoresList
import com.example.nycschools.repository.jsonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: jsonRepository): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSatScoreData()
        }
    }

    val jsonDataSatScores: LiveData<schoolSatScoresList> get()= repository.satScores

    val showProgress: LiveData<Boolean> get() = repository.progress
    val errorMessage: LiveData<String> get() = repository.error
}