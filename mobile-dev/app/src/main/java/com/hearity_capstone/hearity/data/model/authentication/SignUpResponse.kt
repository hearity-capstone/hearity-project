package com.hearity_capstone.hearity.data.model.authentication

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("message") val message: String,
    @SerializedName("statusCode") val statusCode: Int,
)