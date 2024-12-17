package com.hearity_capstone.hearity.data.repository.testResult

import com.hearity_capstone.hearity.data.api.ApiService
import com.hearity_capstone.hearity.data.model.testResult.AddTestResultRequest
import com.hearity_capstone.hearity.data.model.testResult.AddTestResultResponse
import com.hearity_capstone.hearity.data.model.testResult.AllTestResultResponse

class TestResultRepositoryImpl(
    private val apiService: ApiService
) : TestResultRepository {
    override suspend fun getAllTestResult(): AllTestResultResponse {
        return apiService.getAllTestResult()
    }

    override suspend fun addTestResult(request: AddTestResultRequest): AddTestResultResponse {
        return apiService.addTestResult(request)
    }
}