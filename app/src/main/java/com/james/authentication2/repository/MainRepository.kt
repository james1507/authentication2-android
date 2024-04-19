package com.james.authentication2.repository

import com.james.authentication2.model.LoginBody
import com.james.authentication2.model.RegisterBody
import com.james.authentication2.utils.constant.apiRequestFlow
import com.james.authentication2.utils.network.APIConsumer
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiConsumer: APIConsumer) {
//    fun getUserInfo(loginBody: LoginBody) = apiRequestFlow {
//        apiConsumer.loginUser(loginBody)
//    }
}