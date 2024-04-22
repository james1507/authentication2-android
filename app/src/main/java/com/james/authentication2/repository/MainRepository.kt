package com.james.authentication2.repository

import com.james.authentication2.service.home.HomeApiService
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiConsumer: HomeApiService) {
//    fun getUserInfo(loginBody: LoginBody) = apiRequestFlow {
//        apiConsumer.loginUser(loginBody)
//    }
}