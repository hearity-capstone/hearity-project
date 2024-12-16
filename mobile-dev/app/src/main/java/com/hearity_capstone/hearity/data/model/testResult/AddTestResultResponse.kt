package com.hearity_capstone.hearity.data.model.testResult

import com.google.gson.annotations.SerializedName

class AddTestResultResponse(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String,

    )