package com.hearity_capstone.hearity.data.repository.testResult

import com.hearity_capstone.hearity.data.model.AllTestResultResponse

interface TestResultRepository {
    suspend fun getAllTestResult(): AllTestResultResponse
}