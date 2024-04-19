package com.james.authentication2.model

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse(
    val results: RefreshTokenResult,
    val msg: String,
)

data class RefreshTokenResult(
    val token: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
)
