package com.james.authentication2.model

import com.james.authentication2.model.UserModel

data class LoginResponse(val results: UserModel, val msg: String)
