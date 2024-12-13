package com.hearity_capstone.hearity.data.api

interface TokenProvider {
    suspend fun getToken(): String?
    suspend fun saveToken(token: String)
    suspend fun clearToken()
}

