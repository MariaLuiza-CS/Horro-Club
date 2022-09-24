package com.example.horrorclubapp.utils

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object Onboard : Screen("onboard_screen")
}