package com.example.horrorclubapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.horrorclubapp.data.dao.MovieDao
import com.example.horrorclubapp.data.dao.TVShowDao
import com.example.horrorclubapp.data.dao.UserDao
import com.example.horrorclubapp.domain.mode.Movie
import com.example.horrorclubapp.domain.mode.TVShow
import com.example.horrorclubapp.domain.mode.User

@Database(
    entities = [
        User::class,
        Movie::class,
        TVShow::class
    ],
    version = 7,
    exportSchema = false
)
abstract class UserDataBase : RoomDatabase() {
    abstract val userDatabaseDao: UserDao
    abstract val movieDatabaseDao: MovieDao
    abstract val tvShowDao: TVShowDao

    companion object {
        const val DATABASE_NAME = "user_db"
    }
}