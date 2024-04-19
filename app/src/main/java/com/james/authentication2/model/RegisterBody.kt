package com.james.authentication2.model

import com.google.gson.annotations.SerializedName

data class RegisterBody(
    @SerializedName("user_name")
    val username: String,
    val email: String,
    val password: String,
)
