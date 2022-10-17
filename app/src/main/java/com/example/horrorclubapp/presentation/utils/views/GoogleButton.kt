package com.example.horrorclubapp.presentation.utils.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.horrorclubapp.R

@Composable
fun GoogleButton(
    onClick: () -> Unit
) {
    var clicked by remember { mutableStateOf(false) }
    Button(
        onClick = {
            clicked = !clicked
        },
        modifier = Modifier
            .background(Color.Transparent),
        shape = RoundedCornerShape(50)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = stringResource(id = R.string.ct_icon_google)
        )
        if (clicked) {
            onClick()
        }
    }
}
