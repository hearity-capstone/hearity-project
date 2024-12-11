package com.hearity_capstone.hearity.data.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id") val id: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("address") val address: String,
    @SerializedName("city") val city: String,
)
