package com.example.horrorclubapp.presentation.splashscreen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.horrorclubapp.R
import com.example.horrorclubapp.presentation.theme.dark_black
import com.example.horrorclubapp.presentation.theme.white
import com.example.horrorclubapp.presentation.utils.Screen
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController) {

    LaunchedEffect(key1 = true) {
        delay(4000)
        navController.popBackStack()
        navController.navigate(Screen.Onboard.route)
    }
    Splash()
}

@Composable
fun Splash() {
    Box(
        modifier = Modifier
            .background(dark_black)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(id = R.drawable.ic_ghost),
            contentDescription = stringResource(R.string.ct_icon_app),
            Modifier.size(150.dp)
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash()
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DarkThemeSplashScreenPreview() {
    Splash()
}
