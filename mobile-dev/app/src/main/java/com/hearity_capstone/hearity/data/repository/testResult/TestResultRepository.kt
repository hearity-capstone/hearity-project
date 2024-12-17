package com.hearity_capstone.hearity.data.repository.testResult

import com.hearity_capstone.hearity.data.model.testResult.AddTestResultRequest
import com.hearity_capstone.hearity.data.model.testResult.AddTestResultResponse
import com.hearity_capstone.hearity.data.model.testResult.AllTestResultResponse

interface TestResultRepository {
    suspend fun getAllTestResult(): AllTestResultResponse
    suspend fun addTestResult(request: AddTestResultRequest): AddTestResultResponse
}