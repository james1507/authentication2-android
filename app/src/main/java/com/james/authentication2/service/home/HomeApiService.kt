package com.james.authentication2.service.home

import com.james.authentication2.model.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiService {
    @GET("user/profile")
    suspend fun userProfile(@Query("id") id: String): Response<ProfileResponse>
}