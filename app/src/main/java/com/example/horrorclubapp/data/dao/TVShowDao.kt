package com.example.horrorclubapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.horrorclubapp.domain.mode.TVShow

@Dao
interface TVShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVShow(tvShow: TVShow)

    @Query("SELECT * FROM tv_show")
    fun getTVShow(): List<TVShow>?
}