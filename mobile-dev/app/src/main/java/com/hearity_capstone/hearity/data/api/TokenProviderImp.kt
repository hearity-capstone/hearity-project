package com.hearity_capstone.hearity.data.api

import com.hearity_capstone.hearity.data.storage.TokenStorage

class TokenProviderImpl(private val tokenStorage: TokenStorage) : TokenProvider {
    override suspend fun getToken(): String {
        return tokenStorage.getToken() ?: throw IllegalStateException("Token is missing")
    }

    override suspend fun saveToken(token: String) {
        tokenStorage.saveToken(token)
    }

    override suspend fun clearToken() {
        tokenStorage.clearToken()
    }
}

