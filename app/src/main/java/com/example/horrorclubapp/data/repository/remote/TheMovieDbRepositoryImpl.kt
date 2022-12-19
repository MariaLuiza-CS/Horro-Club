package com.example.horrorclubapp.data.repository.remote

import com.example.horrorclubapp.domain.mode.MovieListResponse
import com.example.horrorclubapp.domain.mode.TVShowListResponse
import com.example.horrorclubapp.domain.repository.remote.TheMovieDbRepository
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieDbRepositoryImpl() : TheMovieDbRepository {

    private val api = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(TheMovieDbRepository::class.java)

    override suspend fun getMovieList(api_key: String, page: Int): Response<MovieListResponse> {
        return api.getMovieList(api_key, page)
    }

    override suspend fun getTVShowList(api_key: String, page: Int): Response<TVShowListResponse> {
        return api.getTVShowList(api_key, page)
    }
}
