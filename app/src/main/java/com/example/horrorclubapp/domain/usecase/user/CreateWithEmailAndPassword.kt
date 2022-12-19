package com.example.horrorclubapp.domain.usecase.user

import com.example.horrorclubapp.domain.repository.remote.AuthGoogleRepository
import com.example.horrorclubapp.domain.repository.remote.CreateUserWithEmailAndPasswordFirebaseResponse

class CreateWithEmailAndPassword(
    private val authGoogleRepository: AuthGoogleRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): CreateUserWithEmailAndPasswordFirebaseResponse {
        return authGoogleRepository.createUserWithEmailAndPasswordFirebase(
            email = email,
            password = password
        )
    }
}