package com.hearity_capstone.hearity.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hearity_capstone.hearity.data.model.AllTestResultResponse
import com.hearity_capstone.hearity.data.repository.testResult.TestResultRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TestResultViewModel(
    private val repository: TestResultRepository
) : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false)

    //    private val _latestTestResult = MutableStateFlow<TestResult?>(null)
//    private val _selectedTestResult = MutableStateFlow<TestResult?>(null)
    private val _allTestResult = MutableStateFlow<AllTestResultResponse?>(null)
    private val _errorState = MutableStateFlow<String?>(null)

    val isLoading: StateFlow<Boolean> = _isLoading
    val allTestResult: StateFlow<AllTestResultResponse?> = _allTestResult
    val errorState: StateFlow<String?> = _errorState

    fun getAllTestResult() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getAllTestResult()
                Log.d("TestResultViewModel", "getAllTestResult: $response")
                _allTestResult.value = response
            } catch (e: Exception) {
                Log.d("TestResultViewModel", "getAllTestResult error: $e")
                _errorState.value = "Unexpected error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}