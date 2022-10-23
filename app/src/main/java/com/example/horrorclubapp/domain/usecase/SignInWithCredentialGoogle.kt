package com.example.horrorclubapp.domain.usecase

import com.example.horrorclubapp.domain.repository.AuthGoogleRepository
import com.example.horrorclubapp.domain.repository.SignInWithGoogleResponse
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