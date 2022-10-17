package com.example.horrorclubapp.presentation.loginscreen

import androidx.lifecycle.ViewModel
import com.example.horrorclubapp.domain.mode.User
import com.example.horrorclubapp.domain.usecase.UserUseCases
import com.example.horrorclubapp.presentation.utils.RealDatabase
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user

    suspend fun insertUser(account: GoogleSignInAccount?) {
        account?.id?.let {
            User(
                id = it,
                name = account.displayName,
                account.email
            )
        }?.let {
            insertUserToLocalDatabase(it)
            insertUserToRemoteDatabase(it)
        }
    }

    private fun insertUserToRemoteDatabase(user: User) {
        RealDatabase().insertNewUser(user)
    }

    private suspend fun insertUserToLocalDatabase(user: User) {
        userUseCases.insertUser.invoke(
            user
        )
    }

}