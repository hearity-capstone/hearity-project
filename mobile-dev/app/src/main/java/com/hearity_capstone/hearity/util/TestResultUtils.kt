package com.hearity_capstone.hearity.util

import com.hearity_capstone.hearity.data.model.EarFrequency
import com.patrykandpatrick.vico.core.entry.FloatEntry

object TestResultUtils {
    fun calculateAverageLeftEarFrequency(earFrequency: EarFrequency): Float {
        val totalFrequency =
            earFrequency.left.freq500Hz + earFrequency.left.freq1000Hz + earFrequency.left.freq2000Hz + earFrequency.left.freq4000Hz
        return totalFrequency / 4
    }

    fun calculateAverageRightEarFrequency(earFrequency: EarFrequency): Float {
        val totalFrequency =
            earFrequency.right.freq500Hz + earFrequency.right.freq1000Hz + earFrequency.right.freq2000Hz + earFrequency.right.freq4000Hz
        return totalFrequency / 4

    }

    fun createLeftEarFloatEntries(earFrequency: EarFrequency): List<FloatEntry> {
        return listOf(
            FloatEntry(x = 0f, y = earFrequency.left.freq500Hz),
            FloatEntry(x = 1f, y = earFrequency.left.freq1000Hz),
            FloatEntry(x = 2f, y = earFrequency.left.freq2000Hz),
            FloatEntry(x = 3f, y = earFrequency.left.freq4000Hz),
        )
    }

    fun createRightEarFloatEntries(earFrequency: EarFrequency): List<FloatEntry> {
        return listOf(
            FloatEntry(x = 0f, y = earFrequency.right.freq500Hz),
            FloatEntry(x = 1f, y = earFrequency.right.freq1000Hz),
            FloatEntry(x = 2f, y = earFrequency.right.freq2000Hz),
            FloatEntry(x = 3f, y = earFrequency.right.freq4000Hz)
        )
    }
}