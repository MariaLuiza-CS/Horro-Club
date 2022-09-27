package com.example.horrorclubapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.horrorclubapp.ui.screen.onboardingscreen.OnboardViewModel
import com.example.horrorclubapp.ui.theme.dark_black
import com.example.horrorclubapp.ui.theme.pink
import com.example.horrorclubapp.ui.theme.purple
import com.example.horrorclubapp.ui.theme.white
import com.example.horrorclubapp.utils.GradientButton
import com.example.horrorclubapp.utils.Screen

@Composable
fun OnboardScreen(
    navController: NavHostController,
    onboardViewModel: OnboardViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_angled_ghost),
                contentDescription = stringResource(R.string.ct_icon_app),
                modifier = Modifier
                    .size(200.dp)
                    .padding(start = 100.dp)
            )
        }
        Spacer(modifier = Modifier.size(100.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.tv_welcome),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = if (isSystemInDarkTheme()) white else dark_black,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.app_description),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = if (isSystemInDarkTheme()) white else dark_black,
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(60.dp))
            GradientButton(
                text = stringResource(id = R.string.button_start),
                textColor = white,
                gradient = Brush.horizontalGradient(
                    colors = listOf(purple, pink)
                )
            ) {
                onboardViewModel.saveOnBoardingState(completed = true)
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }
        }
    }
}