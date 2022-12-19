package com.example.horrorclubapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.horrorclubapp.presentation.homescreen.HomeScreen
import com.example.horrorclubapp.presentation.profilescreen.ProfileScreen
import com.example.horrorclubapp.presentation.searchscreen.SearchScreen
import com.example.horrorclubapp.presentation.utils.views.BottomBarNavigation


@Composable
fun SetUpBottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarNavigation.Home.route
    ) {
        composable(route = BottomBarNavigation.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = BottomBarNavigation.Search.route) {
            SearchScreen(navController = navController)
        }
        composable(route = BottomBarNavigation.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}
