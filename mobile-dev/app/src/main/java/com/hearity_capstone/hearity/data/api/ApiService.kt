package com.hearity_capstone.hearity.data.api

import com.hearity_capstone.hearity.data.model.testResult.AllTestResultResponse
import com.hearity_capstone.hearity.data.model.testResult.AddTestResultRequest
import com.hearity_capstone.hearity.data.model.testResult.AddTestResultResponse
import com.hearity_capstone.hearity.data.model.authentication.LoginRequest
import com.hearity_capstone.hearity.data.model.authentication.LoginResponse
import com.hearity_capstone.hearity.data.model.authentication.SignUpRequest
import com.hearity_capstone.hearity.data.model.authentication.SignUpResponse
import com.hearity_capstone.hearity.data.model.authentication.VerifyTokenRequest
import com.hearity_capstone.hearity.data.model.authentication.VerifyTokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("/api/auth/verify-token")
    suspend fun verifyToken(@Body request: VerifyTokenRequest): VerifyTokenResponse

    @POST("api/auth/register")
    suspend fun signUp(@Body request: SignUpRequest): SignUpResponse

    @GET("api/tests/test-result")
    suspend fun getAllTestResult(): AllTestResultResponse

    @POST("api/tests/test-result")
    suspend fun addTestResult(@Body request: AddTestResultRequest): AddTestResultResponse

}