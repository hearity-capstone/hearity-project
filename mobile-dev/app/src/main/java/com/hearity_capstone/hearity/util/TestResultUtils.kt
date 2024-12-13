package com.hearity_capstone.hearity.util

import androidx.compose.ui.graphics.Color
import com.hearity_capstone.hearity.data.model.testResult.TestResultModel
import com.hearity_capstone.hearity.ui.theme.SeverityHigh
import com.hearity_capstone.hearity.ui.theme.SeverityLow
import com.hearity_capstone.hearity.ui.theme.SeverityModerate
import com.patrykandpatrick.vico.core.entry.FloatEntry

object TestResultUtils {

    fun createLeftEarFloatEntries(testResult: TestResultModel? = null): List<FloatEntry> {
        return if (testResult == null) {
            listOf(
                FloatEntry(x = 0f, y = -0f),
                FloatEntry(x = 1f, y = -0f),
                FloatEntry(x = 2f, y = -0f),
                FloatEntry(x = 3f, y = -0f),
            )
        } else {
            return listOf(
                FloatEntry(x = 0f, y = -(testResult.leftFreq500Hz.toFloat())),
                FloatEntry(x = 1f, y = -(testResult.leftFreq1000Hz.toFloat())),
                FloatEntry(x = 2f, y = -(testResult.leftFreq2000Hz.toFloat())),
                FloatEntry(x = 3f, y = -(testResult.leftFreq4000Hz.toFloat())),
            )
        }
    }

    fun createRightEarFloatEntries(testResult: TestResultModel? = null): List<FloatEntry> {
        return if (testResult == null) {
            listOf(
                FloatEntry(x = 0f, y = -0f),
                FloatEntry(x = 1f, y = -0f),
                FloatEntry(x = 2f, y = -0f),
                FloatEntry(x = 3f, y = -0f),
            )
        } else {
            return listOf(
                FloatEntry(x = 0f, y = -(testResult.rightFreq500Hz.toFloat())),
                FloatEntry(x = 1f, y = -(testResult.rightFreq1000Hz.toFloat())),
                FloatEntry(x = 2f, y = -(testResult.rightFreq2000Hz.toFloat())),
                FloatEntry(x = 3f, y = -(testResult.rightFreq4000Hz.toFloat())),
            )
        }

    }

    fun createHearingLevelDiagnosisSummary(hearingLevel: Float): String {
        return when (hearingLevel) {
            in 0f..25f -> "Your hearing is within the normal range. You can hear sounds clearly, even soft ones, without any difficulty. This means you should not experience any hearing-related challenges in daily life."
            in 25f..40f -> "You may have mild difficulty hearing quiet sounds, especially in noisy environments. For example, you might not hear someone speaking softly from a distance or in a crowded area."
            in 40f..55f -> "With moderate hearing loss, you could struggle to hear normal conversations, especially in quieter places. You may often need people to speak louder or repeat themselves for clarity."
            in 55f..70f -> "You might find it difficult to hear speech without assistance, especially in quiet environments. It may be hard to participate in regular conversations without a hearing aid."
            in 70f..90f -> "Severe hearing loss means you will likely have difficulty understanding most speech without amplification and you may find it challenging to engage in everyday conversations."
            else -> "With profound hearing loss, you might not hear any sounds at all without a hearing aid. Communication without assistance will be very difficult and you may rely heavily on visual cues like lip-reading."
        }
    }

    object ClassifyHearingLevelDiagnosis {
        fun string(hearingLevel: Float): String {
            return when (hearingLevel) {
                in 0f..25f -> "Normal"
                in 25f..40f -> "Mild"
                in 40f..55f -> "Moderate"
                in 55f..70f -> "Moderately Severe"
                in 70f..90f -> "Severe"
                else -> "Profound"
            }
        }

        fun color(hearingLevel: Float): Color {
            return when (hearingLevel) {
                in 0f..25f -> SeverityLow
                in 25f..40f -> SeverityLow
                in 40f..55f -> SeverityModerate
                in 55f..70f -> SeverityModerate
                in 70f..90f -> SeverityHigh
                else -> SeverityHigh
            }
        }
    }

}