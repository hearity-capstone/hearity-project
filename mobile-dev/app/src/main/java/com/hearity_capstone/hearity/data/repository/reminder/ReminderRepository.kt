package com.hearity_capstone.hearity.data.repository.reminder

import java.time.LocalDate

interface ReminderRepository {
    suspend fun saveReminder(date: LocalDate)
    suspend fun getReminderDate(): LocalDate?
}
