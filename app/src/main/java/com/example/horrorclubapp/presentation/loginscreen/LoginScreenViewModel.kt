package com.example.horrorclubapp.presentation.loginscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horrorclubapp.domain.mode.Response
import com.example.horrorclubapp.domain.mode.User
import com.example.horrorclubapp.domain.repository.remote.SignInWithEmailAndPasswordFirebaseResponse
import com.example.horrorclubapp.domain.repository.remote.SignInWithGoogleFirebaseResponse
import com.example.horrorclubapp.domain.repository.remote.SignInWithGoogleResponse
import com.example.horrorclubapp.domain.usecase.user.UserUseCases
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {

    var signInWithGoogleResponse by mutableStateOf<SignInWithGoogleResponse>(Response.Success(null))
        private set

    var googleSignInClient by mutableStateOf<SignInWithGoogleFirebaseResponse>(Response.Success(null))
        private set

    var signInWithEmailAndPassword by mutableStateOf<SignInWithEmailAndPasswordFirebaseResponse>(Response.Success(null))
        private set

    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user

    fun googleAuthSecond() = viewModelScope.launch {
        googleSignInClient = Response.Loading
        googleSignInClient = userUseCases.signInWithGoogle.invoke()
    }

    fun signInWithGoogle(googleCredential: AuthCredential) = viewModelScope.launch {
        signInWithGoogleResponse = userUseCases.signInWithCredentialGoogle(googleCredential)
    }

    fun signInWithEmailAndPassword(
        email: String,
        password: String
    ) = viewModelScope.launch {
        signInWithEmailAndPassword = userUseCases.signInWithEmailAndPassword(
            email = email,
            password = password
        )
    }

}