package com.example.coronavirus

import retrofit2.Response
import retrofit2.http.GET

interface CoronaApi {

    @GET("bd")
    suspend fun getCorona(): Response<CoronaDataModel>
}