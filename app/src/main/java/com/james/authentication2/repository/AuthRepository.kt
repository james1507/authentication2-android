package com.james.authentication2.repository

import com.james.authentication2.model.LoginBody
import com.james.authentication2.model.RegisterBody
import com.james.authentication2.service.auth.AuthApiService
import com.james.authentication2.utils.constant.apiRequestFlow
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiConsumer: AuthApiService) {
    fun login(loginBody: LoginBody) = apiRequestFlow {
        apiConsumer.loginUser(loginBody)
    }

    fun register(registerBody: RegisterBody) = apiRequestFlow {
        apiConsumer.registerUser(registerBody)
    }
}