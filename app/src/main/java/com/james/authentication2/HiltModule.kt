package com.james.authentication2

import com.james.authentication2.repository.AuthRepository
import com.james.authentication2.repository.MainRepository
import com.james.authentication2.utils.network.APIConsumer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun provideAuthRepository(apiConsumer: APIConsumer) = AuthRepository(apiConsumer)

    @Provides
    fun provideMainRepository(apiConsumer: APIConsumer) = MainRepository(apiConsumer)
}