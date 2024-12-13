package com.hearity_capstone.hearity.data.repository.authentication

import com.hearity_capstone.hearity.data.model.LoginResponse
import com.hearity_capstone.hearity.data.model.VerifyTokenResponse

interface AuthRepository {
    suspend fun login(email: String, password: String): LoginResponse

    suspend fun verifyToken(token: String): VerifyTokenResponse
}