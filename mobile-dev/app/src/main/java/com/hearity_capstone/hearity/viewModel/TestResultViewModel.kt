package com.hearity_capstone.hearity.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hearity_capstone.hearity.data.model.testResult.TestResultModel
import com.hearity_capstone.hearity.data.repository.testResult.TestResultRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TestResultViewModel(
    private val repository: TestResultRepository
) : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false)

    private val _latestTestResult = MutableStateFlow<TestResultModel?>(null)
    private val _allTestResult = MutableStateFlow<List<TestResultModel>?>(null)
    private val _errorState = MutableStateFlow<String?>(null)

    val isLoading: StateFlow<Boolean> = _isLoading
    val latestTestResult: StateFlow<TestResultModel?> = _latestTestResult
    val allTestResult: StateFlow<List<TestResultModel>?> = _allTestResult
    val errorState: StateFlow<String?> = _errorState

    fun getAllTestResult() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getAllTestResult()
                _allTestResult.value = response.data
                _latestTestResult.value = response.data.firstOrNull()
            } catch (e: Exception) {
                _errorState.value = "Unexpected error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}