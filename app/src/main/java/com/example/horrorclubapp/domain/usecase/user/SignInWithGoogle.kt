package com.example.horrorclubapp.domain.usecase.user

import com.example.horrorclubapp.domain.repository.remote.AuthGoogleRepository
import com.example.horrorclubapp.domain.repository.remote.SignInWithGoogleFirebaseResponse


class SignInWithGoogle(
    private val authGoogleRepository: AuthGoogleRepository
) {
    suspend operator fun invoke(): SignInWithGoogleFirebaseResponse {
        return authGoogleRepository.signInWithGoogleFirebaseResponse()
    }
}
