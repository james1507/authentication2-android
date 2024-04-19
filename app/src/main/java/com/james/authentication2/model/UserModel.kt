package com.james.authentication2.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("user_name")
    val userName: String,
    val email: String,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    val gender: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("is_verified_email")
    val isVerifiedEmail: Boolean,
    @SerializedName("_id")
    val id: String,
    val token: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
)
