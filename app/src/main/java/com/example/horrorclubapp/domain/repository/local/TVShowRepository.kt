package com.example.horrorclubapp.domain.repository.local

import com.example.horrorclubapp.domain.mode.TVShow
import kotlinx.coroutines.flow.Flow

interface TVShowRepository {
    suspend fun insertTVShow(tvShow: TVShow)

    suspend fun getTVShow(): Flow<List<TVShow>?>
}