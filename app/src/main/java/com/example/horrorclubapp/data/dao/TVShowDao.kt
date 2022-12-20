package com.example.horrorclubapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.horrorclubapp.domain.mode.TVShow
import kotlinx.coroutines.flow.Flow

@Dao
interface TVShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVShow(tvShow: TVShow)

    @Query("SELECT * FROM tv_show ORDER BY id DESC")
    fun getTVShow(): Flow<List<TVShow>?>
}