package com.example.horrorclubapp.domain.usecase.user

import com.example.horrorclubapp.domain.mode.User
import com.example.horrorclubapp.domain.repository.local.UserRepository

class InsertUser(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        user: User
    ) {
        userRepository.insertUser(
            user = user
        )
    }
}