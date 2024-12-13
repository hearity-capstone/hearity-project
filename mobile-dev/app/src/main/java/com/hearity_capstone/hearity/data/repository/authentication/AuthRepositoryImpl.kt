package com.hearity_capstone.hearity.data.repository.authentication

import com.hearity_capstone.hearity.data.api.ApiService
import com.hearity_capstone.hearity.data.model.LoginRequest
import com.hearity_capstone.hearity.data.model.LoginResponse
import com.hearity_capstone.hearity.data.model.VerifyTokenRequest
import com.hearity_capstone.hearity.data.model.VerifyTokenResponse

class AuthRepositoryImpl(
    private val apiService: ApiService
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): LoginResponse {
        return apiService.login(request = LoginRequest(email, password))
    }

    override suspend fun verifyToken(
        token: String
    ): VerifyTokenResponse {
        return apiService.verifyToken(request = VerifyTokenRequest(token))
    }
}