package com.example.horrorclubapp.data.repository.local

import com.example.horrorclubapp.data.dao.TVShowDao
import com.example.horrorclubapp.domain.mode.TVShow
import com.example.horrorclubapp.domain.repository.local.TVShowRepository
import kotlinx.coroutines.flow.Flow

class TVShowRepositoryImpl(private val tvShowDao: TVShowDao) : TVShowRepository {
    override suspend fun insertTVShow(tvShow: TVShow) {
        tvShowDao.insertTVShow(tvShow)
    }

    override suspend fun getTVShow(): Flow<List<TVShow>?> {
        return tvShowDao.getTVShow()
    }
}
