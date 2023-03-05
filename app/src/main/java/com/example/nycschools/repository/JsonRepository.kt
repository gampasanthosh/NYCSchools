package com.example.nycschools.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nycschools.api.JsonAPI
import com.example.nycschools.models.SchoolSatScoresList

class JsonRepository(private var jsonApi: JsonAPI) {

    // Live data objects for progressBar and error, we will observe these in Fragment/Activity
    private val showProgress: MutableLiveData<Boolean> = MutableLiveData()
    private val errorMessage: MutableLiveData<String> = MutableLiveData<String>("")
    private val jsonSatScoresLiveData = MutableLiveData<SchoolSatScoresList>()
    val satScores : LiveData<SchoolSatScoresList> get() = jsonSatScoresLiveData
    val progress : LiveData<Boolean> get() = showProgress
    val error : LiveData<String> get() = errorMessage

    suspend fun getSatScoreData(){
        try {
            showProgress.postValue(true)
            val result = jsonApi.getSatScoresList()
            if (result?.body() != null && result.code() == 200){
                jsonSatScoresLiveData.postValue(result.body())
                showProgress.postValue(false)
            }
        } catch (e: Exception){
            errorMessage.postValue("Sat score data call failed, please try again")
            showProgress.postValue(false)
        }
    }
}