package com.example.horrorclubapp.domain.usecase.user

import com.example.horrorclubapp.domain.repository.remote.AuthGoogleRepository
import com.example.horrorclubapp.domain.repository.remote.SignInWithGoogleResponse
import com.google.firebase.auth.AuthCredential

class SignInWithCredentialGoogle(
    private val authGoogleRepository: AuthGoogleRepository,
) {
    suspend operator fun invoke(googleCredential: AuthCredential): SignInWithGoogleResponse {
        return authGoogleRepository.firebaseSignInWithGoogle(
            googleCredential = googleCredential
        )
    }
}
