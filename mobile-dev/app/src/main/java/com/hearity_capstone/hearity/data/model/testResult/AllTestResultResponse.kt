package com.hearity_capstone.hearity.data.model.testResult

import com.google.gson.annotations.SerializedName

data class AllTestResultResponse(
    @SerializedName("data") val data: List<TestResultModel>,
    @SerializedName("message") val message: String,
    @SerializedName("statusCode") val statusCode: Int
)

