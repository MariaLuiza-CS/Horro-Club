package com.example.horrorclubapp.domain.usecase.user

import com.example.horrorclubapp.domain.mode.User
import com.example.horrorclubapp.domain.repository.local.UserRepository

class GetUser(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        id: String
    ): User? {
        return userRepository.getUser(
            id = id
        )
    }
}