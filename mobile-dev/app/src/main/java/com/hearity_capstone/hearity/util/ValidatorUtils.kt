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

    fun validatePhoneNumber(phoneNumber: String): Boolean {
        val phoneNumberRegex = Regex("^\\+?[1-9]\\d{1,14}\$")
        return phoneNumberRegex.matches(phoneNumber)
    }

    fun validateAddress(address: String): Boolean {
        val addressRegex = Regex("^.{3,}\$")
        return addressRegex.matches(address)
    }

    fun validateCity(city: String): Boolean {
        val cityRegex = Regex("^.{3,}\$")
        return cityRegex.matches(city)
    }
}