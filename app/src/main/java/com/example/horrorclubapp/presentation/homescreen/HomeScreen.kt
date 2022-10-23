package com.example.horrorclubapp.presentation.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.horrorclubapp.domain.mode.Response
import com.example.horrorclubapp.presentation.utils.Screen
import com.example.horrorclubapp.presentation.utils.views.ProgressBar

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = viewModel.displayName,
            fontSize = 24.sp
        )
        Button(onClick = {
            viewModel.signOut()
        }) {
            Text(
                text = "SIGNOUT",
                fontSize = 24.sp
            )
        }
        Button(onClick = {
            viewModel.revokeAccess()
        }) {
            Text(
                text = "REVOKEACESS",
                fontSize = 24.sp
            )
        }
    }

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
}