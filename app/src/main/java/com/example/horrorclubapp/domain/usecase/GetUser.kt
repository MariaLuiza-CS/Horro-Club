package com.example.horrorclubapp.domain.usecase

import com.example.horrorclubapp.domain.mode.User
import com.example.horrorclubapp.domain.repository.UserRepository

class GetUser(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id: String): User? {
        return userRepository.getUser(id)
    }
}