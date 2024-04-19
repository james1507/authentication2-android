package com.james.authentication2.model

import com.google.gson.annotations.SerializedName

data class RefreshTokenBody(
    @SerializedName("refresh_token")
    val refreshToken: String,
)
