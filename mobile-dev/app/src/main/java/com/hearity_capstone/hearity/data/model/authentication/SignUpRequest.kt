package com.hearity_capstone.hearity.data.model.authentication

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("password") val password: String,
    @SerializedName("address") val address: String,
    @SerializedName("city") val city: String,
    @SerializedName("gender") val gender: String,
)