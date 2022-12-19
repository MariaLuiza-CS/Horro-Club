package com.example.horrorclubapp.domain.repository.remote

import com.example.horrorclubapp.domain.mode.MovieListResponse
import com.example.horrorclubapp.domain.mode.TVShowListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbRepository {
    @GET("movie/popular?")
    suspend fun getMovieList(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<MovieListResponse>

    @GET("tv/popular?")
    suspend fun getTVShowList(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<TVShowListResponse>
}
