package com.example.horrorclubapp.presentation.profilescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.horrorclubapp.domain.mode.Response
import com.example.horrorclubapp.navigation.SetupNavGraph
import com.example.horrorclubapp.presentation.theme.dark_black
import com.example.horrorclubapp.presentation.theme.pink
import com.example.horrorclubapp.presentation.theme.purple
import com.example.horrorclubapp.presentation.theme.white
import com.example.horrorclubapp.presentation.utils.Screen
import com.example.horrorclubapp.presentation.utils.views.GradientButton
import com.example.horrorclubapp.presentation.utils.views.ProgressBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProfileScreen(
    navController: NavController,
    navController02: NavController,
    viewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = dark_black
    )

    when (val signOutResponse = viewModel.signOutResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> signOutResponse.data?.let { signedOut ->
            if (signedOut) {
                LaunchedEffect(signedOut) {
                    navController.popBackStack()
                    navController.navigate(Screen.Login.route)
                }
            }

        }
        is Response.Failure -> LaunchedEffect(Unit) {
            print(signOutResponse.e)
        }
    }

    when (val revokeAccessResponse = viewModel.revokeAccessResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> revokeAccessResponse.data?.let { accessRevoked ->
            if (accessRevoked) {
                LaunchedEffect(accessRevoked) {
                    navController.popBackStack()
                    navController.navigate(Screen.Login.route)
                }
            }

        }
        is Response.Failure -> LaunchedEffect(Unit) {
            print(revokeAccessResponse.e)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(dark_black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(Modifier.padding(16.dp)) {
            GradientButton(
                text = "Choose", textColor = white, gradient = Brush.horizontalGradient(
                    colors = listOf(purple, pink)
                )
            ) {
                navController.popBackStack()
                navController02.navigate(Screen.AvatarScreen.route)
            }
        }
    }
}
