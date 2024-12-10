package com.hearity_capstone.hearity.data.repository

import com.hearity_capstone.hearity.data.model.LoginResponse

interface AuthRepository {
    suspend fun login(email: String, password: String): LoginResponse
}