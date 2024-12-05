package com.hearity_capstone.hearity.data.model

import java.time.LocalDate

data class TestResultModel(
    val id: Int,
    val doctorName: String,
    val date: LocalDate,
    val summary: String,
    val earFrequency: EarFrequency,
)

data class FrequencyResult(
    val freq500Hz: Float,
    val freq1000Hz: Float,
    val freq2000Hz: Float,
    val freq4000Hz: Float
)

data class EarFrequency(
    val left: FrequencyResult,
    val right: FrequencyResult
)

enum class EarSide {
    LEFT, RIGHT, BOTH
}
