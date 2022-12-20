package com.example.horrorclubapp.domain.usecase.movie

import com.example.horrorclubapp.domain.mode.Movie
import com.example.horrorclubapp.domain.mode.MovieResponse
import com.example.horrorclubapp.domain.repository.local.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovies(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): Flow<List<Movie>?> {
        return movieRepository.getMovies()
    }
}