package com.example.horrorclubapp.presentation.avatarscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.horrorclubapp.R
import com.example.horrorclubapp.presentation.theme.green
import com.example.horrorclubapp.presentation.theme.pink
import com.example.horrorclubapp.presentation.theme.purple
import com.example.horrorclubapp.presentation.theme.white
import com.example.horrorclubapp.presentation.utils.Screen
import com.example.horrorclubapp.presentation.utils.views.GradientButton
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AvatarScreen(
    navController: NavController,
    viewModel: AvatarScreenViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState()

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = green
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(green),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Avatar",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h2,
                    fontSize = 24.sp
                )
            },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "null"
                    )
                }
            },
            elevation = 0.dp,
            backgroundColor = Color.Transparent,
            contentColor = Color.White
        )
        Column(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalPager(count = viewModel.charactersList.size, state = pagerState) { page ->
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painterResource(id = viewModel.charactersList[page]),
                        contentDescription = stringResource(R.string.ct_icon_app),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .border(
                                BorderStroke(4.dp, pink), CircleShape
                            )
                            .padding(4.dp)
                            .clip(CircleShape)
                    )
                }
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(end = 8.dp, start = 8.dp, bottom = 250.dp),
        )
        Column(Modifier.padding(16.dp)) {

            GradientButton(
                text = "Choose", textColor = white, gradient = Brush.horizontalGradient(
                    colors = listOf(purple, pink)
                )
            ) {
                navController.navigate(Screen.Main.route)
            }
        }
    }
}