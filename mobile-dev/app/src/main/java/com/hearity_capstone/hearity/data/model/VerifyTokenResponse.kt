package com.hearity_capstone.hearity.data.model

import com.google.gson.annotations.SerializedName


data class VerifyTokenResponse(
    @SerializedName("message") val message: String,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("data") val data: UserModel
)