package com.example.horrorclubapp.presentation.splashscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horrorclubapp.data.local.onboarddatasource.DataStoreRepository
import com.example.horrorclubapp.domain.repository.AuthGoogleRepository
import com.example.horrorclubapp.presentation.utils.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val authGoogleRepository: AuthGoogleRepository,
) : ViewModel() {
    private val isUserAuthenticated get() = authGoogleRepository.isUserAuthenticatedInFirebase

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.Onboard.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            dataStoreRepository.readOnBoardingState().collect { completed ->
                if (completed) {
                    if (isUserAuthenticated) _startDestination.value = Screen.Home.route
                        else _startDestination.value = Screen.Login.route
                } else {
                    _startDestination.value = Screen.Onboard.route
                }
            }
            _isLoading.value = false
        }
    }
}