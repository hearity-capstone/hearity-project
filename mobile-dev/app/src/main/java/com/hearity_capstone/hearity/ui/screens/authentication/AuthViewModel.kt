package com.hearity_capstone.hearity.ui.screens.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hearity_capstone.hearity.data.api.TokenProvider
import com.hearity_capstone.hearity.data.model.LoginResponse
import com.hearity_capstone.hearity.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AuthViewModel(
    private val repository: AuthRepository,
    private val tokenProvider: TokenProvider
) : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    private val _isLoggedIn = MutableStateFlow<Boolean>(false)
    private val _loginState = MutableStateFlow<LoginResponse?>(null)
    private val _errorState = MutableStateFlow<String?>(null)


    val isLoading: StateFlow<Boolean> = _isLoading
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn
    val loginState: StateFlow<LoginResponse?> = _loginState
    val errorState: StateFlow<String?> = _errorState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.login(email, password)
                if (response.statusCode == 200) {
                    _isLoggedIn.value = true
                    _loginState.value = response
                    viewModelScope.launch {
                        tokenProvider.saveToken(response.token)
                    }
                } else {
                    _errorState.value = response.message
                }
            } catch (e: HttpException) {
                _errorState.value = "Something went wrong, check your email or password"
            } catch (e: IOException) {
                _errorState.value = "Network error, please check your connection"
            } catch (e: Exception) {
                _errorState.value = "Unexpected error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearErrorState() {
        _errorState.value = null
    }
}