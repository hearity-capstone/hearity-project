package com.hearity_capstone.hearity.data.repository.authentication

import com.hearity_capstone.hearity.data.model.authentication.LoginResponse
import com.hearity_capstone.hearity.data.model.authentication.SignUpRequest
import com.hearity_capstone.hearity.data.model.authentication.SignUpResponse
import com.hearity_capstone.hearity.data.model.authentication.VerifyTokenResponse

interface AuthRepository {
    suspend fun login(email: String, password: String): LoginResponse

    suspend fun verifyToken(token: String): VerifyTokenResponse

    suspend fun signUp(request: SignUpRequest): SignUpResponse
}