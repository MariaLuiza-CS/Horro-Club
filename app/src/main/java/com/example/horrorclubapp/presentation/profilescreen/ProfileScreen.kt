package com.example.horrorclubapp.presentation.profilescreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.horrorclubapp.R
import com.example.horrorclubapp.domain.mode.Response
import com.example.horrorclubapp.presentation.theme.dark_black
import com.example.horrorclubapp.presentation.theme.dark_light
import com.example.horrorclubapp.presentation.theme.pink
import com.example.horrorclubapp.presentation.theme.purple
import com.example.horrorclubapp.presentation.utils.Screen
import com.example.horrorclubapp.presentation.utils.views.ProgressBar
import com.example.horrorclubapp.presentation.utils.views.SimpleGreyButton
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProfileScreen(
    navController: NavController,
    navController02: NavController,
    viewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = dark_black
    )

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
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Profile",
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.h2,
                        fontSize = 24.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "null"
                        )
                    }
                },
                elevation = 0.dp,
                backgroundColor = dark_black,
                contentColor = Color.White
            )
        }, content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(dark_black)
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Column(
                    Modifier.padding(16.dp)
                ) {
                    var expanded by remember { mutableStateOf(false) }
                    val suggestions = listOf(
                        "They",
                        "She",
                        "He",
                        "Co",
                        "En",
                        "Ey",
                        "Xie",
                        "Yo",
                        "Ze",
                        "Ve",
                        "Any Pronouns"
                    )
                    var selectedText by remember { mutableStateOf("Pronouns") }
                    var nameText by remember { mutableStateOf("Name") }
                    var textfieldSize by remember { mutableStateOf(Size.Zero) }

                    val icon = if (expanded)
                        Icons.Filled.KeyboardArrowUp
                    else
                        Icons.Filled.KeyboardArrowDown
                    Column(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painterResource(id = R.drawable.ic_zombie_girl),
                                contentDescription = stringResource(R.string.ct_icon_app),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(150.dp)
                                    .border(
                                        BorderStroke(4.dp, pink), CircleShape
                                    )
                                    .padding(4.dp)
                                    .clip(CircleShape)
                            )
                            Column(
                                modifier = Modifier.size(200.dp).padding(end = 20.dp, bottom = 5.dp),
                                horizontalAlignment = Alignment.End,
                                verticalArrangement = Arrangement.Bottom
                            ) {
                                Button(
                                    onClick = {
                                        navController02.navigate(Screen.AvatarScreen.route)
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
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = "null",
                                            modifier = Modifier.size(24.dp),
                                            tint = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }
                    OutlinedTextField(
                        value = nameText,
                        onValueChange = { nameText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { coordinates ->
                                //This value is used to assign to the DropDown the same width
                                textfieldSize = coordinates.size.toSize()
                            },
                        label = { null },
                        leadingIcon = {
                            Icon(Icons.Default.Person, "contentDescription")
                        },
                        shape = RoundedCornerShape(40),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = dark_light,
                            focusedBorderColor = dark_light,
                            unfocusedBorderColor = dark_light,
                            textColor = Color.White,
                            trailingIconColor = Color.White,
                            disabledTextColor = Color.White,
                            disabledTrailingIconColor = Color.White,
                            disabledLeadingIconColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    SimpleGreyButton(
                        text = "veggiebrain@email.com",
                        icon = Icons.Default.Email,
                        contentDescription = "Person Button"
                    ) {
                        navController02.navigate(Screen.AvatarScreen.route)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    OutlinedTextField(
                        value = selectedText,
                        onValueChange = { selectedText = it },
                        enabled = false,
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { coordinates ->
                                //This value is used to assign to the DropDown the same width
                                textfieldSize = coordinates.size.toSize()
                            },
                        label = { null },
                        leadingIcon = {
                            Icon(Icons.Default.Transgender, "contentDescription")
                        },
                        trailingIcon = {
                            Icon(icon, "contentDescription",
                                Modifier.clickable { expanded = !expanded })
                        },
                        shape = RoundedCornerShape(40),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = dark_light,
                            focusedBorderColor = dark_light,
                            unfocusedBorderColor = dark_light,
                            textColor = Color.White,
                            trailingIconColor = Color.White,
                            disabledTextColor = Color.White,
                            disabledTrailingIconColor = Color.White,
                            disabledLeadingIconColor = Color.White
                        )
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                    ) {
                        suggestions.forEach { label ->
                            DropdownMenuItem(onClick = {
                                selectedText = label
                                expanded = false
                            }) {
                                Text(text = label)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    SimpleGreyButton(
                        text = "Support",
                        icon = Icons.Default.SupportAgent,
                        contentDescription = "Person Button"
                    ) {
                        navController02.navigate(Screen.AvatarScreen.route)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    SimpleGreyButton(
                        text = "Log Out",
                        icon = Icons.Default.Logout,
                        contentDescription = "Person Button"
                    ) {
                        navController02.navigate(Screen.AvatarScreen.route)
                    }
                }
            }
        })
}
