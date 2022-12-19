package com.example.horrorclubapp.domain.repository.local

import com.example.horrorclubapp.domain.mode.Movie

interface MovieRepository {
    suspend fun insertMovie(movie: Movie)

    suspend fun getMovies(): List<Movie>?
}