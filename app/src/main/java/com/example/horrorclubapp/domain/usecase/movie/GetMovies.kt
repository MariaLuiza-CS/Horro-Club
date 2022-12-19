package com.example.horrorclubapp.domain.usecase.movie

import com.example.horrorclubapp.domain.mode.Movie
import com.example.horrorclubapp.domain.mode.MovieResponse
import com.example.horrorclubapp.domain.repository.local.MovieRepository

class GetMovies(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): List<Movie>? {
        return movieRepository.getMovies()
    }
}