package com.example.horrorclubapp.presentation.utils.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarNavigation(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarNavigation(
        route = "home_screen",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Search : BottomBarNavigation(
        route = "search_screen",
        title = "Search",
        icon = Icons.Default.Search
    )

    object Profile : BottomBarNavigation(
        route = "profile_screen",
        title = "Profile",
        icon = Icons.Default.Person
    )
}
