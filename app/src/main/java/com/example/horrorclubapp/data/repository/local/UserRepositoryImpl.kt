package com.example.horrorclubapp.data.repository.local

import com.example.horrorclubapp.data.dao.UserDao
import com.example.horrorclubapp.domain.mode.User
import com.example.horrorclubapp.domain.repository.local.UserRepository

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun getUser(id: String): User? {
        return userDao.getUser(id)
    }

    override suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}