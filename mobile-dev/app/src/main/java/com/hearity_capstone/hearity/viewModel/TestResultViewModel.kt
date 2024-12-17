package com.hearity_capstone.hearity.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hearity_capstone.hearity.data.model.testResult.AddTestResultRequest
import com.hearity_capstone.hearity.data.model.testResult.TestResultModel
import com.hearity_capstone.hearity.data.repository.testResult.TestResultRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class TestResultViewModel(
    private val repository: TestResultRepository,
) : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    private val _firstFetchAllTestResult = MutableStateFlow<Boolean>(true)
    private val _latestTestResult = MutableStateFlow<TestResultModel?>(null)
    private val _allTestResult = MutableStateFlow<List<TestResultModel>?>(null)
    private val _errorState = MutableStateFlow<String?>(null)
    private val _addTestResultState = MutableStateFlow<AddTestResultState>(AddTestResultState.Idle)

    val isLoading: StateFlow<Boolean> = _isLoading
    val latestTestResult: StateFlow<TestResultModel?> = _latestTestResult
    val allTestResult: StateFlow<List<TestResultModel>?> = _allTestResult
    val errorState: StateFlow<String?> = _errorState
    val addTestResultState: StateFlow<AddTestResultState?> = _addTestResultState

    fun initialFetchAllTestResult() {
        if (_firstFetchAllTestResult.value) {
            getAllTestResult()
            _firstFetchAllTestResult.value = false
        }
    }

    fun getAllTestResult() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getAllTestResult()
                _allTestResult.value = response.data
                _latestTestResult.value = response.data.firstOrNull()
            } catch (e: IOException) {
                _errorState.value = "Network error, please check your connection"
            } catch (e: Exception) {
                _errorState.value = "Unexpected error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addTestResult(request: AddTestResultRequest) {
        viewModelScope.launch {
            try {
                _addTestResultState.value = AddTestResultState.Loading
                _isLoading.value = true
                val response = repository.addTestResult(request)
                if (response.statusCode == 200) {
                    _addTestResultState.value = AddTestResultState.Success
                    Log.d("TestResultViewModel", "Success add test result ${response.message}")
                } else {
                    _addTestResultState.value = AddTestResultState.Error(response.message)
                }
            } catch (e: IOException) {
                Log.d("TestResultViewModel", "Network error")
                _addTestResultState.value =
                    AddTestResultState.Error("Network error, please check your connection")
            } catch (e: Exception) {
                Log.d("TestResultViewModel", e.toString())
                _addTestResultState.value = AddTestResultState.Error("Unexpected error")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearErrorState() {
        _errorState.value = null
    }
}

sealed class AddTestResultState {
    object Idle : AddTestResultState()
    object Loading : AddTestResultState()
    object Success : AddTestResultState()
    data class Error(val message: String) : AddTestResultState()
}