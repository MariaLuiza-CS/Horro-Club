package com.example.horrorclubapp.presentation.utils

import com.google.common.truth.Truth
import org.junit.Test


class UtilsTest {

    @Test
    fun `login function returns false when email is empty`() {
        val emailEmpty = ""
        Truth.assertThat(Utils.validateEmail(emailEmpty)).isFalse()
    }

    @Test
    fun `login function returns true when email is valid`() {
        val emailCorrect = "user@email.com"
        Truth.assertThat(Utils.validateEmail(emailCorrect)).isTrue()
    }
}