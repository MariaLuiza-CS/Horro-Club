package com.example.horrorclubapp.data.local.usedatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.horrorclubapp.domain.mode.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDataBase : RoomDatabase() {
    abstract val userDatabaseDao: UserDao

    companion object {
        const val DATABASE_NAME = "user_db"
    }
}