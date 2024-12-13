package com.hearity_capstone.hearity.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data") val data: UserModel,
    @SerializedName("message") val message: String,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("token") val token: String
)