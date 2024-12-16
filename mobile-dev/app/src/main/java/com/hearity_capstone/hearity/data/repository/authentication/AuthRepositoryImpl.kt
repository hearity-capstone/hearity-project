package com.hearity_capstone.hearity.data.repository.authentication

import com.hearity_capstone.hearity.data.api.ApiService
import com.hearity_capstone.hearity.data.model.authentication.LoginRequest
import com.hearity_capstone.hearity.data.model.authentication.LoginResponse
import com.hearity_capstone.hearity.data.model.authentication.SignUpRequest
import com.hearity_capstone.hearity.data.model.authentication.SignUpResponse
import com.hearity_capstone.hearity.data.model.authentication.VerifyTokenRequest
import com.hearity_capstone.hearity.data.model.authentication.VerifyTokenResponse

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

    override suspend fun signUp(request: SignUpRequest): SignUpResponse {
        return apiService.signUp(request = request)
    }
}