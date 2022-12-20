package com.example.horrorclubapp.presentation.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.horrorclubapp.R
import com.example.horrorclubapp.domain.mode.Response
import com.example.horrorclubapp.presentation.theme.dark_black
import com.example.horrorclubapp.presentation.theme.pink
import com.example.horrorclubapp.presentation.theme.purple
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
            Box(
                contentAlignment = Alignment.BottomEnd
            ) {
                Card(
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth()
                        .drawWithContent {
                            val colors = listOf(
                                Transparent,
                                Transparent,
                                Transparent,
                                dark_black
                            )
                            drawContent()
                            drawRect(
                                brush = Brush.verticalGradient(colors)
                            )
                        }
                ) {
                    viewModel.tvShowResponses?.last()?.backdrop_path?.let { backdrop_path ->
                        val backImage = MovieCard(
                            url = "/w500_and_h282_face$backdrop_path"
                        )
                        backImage?.let { backImageBitmap ->
                            Image(
                                bitmap = backImageBitmap.asImageBitmap(),
                                contentDescription = stringResource(R.string.ct_icon_app),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .drawWithContent {
                                        val colors = listOf(
                                            dark_black,
                                            Transparent,
                                            Transparent,
                                            Transparent
                                        )
                                        drawContent()
                                        drawRect(
                                            brush = Brush.verticalGradient(colors)
                                        )
                                    },
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 25.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {

                        },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .background(
                                Transparent
                            )
                            .height(40.dp)
                            .width(50.dp),

                        contentPadding = PaddingValues()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(purple, pink)
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "null",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Popular movies",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h4,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
                Text(
                    text = "See all",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h4,
                    fontSize = 12.sp,
                    color = pink,
                    modifier = Modifier
                        .padding(end = 10.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow() {
                items(
                    items = viewModel.movieResponses!!
                ) { movie ->
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .height(245.dp)
                            .width(165.dp)
                            .background(Transparent),
                        shape = RoundedCornerShape(20.dp),
                        contentColor = Green
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
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Popular TvShows",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h4,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
                Text(
                    text = "See all",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h4,
                    fontSize = 12.sp,
                    color = pink,
                    modifier = Modifier
                        .padding(end = 10.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow() {
                items(
                    items = viewModel.tvShowResponses!!
                ) { tvShow ->
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .height(245.dp)
                            .width(165.dp)
                            .background(Transparent),
                        shape = RoundedCornerShape(20.dp),
                        contentColor = Green
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