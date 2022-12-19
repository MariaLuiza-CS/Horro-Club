package com.example.horrorclubapp.domain.repository.local

import com.example.horrorclubapp.domain.mode.User

interface UserRepository {
    suspend fun getUser(id: String): User?

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user:User)
}
