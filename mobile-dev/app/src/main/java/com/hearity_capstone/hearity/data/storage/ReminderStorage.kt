package com.hearity_capstone.hearity.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import java.time.LocalDate

class ReminderStorage(
    private val context: Context,
) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("reminder_prefs")
        private val REMINDER_KEY = longPreferencesKey("reminder_key")
    }

    suspend fun saveReminder(date: LocalDate) {
        context.dataStore.edit { preferences ->
            preferences[REMINDER_KEY] = date.toEpochDay()
        }
    }


    suspend fun getReminderDate(): LocalDate? {
        val preferences = context.dataStore.data.first()
        return preferences[REMINDER_KEY]?.let { LocalDate.ofEpochDay(it) }
    }


}