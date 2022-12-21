package com.example.horrorclubapp.presentation.searchscreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.horrorclubapp.presentation.theme.dark_black
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SearchScreen(
     navController: NavController
){
     val systemUiController = rememberSystemUiController()
     systemUiController.setSystemBarsColor(
          color = dark_black
     )
}