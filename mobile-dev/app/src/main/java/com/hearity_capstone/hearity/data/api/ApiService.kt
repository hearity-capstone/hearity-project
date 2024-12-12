package com.hearity_capstone.hearity.data.api

import com.hearity_capstone.hearity.data.model.AllTestResultResponse
import com.hearity_capstone.hearity.data.model.LoginRequest
import com.hearity_capstone.hearity.data.model.LoginResponse
import com.hearity_capstone.hearity.data.model.VerifyTokenRequest
import com.hearity_capstone.hearity.data.model.VerifyTokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("/api/auth/verify-token")
    suspend fun verifyToken(@Body request: VerifyTokenRequest): VerifyTokenResponse

    @GET("api/tests/test-result")
    suspend fun getAllTestResult(): AllTestResultResponse
}