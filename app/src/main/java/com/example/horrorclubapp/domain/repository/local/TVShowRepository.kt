package com.example.horrorclubapp.domain.repository.local

import com.example.horrorclubapp.domain.mode.TVShow

interface TVShowRepository {
    suspend fun insertTVShow(tvShow: TVShow)

    suspend fun getTVShow(): List<TVShow>?
}