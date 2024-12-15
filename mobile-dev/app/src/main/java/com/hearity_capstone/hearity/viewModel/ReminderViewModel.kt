package com.hearity_capstone.hearity.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hearity_capstone.hearity.data.repository.reminder.ReminderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class ReminderViewModel(
    private val reminderRepository: ReminderRepository,
) : ViewModel() {
    private val _reminderDate = MutableStateFlow<LocalDate?>(null)

    val reminderDate: StateFlow<LocalDate?> = _reminderDate

    init {
        viewModelScope.launch {
            getReminderDate()
        }
    }

    fun saveReminder(date: LocalDate) {
        viewModelScope.launch {
            reminderRepository.saveReminder(date)
            Log.d("ReminderViewModel", "Reminder saved: $date")
            _reminderDate.value = date
        }
    }

    fun getReminderDate() {
        viewModelScope.launch {
            _reminderDate.value = reminderRepository.getReminderDate()
            Log.d("ReminderViewModel", "Getting reminder date, result: ${_reminderDate.value}")
        }
    }
}