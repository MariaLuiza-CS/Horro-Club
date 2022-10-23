package com.example.horrorclubapp.presentation.homescreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horrorclubapp.domain.mode.Response
import com.example.horrorclubapp.domain.repository.ProfileRepository
import com.example.horrorclubapp.domain.repository.RevokeAccessResponse
import com.example.horrorclubapp.domain.repository.SignOutResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    val displayName get() = profileRepository.displayName

    var signOutResponse by mutableStateOf<SignOutResponse>(Response.Success(false))
        private set

    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Response.Success(false))
        private set

    fun signOut() = viewModelScope.launch {
        signOutResponse = Response.Loading
        signOutResponse = profileRepository.signOut()
    }

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Response.Loading
        revokeAccessResponse = profileRepository.revokeAccess()
    }
}