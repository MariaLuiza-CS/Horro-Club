package com.example.horrorclubapp.data.local.usedatasource

import androidx.room.*
import com.example.horrorclubapp.domain.mode.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM user WHERE id = :idUser")
    fun getUser(idUser: String): User?

}