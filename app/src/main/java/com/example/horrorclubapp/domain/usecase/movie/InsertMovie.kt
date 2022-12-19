package com.example.horrorclubapp.domain.usecase.movie

import com.example.horrorclubapp.domain.mode.Movie
import com.example.horrorclubapp.domain.mode.MovieResponse
import com.example.horrorclubapp.domain.repository.local.MovieRepository

class InsertMovie(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(
        movie: Movie
    ) {
        movieRepository.insertMovie(
            movie = movie
        )
    }
}