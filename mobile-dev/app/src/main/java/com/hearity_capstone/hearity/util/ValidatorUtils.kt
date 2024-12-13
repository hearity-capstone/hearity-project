package com.hearity_capstone.hearity.util

object ValidatorUtils {
    fun validateEmail(email: String): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        return emailRegex.matches(email)
    }

    fun validatePassword(password: String): Boolean {
        val passwordRegex = Regex(".{8,}\$")
        return passwordRegex.matches(password)
    }

    fun validateName(name: String): Boolean {
        val nameRegex = Regex("^[a-zA-Z]{3,}\$")
        return nameRegex.matches(name)
    }
}