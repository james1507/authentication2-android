package com.james.authentication2.repository

import com.james.authentication2.service.home.HomeApiService
import com.james.authentication2.utils.constant.apiRequestFlow
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiConsumer: HomeApiService) {
    fun getUserInfo(id: String) = apiRequestFlow {
        apiConsumer.userProfile(id)
    }
}