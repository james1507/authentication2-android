package com.james.authentication2.viewmodel

import androidx.lifecycle.MutableLiveData
import com.james.authentication2.helpers.ApiResponse
import com.james.authentication2.model.ProfileResponse
import com.james.authentication2.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
): BaseViewModel() {

    private val _profileResponse = MutableLiveData<ApiResponse<ProfileResponse>>()
    val profileResponse = _profileResponse

    fun getProfile(userId: String, coroutinesErrorHandler: CoroutinesErrorHandler) = baseRequest(
        _profileResponse,
        coroutinesErrorHandler
    ) {
        homeRepository.getUserInfo(userId)
    }
}