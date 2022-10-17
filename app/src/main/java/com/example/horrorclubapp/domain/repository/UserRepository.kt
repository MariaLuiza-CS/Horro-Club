package com.example.horrorclubapp.domain.repository

import com.example.horrorclubapp.domain.mode.User

interface UserRepository {

    suspend fun getUser(id: String): User?

    suspend fun insertUser(user: User)
}