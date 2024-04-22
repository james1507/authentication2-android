package com.james.authentication2.repository

import com.james.authentication2.service.splash.SplashApiService
import javax.inject.Inject

class SplashRepository @Inject constructor(private val apiConsumer: SplashApiService) {
}