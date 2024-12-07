package com.hearity_capstone.hearity.data.dummy

import com.hearity_capstone.hearity.data.model.EarFrequency
import com.hearity_capstone.hearity.data.model.FrequencyResult
import com.hearity_capstone.hearity.data.model.TestResultModel
import java.time.LocalDate


val testResultDummyData = listOf(
    TestResultModel(
        id = 1,
        doctorName = "dr. Ratna Kusuma, Sp.THT-KL",
        date = LocalDate.of(2024, 11, 22),
        summary = "Your hearing is normal at low to medium frequencies. However, there is a mild hearing loss detected at high frequencies in your right ear. This might affect your ability to hear high-pitched sounds clearly. ",
        earFrequency = EarFrequency(
            left = FrequencyResult(10f, 5f, 10f, 0f),
            right = FrequencyResult(15f, 0f, 15f, 25f)
        ),
    ),

    TestResultModel(
        id = 2,
        doctorName = "dr. Ratna Kusuma, Sp.THT-KL",
        date = LocalDate.of(2024, 10, 23),
        summary = "Your test results indicate a mild hearing loss at high frequencies, particularly in your right ear. This could impact your ability to hear certain consonants and high-pitched sounds.",
        earFrequency = EarFrequency(
            left = FrequencyResult(12f, 15f, 14f, 10f),
            right = FrequencyResult(20f, 5f, 12f, 15f)
        ),
    ),
)