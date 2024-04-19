package com.james.authentication2.utils.network

import com.james.authentication2.model.LoginBody
import com.james.authentication2.model.LoginResponse
import com.james.authentication2.model.RefreshTokenBody
import com.james.authentication2.model.RefreshTokenResponse
import com.james.authentication2.model.RegisterBody
import com.james.authentication2.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIConsumer {
    @POST("user/sign-up")
    suspend fun registerUser(@Body body: RegisterBody): Response<RegisterResponse>

    @POST("user/sign-in")
    suspend fun loginUser(@Body body: LoginBody): Response<LoginResponse>

    @POST("user/refresh-token")
    suspend fun refreshToken(
        @Body body: RefreshTokenBody,
    ): Response<RefreshTokenResponse>

    @GET("user/profile")
    suspend fun userProfile(@Query("id") id: Int): Response<LoginResponse>
}