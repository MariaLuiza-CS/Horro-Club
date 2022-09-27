package com.example.horrorclubapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.horrorclubapp.ui.theme.dark_black
import com.example.horrorclubapp.ui.theme.dark_light

@Composable
fun TextInput(
    textLabel: String
): String {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        var text by remember { mutableStateOf(textLabel) }
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            modifier = Modifier.background(color = dark_light),
            keyboardOptions = KeyboardOptions(
                keyboardType = setKeyBoardOptions(textLabel),
                imeAction = ImeAction.Done
            )
        )
    }
}

@Composable
fun setKeyBoardOptions(textLabel: String): KeyboardType {
    return when (textLabel) {
        "Email" -> KeyboardType.Email
        "Password" -> KeyboardType.Password
        "Confirm Password" -> KeyboardType.Password
        else -> KeyboardType.Text
    }
}
