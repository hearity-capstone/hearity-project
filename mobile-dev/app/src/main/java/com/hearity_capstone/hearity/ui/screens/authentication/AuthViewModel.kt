package com.hearity_capstone.hearity.ui.screens.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hearity_capstone.hearity.data.model.LoginResponse
import com.hearity_capstone.hearity.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()
    private val _isLoading = MutableStateFlow<Boolean>(false)
    private val _isLoggedIn = MutableStateFlow<Boolean>(false)
    private val _loginState = MutableStateFlow<LoginResponse?>(null)
    private val _errorState = MutableStateFlow<String?>(null)

    val isLoading: StateFlow<Boolean> = _isLoading
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn
    val loginState: StateFlow<LoginResponse?> = _loginState
    val errorState: StateFlow<String?> = _errorState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.login(username, password)
                if (response.statusCode == 200) {
                    _isLoggedIn.value = true
                    _loginState.value = response
                } else {
                    _errorState.value = response.message
                }
            } catch (e: Exception) {
                _errorState.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearErrorState() {
        _errorState.value = null
    }
}