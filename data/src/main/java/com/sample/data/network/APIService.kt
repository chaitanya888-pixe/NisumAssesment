package com.sample.data.network

import com.sample.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("api/")
    suspend fun getUsers(@Query("results") results: String="10"): UserResponse

}