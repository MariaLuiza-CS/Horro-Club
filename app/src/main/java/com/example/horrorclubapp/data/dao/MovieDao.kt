package com.example.horrorclubapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.horrorclubapp.domain.mode.Movie
import com.example.horrorclubapp.domain.mode.MovieResponse

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movie")
    fun getMovies(): List<Movie>?
}
