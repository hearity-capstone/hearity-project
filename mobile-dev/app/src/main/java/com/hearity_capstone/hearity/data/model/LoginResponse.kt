package com.hearity_capstone.hearity.data.model

data class LoginResponse(
    val data: UserModel,
    val message: String,
    val statusCode: Int,
    val token: String
)

