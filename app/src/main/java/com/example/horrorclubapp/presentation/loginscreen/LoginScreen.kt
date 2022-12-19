package com.example.horrorclubapp.presentation.loginscreen

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.horrorclubapp.R
import com.example.horrorclubapp.domain.mode.Response
import com.example.horrorclubapp.presentation.theme.*
import com.example.horrorclubapp.presentation.utils.Screen
import com.example.horrorclubapp.presentation.utils.Utils
import com.example.horrorclubapp.presentation.utils.views.GradientButton
import com.example.horrorclubapp.presentation.utils.views.ProgressBar
import com.example.horrorclubapp.utils.*

@Composable
fun LoginScreen(
    navController: NavHostController, viewModel: LoginScreenViewModel = hiltViewModel()
) {

    var isChecked by remember { mutableStateOf(true) }
    var textFieldEmail by remember { mutableStateOf("") }
    var textFieldPassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var borderFieldEmail: Color by remember { mutableStateOf(white_dark) }
    var borderFieldPassword by remember { mutableStateOf(white_dark) }
    var isErrorTextFieldEmail by rememberSaveable { mutableStateOf(false) }
    var isErrorTextFieldPassword by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val launcher = rememberFirebaseAuthLauncher(onAuthComplete = {
        if (it) {
            navController.popBackStack()
            navController.navigate(Screen.Main.route)
        }
    }, onAuthError = {
        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
    })

    when (val signInWithGoogleFirebaseResponse = viewModel.googleSignInClient) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> signInWithGoogleFirebaseResponse.data?.let {
            LaunchedEffect(it) {
                launcher.launch(it.signInIntent)
            }
        }
        is Response.Failure -> LaunchedEffect(Unit) {
            Toast.makeText(context, signInWithGoogleFirebaseResponse.e.message, Toast.LENGTH_SHORT)
                .show()
        }
    }

    when (val signInWithEmailAndPassword = viewModel.signInWithEmailAndPassword) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> signInWithEmailAndPassword.data?.let {
            LaunchedEffect(it) {
                navController.popBackStack()
                navController.navigate(Screen.Main.route)
            }
        }
        is Response.Failure -> LaunchedEffect(Unit) {
            Toast.makeText(context, signInWithEmailAndPassword.e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun validate(text: String, textField: String): Boolean {
        when {
            textField == "Email" -> {
                if (Utils.validateEmail(text)
                ) {
                    isErrorTextFieldEmail = true
                    borderFieldEmail = Color.Red
                    return false
                } else {
                    return true
                }
            }
            textField.contains("Password", true) -> {
                if (Utils.validatePassword(text)) {
                    isErrorTextFieldPassword = true
                    borderFieldPassword = Color.Red
                    return false
                } else {
                    return true
                }
            }
            else -> {
                isErrorTextFieldEmail = false
                isErrorTextFieldPassword = false
                return true
            }
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
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = textFieldEmail,
                    onValueChange = { newText ->
                        textFieldEmail = newText
                        isErrorTextFieldEmail = false
                    },
                    placeholder = { Text(text = stringResource(id = R.string.tv_email)) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(dark_light, shape = RoundedCornerShape(15.dp))
                        .border(1.dp, borderFieldEmail, shape = RoundedCornerShape(15.dp)),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = setKeyBoardOptions(stringResource(id = R.string.tv_email)),
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    trailingIcon = {
                        IconButton(
                            onClick = { passwordVisibility = !passwordVisibility },
                            enabled = enableIcon(textLabel = stringResource(id = R.string.tv_email))
                        ) {
                            Icon(
                                painter = setIcon(
                                    stringResource(id = R.string.tv_email), passwordVisibility
                                ),
                                contentDescription = setIconDescription(stringResource(id = R.string.tv_email)),
                                tint = white_dark
                            )
                        }
                    },
                    visualTransformation = passwordVisualTransformation(
                        stringResource(id = R.string.tv_email), passwordVisibility
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        cursorColor = white_dark
                    )
                )
                if (isErrorTextFieldEmail) {
                    Text(
                        text = stringResource(id = R.string.tv_error_email),
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                } else {
                    borderFieldEmail = white_dark
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = textFieldPassword,
                    onValueChange = { newText ->
                        textFieldPassword = newText
                        isErrorTextFieldPassword = false
                    },
                    placeholder = { Text(text = stringResource(id = R.string.tv_password)) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(dark_light, shape = RoundedCornerShape(15.dp))
                        .border(1.dp, borderFieldPassword, shape = RoundedCornerShape(15.dp)),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = setKeyBoardOptions(stringResource(id = R.string.tv_password)),
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    trailingIcon = {
                        IconButton(
                            onClick = { passwordVisibility = !passwordVisibility },
                            enabled = enableIcon(textLabel = stringResource(id = R.string.tv_password))
                        ) {
                            Icon(
                                painter = setIcon(
                                    stringResource(id = R.string.tv_password), passwordVisibility
                                ),
                                contentDescription = setIconDescription(stringResource(id = R.string.tv_password)),
                                tint = white_dark
                            )
                        }
                    },
                    visualTransformation = passwordVisualTransformation(
                        stringResource(id = R.string.tv_password), passwordVisibility
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        cursorColor = white_dark
                    )
                )
                if (isErrorTextFieldPassword) {
                    Text(
                        text = stringResource(id = R.string.tv_error_password),
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                } else {
                    borderFieldPassword = white_dark
                }
            }
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
            OutlinedButton(
                onClick = {
                    viewModel.googleAuthSecond()
                },
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.dp, white_dark)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = stringResource(id = R.string.ct_icon_google),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Sign in With Google",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h2,
                    fontSize = 16.sp,
                    color = Color.White
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
                if (validate(
                        text = textFieldEmail,
                        textField = "Email"
                    ) && validate(text = textFieldPassword, textField = "Password")
                ) {
                    viewModel.signInWithEmailAndPassword(textFieldEmail, textFieldPassword)
                }
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
                Text(text = stringResource(id = R.string.tv_sign_up),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h5,
                    fontSize = 16.sp,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.SignUp.route)
                    })
            }
        }
    }
}


