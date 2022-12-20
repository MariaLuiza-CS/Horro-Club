package com.example.horrorclubapp.data.repository.local

import com.example.horrorclubapp.data.dao.MovieDao
import com.example.horrorclubapp.domain.mode.Movie
import com.example.horrorclubapp.domain.repository.local.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(private val movieDao: MovieDao) : MovieRepository {
    override suspend fun insertMovie(movie: Movie) {
        movieDao.insertMovie(movie = movie)
    }

    override suspend fun getMovies(): Flow<List<Movie>?> {
        return movieDao.getMovies()
    }
}