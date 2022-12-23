package com.example.horrorclubapp.presentation.utils.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.horrorclubapp.presentation.theme.dark_light
import com.example.horrorclubapp.presentation.theme.grey

@Composable
fun SimpleGreyButton(
    text: String,
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(40),
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth(),
        contentPadding = PaddingValues(),
        elevation =  ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 15.dp,
            focusedElevation = 10.dp
        )
    ) {
        Row(
            modifier = Modifier
                .background(dark_light)
                .padding(16.dp, 16.dp)
                .fillMaxWidth()
        ) {
            Icon(
                icon,
                contentDescription = contentDescription,
                Modifier.padding(end = 10.dp),
                tint = Color.White
            )
            Text(
                text = text,
                color = Color.White,
                fontSize = 18.sp,
                style = MaterialTheme.typography.h4
            )
        }
    }
}