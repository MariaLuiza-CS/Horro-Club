package com.example.horrorclubapp.presentation.utils

sealed class Screen(val route: String) {
    object Onboard : Screen("onboard_screen")
    object Splash : Screen("splash_screen")
    object Login : Screen("login_screen")
    object SignUp : Screen("sign_up_screen")
    object Main: Screen("main_screen")
    object AvatarScreen: Screen("avatar_screen")
}