package com.example.horrorclubapp.domain.usecase.tmbd

import com.example.horrorclubapp.domain.mode.MovieListResponse
import com.example.horrorclubapp.domain.repository.remote.TheMovieDbRepository
import retrofit2.Response

class GetMovieList(
    private val theMovieDbRepository: TheMovieDbRepository
) {
    suspend operator fun invoke(
        api_key: String,
        page: Int
    ): Response<MovieListResponse> {
        return theMovieDbRepository.getMovieList(api_key, page)
    }
}