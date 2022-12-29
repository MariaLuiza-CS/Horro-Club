package com.example.horrorclubapp.presentation.profilescreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horrorclubapp.R
import com.example.horrorclubapp.domain.mode.Response
import com.example.horrorclubapp.domain.mode.User
import com.example.horrorclubapp.domain.repository.remote.ProfileRepository
import com.example.horrorclubapp.domain.repository.remote.RevokeAccessResponse
import com.example.horrorclubapp.domain.repository.remote.SignOutResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    var signOutResponse by mutableStateOf<SignOutResponse>(Response.Success(false))
        private set

    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Response.Success(false))
        private set

    var user by mutableStateOf(User(
        id = "id",
        name = "name",
        email = "email"
    ))
        private set

    init {
        getAuthUser()
    }

    var charactersList by mutableStateOf(
        listOf(
            R.drawable.ic_zombie_girl,
            R.drawable.ic_zombie_boy,
            R.drawable.ic_glasses_person,
            R.drawable.ic_buss_person,
            R.drawable.ic_cut_person,
            R.drawable.ic_pink_person,
            R.drawable.ic_punk_person,
            R.drawable.ic_simple_person,
            R.drawable.ic_long_hair_person,
            R.drawable.ic_simple_glasses_person
        )
    )
        private set

    fun signOut() = viewModelScope.launch {
        signOutResponse = Response.Loading
        signOutResponse = profileRepository.signOut()
    }

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Response.Loading
        revokeAccessResponse = profileRepository.revokeAccess()
    }

    fun getAuthUser() = viewModelScope.launch {
        user = profileRepository.authUser()
    }
}
