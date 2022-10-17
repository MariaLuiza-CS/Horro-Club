package com.example.horrorclubapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.horrorclubapp.presentation.splashscreen.AnimatedSplashScreen
import com.example.horrorclubapp.presentation.loginscreen.LoginScreen
import com.example.horrorclubapp.presentation.onboardingscreen.OnboardScreen
import com.example.horrorclubapp.presentation.utils.Screen


@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Screen.Onboard.route) {
            OnboardScreen(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            Box(modifier = Modifier.fillMaxSize())
        }
    }
}