package com.hearity_capstone.hearity.data.model.authentication

import com.google.gson.annotations.SerializedName
import com.hearity_capstone.hearity.data.model.UserModel


data class VerifyTokenResponse(
    @SerializedName("message") val message: String,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("data") val data: UserModel
)