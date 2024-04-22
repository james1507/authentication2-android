package com.james.authentication2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.james.authentication2.utils.network.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authManager: AuthManager,
): ViewModel() {

    val token = MutableLiveData<String?>()
    val userId = MutableLiveData<String?>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            authManager.getToken().collect {
                withContext(Dispatchers.Main) {
                    token.value = it
                }
            }

        }
        viewModelScope.launch(Dispatchers.IO) {
            authManager.getUserId().collect {
                withContext(Dispatchers.Main) {
                    userId.value = it
                }
            }
        }
    }

    fun saveAuth(token: String, userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authManager.saveToken(token)
            authManager.saveUserId(userId)
        }
    }

    fun deleteAuth() {
        viewModelScope.launch(Dispatchers.IO) {
            authManager.deleteToken()
            authManager.deleteUserId()
        }
    }
}