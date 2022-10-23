package com.example.horrorclubapp.domain.usecase

import com.example.horrorclubapp.domain.repository.AuthGoogleRepository
import com.example.horrorclubapp.domain.repository.SignInWithGoogleFirebaseResponse

class SignInWithGoogle(
    private val authGoogleRepository: AuthGoogleRepository
) {
    suspend operator fun invoke(): SignInWithGoogleFirebaseResponse {
        return authGoogleRepository.signInWithGoogleFirebaseResponse()
    }
}