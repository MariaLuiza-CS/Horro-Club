package com.example.horrorclubapp.presentation.utils

import androidx.core.util.PatternsCompat.EMAIL_ADDRESS

object Utils {

    fun validatePassword(password: String): Boolean {
        return password.length < 8 ||
                password
                    .filter { it.isDigit() }
                    .firstOrNull() == null ||
                password
                    .filter { it.isLetter() }
                    .filter { it.isUpperCase() }
                    .firstOrNull() == null ||
                password
                    .filter { it.isLetter() }
                    .filter { it.isLowerCase() }
                    .firstOrNull() == null ||
                password
                    .filter { !it.isLetterOrDigit() }
                    .firstOrNull() == null
    }

    fun validateEmail(email: String): Boolean {
        return !email.isEmpty() ||
                EMAIL_ADDRESS
                    .matcher(email)
                    .matches()
    }
}