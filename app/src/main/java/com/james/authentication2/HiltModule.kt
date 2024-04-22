package com.james.authentication2

import com.james.authentication2.repository.AuthRepository
import com.james.authentication2.repository.HomeRepository
import com.james.authentication2.service.auth.AuthApiService
import com.james.authentication2.service.home.HomeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun provideAuthRepository(apiConsumer: AuthApiService) = AuthRepository(apiConsumer)

    @Provides
    fun provideMainRepository(apiConsumer: HomeApiService) = HomeRepository(apiConsumer)
}