package com.hearity_capstone.hearity.data.repository

import com.hearity_capstone.hearity.data.api.ApiConfig
import com.hearity_capstone.hearity.data.model.LoginRequest
import com.hearity_capstone.hearity.data.model.LoginResponse

class AuthRepository {
    private val apiService = ApiConfig.getApiService()

    suspend fun login(username: String, password: String): LoginResponse {
        val request = LoginRequest(username, password)
        return apiService.login(request)
    }
}