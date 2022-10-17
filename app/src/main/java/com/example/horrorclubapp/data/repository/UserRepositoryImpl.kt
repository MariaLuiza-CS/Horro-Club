package com.example.horrorclubapp.data.repository

import com.example.horrorclubapp.data.local.usedatasource.UserDao
import com.example.horrorclubapp.domain.mode.User
import com.example.horrorclubapp.domain.repository.UserRepository

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun getUser(id: String): User? {
        return userDao.getUser(id)
    }

    override suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }
}