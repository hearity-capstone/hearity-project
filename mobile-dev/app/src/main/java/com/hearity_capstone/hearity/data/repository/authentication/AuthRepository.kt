package com.hearity_capstone.hearity.data.repository.authentication

import com.hearity_capstone.hearity.data.model.LoginResponse
import com.hearity_capstone.hearity.data.model.VerifyTokenResponse
import com.hearity_capstone.hearity.data.model.authentication.SignUpRequest
import com.hearity_capstone.hearity.data.model.authentication.SignUpResponse

interface AuthRepository {
    suspend fun login(email: String, password: String): LoginResponse

    suspend fun verifyToken(token: String): VerifyTokenResponse

    suspend fun signUp(request: SignUpRequest): SignUpResponse
}