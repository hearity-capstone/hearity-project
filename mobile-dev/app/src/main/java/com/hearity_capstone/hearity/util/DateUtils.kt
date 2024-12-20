package com.hearity_capstone.hearity.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit


object DateUtils {

    fun formatDateToString(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return date.format(formatter)
    }

    fun parseToLocalDate(dateString: String, format: String = "yyyy/MM/dd"): LocalDate? {
        return try {
            val formatter = DateTimeFormatter.ofPattern(format)
            LocalDate.parse(dateString, formatter)
        } catch (e: DateTimeParseException) {
            null
        }
    }

    fun formatLocalDate(date: LocalDate, format: String = "dd MMMM yyyy"): String {
        val formatter = DateTimeFormatter.ofPattern(format)
        return date.format(formatter)
    }

    fun formatToDDMMYYYY(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return date.format(formatter)
    }

    fun calculateDaysBetween(today: LocalDate, futureDate: LocalDate): Long {
        return ChronoUnit.DAYS.between(today, futureDate)
    }
}