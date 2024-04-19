package com.james.authentication2.viewmodel

import androidx.lifecycle.MutableLiveData
import com.james.authentication2.helpers.ApiResponse
import com.james.authentication2.model.LoginBody
import com.james.authentication2.model.LoginResponse
import com.james.authentication2.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
): BaseViewModel() {

    private val _loginResponse = MutableLiveData<ApiResponse<LoginResponse>>()
    val loginResponse = _loginResponse

    fun login(loginBody: LoginBody, coroutinesErrorHandler: CoroutinesErrorHandler) = baseRequest(
        _loginResponse,
        coroutinesErrorHandler
    ) {
        authRepository.login(loginBody)
    }
}