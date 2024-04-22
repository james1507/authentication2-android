package com.james.authentication2.service.auth

import com.james.authentication2.model.LoginBody
import com.james.authentication2.model.LoginResponse
import com.james.authentication2.model.RefreshTokenBody
import com.james.authentication2.model.RefreshTokenResponse
import com.james.authentication2.model.RegisterBody
import com.james.authentication2.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("user/sign-up")
    suspend fun registerUser(@Body body: RegisterBody): Response<RegisterResponse>

    @POST("user/sign-in")
    suspend fun loginUser(@Body body: LoginBody): Response<LoginResponse>

    @POST("user/refresh-token")
    suspend fun refreshToken(
        @Body body: RefreshTokenBody,
    ): Response<RefreshTokenResponse>
}