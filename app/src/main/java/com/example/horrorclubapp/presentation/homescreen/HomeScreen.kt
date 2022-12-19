package com.example.horrorclubapp.presentation.homescreen

import android.icu.number.Scale
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.horrorclubapp.R
import com.example.horrorclubapp.domain.mode.Response
import com.example.horrorclubapp.presentation.theme.dark_black
import com.example.horrorclubapp.presentation.utils.Screen
import com.example.horrorclubapp.presentation.utils.views.MovieCard
import com.example.horrorclubapp.presentation.utils.views.ProgressBar

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

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

    if (viewModel.movieResponses != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(dark_black)
                .verticalScroll(rememberScrollState())
        ) {
            viewModel.getAllMovies()
            viewModel.getAllTVShow()
            Row() {
                Card(
                    modifier = Modifier
                        .height(250.dp)
                        .width(444.dp)
                        .background(Color.Transparent),
                    shape = RoundedCornerShape(20.dp),
                    contentColor = Color.Green
                ) {
                    viewModel.tvShowResponses?.get(0)?.backdrop_path?.let { backdrop_path ->
                        val backImage = MovieCard(
                            url = "/w500_and_h282_face$backdrop_path"
                        )
                        backImage?.let { backImageBitmap ->
                            Image(
                                bitmap = backImageBitmap.asImageBitmap(),
                                contentDescription = stringResource(R.string.ct_icon_app),
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
            LazyRow() {
                items(
                    items = viewModel.movieResponses!!
                ) { movie ->
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .height(245.dp)
                            .width(165.dp)
                            .background(Color.Transparent),
                        shape = RoundedCornerShape(20.dp),
                        contentColor = Color.Green
                    ) {
                        movie.poster_path?.let {
                            val image = MovieCard(
                                url = "/w600_and_h900_bestv2$it"
                            )
                            image?.let { bitmapImage ->
                                Image(
                                    bitmap = bitmapImage.asImageBitmap(),
                                    contentDescription = stringResource(R.string.ct_icon_app),
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            }
            LazyRow() {
                items(
                    items = viewModel.tvShowResponses!!
                ) { tvShow ->
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .height(245.dp)
                            .width(165.dp)
                            .background(Color.Transparent),
                        shape = RoundedCornerShape(20.dp),
                        contentColor = Color.Green
                    ) {
                        tvShow.poster_path?.let {
                            val image = MovieCard(
                                url = "/w600_and_h900_bestv2$it"
                            )
                            image?.let { bitmapImage ->
                                Image(
                                    bitmap = bitmapImage.asImageBitmap(),
                                    contentDescription = stringResource(R.string.ct_icon_app),
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}