package com.example.horrorclubapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.horrorclubapp.R
import com.example.horrorclubapp.presentation.theme.dark_light
import com.example.horrorclubapp.presentation.theme.white_dark


@Composable
fun TextInput(
    textLabel: String
): String {

    var text by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            placeholder = { Text(text = textLabel) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(dark_light, shape = RoundedCornerShape(15.dp))
                .border(1.dp, white_dark, shape = RoundedCornerShape(15.dp)),
            keyboardOptions = KeyboardOptions(
                keyboardType = setKeyBoardOptions(textLabel),
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisibility = !passwordVisibility },
                    enabled = enableIcon(textLabel = textLabel)
                ) {
                    Icon(
                        painter = setIcon(textLabel, passwordVisibility),
                        contentDescription = setIconDescription(textLabel),
                        tint = white_dark
                    )
                }
            },
            visualTransformation = passwordVisualTransformation(textLabel, passwordVisibility),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                cursorColor = white_dark
            )
        )
    }
    return text
}

@Composable
fun passwordVisualTransformation(
    textLabel: String,
    passwordVisibility: Boolean
): VisualTransformation {
    return when {
        textLabel == "Email" -> VisualTransformation.None
        textLabel.contains("Password", true) ->
            if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
        textLabel == "Name" -> VisualTransformation.None
        else -> VisualTransformation.None
    }
}

@Composable
fun enableIcon(textLabel: String): Boolean {
    return when {
        textLabel == "Email" -> false
        textLabel.contains("Password", true) -> true
        textLabel == "Name" -> false
        else -> false
    }
}

@Composable
fun setIcon(textLabel: String, passwordVisibility: Boolean): Painter {
    return when {
        textLabel == "Email" -> painterResource(id = R.drawable.ic_email)
        textLabel.contains("Password", true) ->
            if (passwordVisibility) painterResource(id = R.drawable.ic_eye) else painterResource(id = R.drawable.ic_eye_off)
        textLabel == "Name" -> painterResource(id = R.drawable.ic_person)
        else -> painterResource(id = R.drawable.ic_email)
    }
}

@Composable
fun setIconDescription(textLabel: String): String {
    return when {
        textLabel == "Email" -> stringResource(id = R.string.ct_icon_email)
        textLabel.contains("Password", true) -> stringResource(id = R.string.ct_icon_password)
        else -> stringResource(id = R.string.ct_icon_user)
    }
}

@Composable
fun setKeyBoardOptions(textLabel: String): KeyboardType {
    return when {
        textLabel == "Email" -> KeyboardType.Email
        textLabel.contains("Password", true) -> KeyboardType.Password
        else -> KeyboardType.Text
    }
}

