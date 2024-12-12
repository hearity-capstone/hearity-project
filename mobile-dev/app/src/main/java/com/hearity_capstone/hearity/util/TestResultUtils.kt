package com.hearity_capstone.hearity.util

import com.hearity_capstone.hearity.data.model.testResult.TestResultModel
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
}