package com.example.horrorclubapp.domain.usecase.tvshow

import com.example.horrorclubapp.domain.mode.TVShow
import com.example.horrorclubapp.domain.repository.local.TVShowRepository
import kotlinx.coroutines.flow.Flow

class GetTVShow(
    private val tvShowRepository: TVShowRepository
) {
    suspend operator fun invoke(): Flow<List<TVShow>?> {
        return tvShowRepository.getTVShow()
    }
}
