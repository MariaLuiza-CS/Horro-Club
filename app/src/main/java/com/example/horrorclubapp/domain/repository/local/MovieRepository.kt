package com.example.horrorclubapp.domain.repository.local

import com.example.horrorclubapp.domain.mode.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun insertMovie(movie: Movie)

    suspend fun getMovies(): Flow<List<Movie>?>
}