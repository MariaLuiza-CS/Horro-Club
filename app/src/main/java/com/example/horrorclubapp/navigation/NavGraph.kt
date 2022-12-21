package com.example.horrorclubapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.horrorclubapp.presentation.MainScreen
import com.example.horrorclubapp.presentation.avatarscreen.AvatarScreen
import com.example.horrorclubapp.presentation.homescreen.HomeScreen
import com.example.horrorclubapp.presentation.loginscreen.LoginScreen
import com.example.horrorclubapp.presentation.onboardingscreen.OnboardScreen
import com.example.horrorclubapp.presentation.profilescreen.ProfileScreen
import com.example.horrorclubapp.presentation.searchscreen.SearchScreen
import com.example.horrorclubapp.presentation.signupscreen.SignUpScreen
import com.example.horrorclubapp.presentation.splashscreen.AnimatedSplashScreen
import com.example.horrorclubapp.presentation.utils.Screen
import com.example.horrorclubapp.presentation.utils.views.BottomBarNavigation


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
        composable(route = Screen.SignUp.route) {
            SignUpScreen(navController = navController)
        }
        composable(route = Screen.Main.route) {
            MainScreen(navController02 = navController)
        }
        composable(route = Screen.AvatarScreen.route) {
            AvatarScreen(navController = navController)
        }
    }
}