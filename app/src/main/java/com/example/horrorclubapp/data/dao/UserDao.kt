package com.example.horrorclubapp.data.dao

import androidx.room.*
import com.example.horrorclubapp.domain.mode.User

/**
 * This is the Dao interface.
 *
 * Responsible to make the calls with the database.
 *
 * @author maria_luiza
 */
@Dao
interface UserDao {

    /**
     * This is the insert method.
     *
     * Responsible for add a new user on database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    /**
     * This is the getUser method.
     *
     * @return An [User] from the database if the [idUser] matches with some id on database.
     * @param idUser It is the id to match with some id on database.
     */
    @Query("SELECT * FROM user WHERE id = :idUser")
    fun getUser(idUser: String): User?

    @Delete
    fun deleteUser(user: User)
}