package com.example.horrorclubapp.domain.usecase.tmbd

import com.example.horrorclubapp.domain.mode.TVShowListResponse
import com.example.horrorclubapp.domain.repository.remote.TheMovieDbRepository
import retrofit2.Response

class GetTVShowList(
    private val theMovieDbRepository: TheMovieDbRepository
) {
    suspend operator fun invoke(
        api_key: String,
        page: Int
    ): Response<TVShowListResponse> {
        return theMovieDbRepository.getTVShowList(api_key, page)
    }
}
