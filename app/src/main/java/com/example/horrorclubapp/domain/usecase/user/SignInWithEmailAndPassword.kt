package com.example.horrorclubapp.domain.usecase.user

import com.example.horrorclubapp.domain.repository.remote.AuthGoogleRepository
import com.example.horrorclubapp.domain.repository.remote.SignInWithEmailAndPasswordFirebaseResponse

class SignInWithEmailAndPassword(
    private val authGoogleRepository: AuthGoogleRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): SignInWithEmailAndPasswordFirebaseResponse {
        return authGoogleRepository.signInWithEmailAndPasswordFirebase(
            email = email,
            password = password
        )
    }
}
