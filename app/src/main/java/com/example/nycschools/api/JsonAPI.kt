package com.example.nycschools.api

import com.example.nycschools.models.schoolSatScoresList
import retrofit2.Response
import retrofit2.http.GET

interface  JsonAPI {

    @GET("/resource/f9bf-2cp4")
    suspend fun getSatScoresList(): Response<schoolSatScoresList>
}