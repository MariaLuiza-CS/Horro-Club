package com.example.horrorclubapp.presentation.loginscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.horrorclubapp.R
import com.example.horrorclubapp.domain.mode.Response
import com.example.horrorclubapp.presentation.theme.*
import com.example.horrorclubapp.presentation.utils.Screen
import com.example.horrorclubapp.presentation.utils.views.GradientButton
import com.example.horrorclubapp.presentation.utils.views.ProgressBar
import com.example.horrorclubapp.utils.TextInput

@Composable
fun LoginScreen(
    navController: NavHostController, viewModel: LoginScreenViewModel = hiltViewModel()
) {

    var isChecked by remember { mutableStateOf(true) }

    val launcher = rememberFirebaseAuthLauncher(onAuthComplete = {
        if (it) {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        }
    }, onAuthError = {
        Log.e(it.message, it.message.toString())
    })

    when (val signInWithGoogleFirebaseResponse = viewModel.googleSignInClient) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> signInWithGoogleFirebaseResponse.data?.let {
            LaunchedEffect(it) {
                launcher.launch(it.signInIntent)
            }

        }
        is Response.Failure -> LaunchedEffect(Unit) {
            print(signInWithGoogleFirebaseResponse.e)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.tv_login),
                modifier = Modifier.fillMaxWidth(),
                fontSize = 26.sp,
                textAlign = TextAlign.Start,
                color = if (isSystemInDarkTheme()) white else dark_black,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(80.dp))
            TextInput(textLabel = stringResource(id = R.string.tv_email))
            Spacer(modifier = Modifier.height(16.dp))
            TextInput(textLabel = stringResource(id = R.string.tv_password))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Checkbox(
                checked = isChecked, onCheckedChange = {
                    isChecked = it
                }, colors = CheckboxDefaults.colors(
                    uncheckedColor = white_dark, checkedColor = purple, checkmarkColor = white
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = R.string.tv_rebember_me),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h2,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .height(1.dp)
                    .width(150.dp)
                    .background(white_dark)
            ) {}
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = R.string.tv_or),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h2,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .height(1.dp)
                    .width(150.dp)
                    .background(white_dark)
            ) {}
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    viewModel.googleAuthSecond()
                }, modifier = Modifier.background(Color.Transparent), shape = RoundedCornerShape(50)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = stringResource(id = R.string.ct_icon_google),
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {

                }, modifier = Modifier.background(Color.Transparent), shape = RoundedCornerShape(50)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = stringResource(id = R.string.ct_icon_facebook),
                )
            }
        }
        Spacer(modifier = Modifier.height(64.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            GradientButton(
                text = stringResource(id = R.string.button_login),
                textColor = white,
                gradient = Brush.horizontalGradient(
                    colors = listOf(purple, pink)
                )
            ) {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.tv_dont_have_account),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h2,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.tv_sign_up),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h5,
                    fontSize = 16.sp
                )
            }
        }
    }

}


