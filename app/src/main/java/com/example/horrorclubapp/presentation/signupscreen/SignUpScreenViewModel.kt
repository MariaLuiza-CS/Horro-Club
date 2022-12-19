package com.example.horrorclubapp.presentation.signupscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horrorclubapp.domain.mode.Response
import com.example.horrorclubapp.domain.mode.User
import com.example.horrorclubapp.domain.repository.*
import com.example.horrorclubapp.domain.repository.remote.CreateUserWithEmailAndPasswordFirebaseResponse
import com.example.horrorclubapp.domain.repository.remote.SignInWithGoogleFirebaseResponse
import com.example.horrorclubapp.domain.repository.remote.SignInWithGoogleResponse
import com.example.horrorclubapp.domain.usecase.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {

    var signInWithGoogleResponse by mutableStateOf<SignInWithGoogleResponse>(Response.Success(null))
        private set

    var googleSignInClient by mutableStateOf<SignInWithGoogleFirebaseResponse>(Response.Success(null))
        private set

    var createWithEmailAndPassword by mutableStateOf<CreateUserWithEmailAndPasswordFirebaseResponse>(Response.Success(null))
        private set

    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user

    fun googleAuthSecond() = viewModelScope.launch {
        googleSignInClient = Response.Loading
        googleSignInClient = userUseCases.signInWithGoogle.invoke()
    }

    fun createWithEmailAndPassword(
        email: String,
        password: String
    ) = viewModelScope.launch {
        createWithEmailAndPassword = userUseCases.createWithEmailAndPassword(
            email = email,
            password = password
        )
    }
}