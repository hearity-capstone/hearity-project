package com.hearity_capstone.hearity.data.model

import com.google.gson.annotations.SerializedName

data class AllTestResultResponse(
    @SerializedName("data") val data: List<DataItem>,
    @SerializedName("message") val message: String,
    @SerializedName("statusCode") val statusCode: Int
)

data class Date(
    @SerializedName("date") val value: String
)

data class DataItem(
    @SerializedName("id") val id: String,
    @SerializedName("user_id") val userId: String,
    @SerializedName("date") val date: Date,
    @SerializedName("left_freq_500_hz") val leftFreq500Hz: Int,
    @SerializedName("left_freq_1000_hz") val leftFreq1000Hz: Int,
    @SerializedName("left_freq_2000_hz") val leftFreq2000Hz: Int,
    @SerializedName("left_freq_4000_hz") val leftFreq4000Hz: Int,
    @SerializedName("right_freq_500_hz") val rightFreq500Hz: Int,
    @SerializedName("right_freq_1000_hz") val rightFreq2000Hz: Int,
    @SerializedName("right_freq_2000_hz") val rightFreq1000Hz: Int,
    @SerializedName("right_freq_4000_hz") val rightFreq4000Hz: Int,
    @SerializedName("AS") val AS: Float,
    @SerializedName("AD") val AD: Float,
    @SerializedName("doctor") val doctor: String,
    @SerializedName("hospital") val hospital: String,
)
