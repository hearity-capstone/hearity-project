package com.hearity_capstone.hearity.data.repository

import com.hearity_capstone.hearity.data.api.ApiService
import com.hearity_capstone.hearity.data.model.LoginRequest
import com.hearity_capstone.hearity.data.model.LoginResponse

class AuthRepositoryImpl(
    private val apiService: ApiService
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): LoginResponse {
        return apiService.login(request = LoginRequest(email, password))
    }
}