package com.example.horrorclubapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.horrorclubapp.R

private val MontserratFamily = FontFamily(
    Font(R.font.montserrat_light, weight = FontWeight.Light),
    Font(R.font.montserrat_regular, weight = FontWeight.Normal),
    Font(R.font.montserrat_medium, weight = FontWeight.Medium),
    Font(R.font.montserrat_semibold, weight = FontWeight.SemiBold),
    Font(R.font.montserrat_bold, weight = FontWeight.Bold),
    Font(R.font.montserrat_extrabold, weight = FontWeight.ExtraBold)
)

val MontserratTypography = Typography(
    h1 = TextStyle(
        fontFamily = MontserratFamily,
        fontWeight = FontWeight.Light
    ),
    h2 = TextStyle(
        fontFamily = MontserratFamily,
        fontWeight = FontWeight.Normal
    ),
    h3 = TextStyle(
        fontFamily = MontserratFamily,
        fontWeight = FontWeight.Medium
    ),
    h4 = TextStyle(
        fontFamily = MontserratFamily,
        fontWeight = FontWeight.SemiBold
    ),
    h5 = TextStyle(
        fontFamily = MontserratFamily,
        fontWeight = FontWeight.Bold
    ),
    h6 = TextStyle(
        fontFamily = MontserratFamily,
        fontWeight = FontWeight.ExtraBold
    )
)
