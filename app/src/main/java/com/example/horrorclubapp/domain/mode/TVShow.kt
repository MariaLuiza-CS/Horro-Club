package com.example.horrorclubapp.domain.mode

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tv_show")
data class TVShow(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id : Int?,
    @ColumnInfo(name = "original_name")
    var original_name : String?,
    @ColumnInfo(name = "overview")
    var overview : String?,
    @ColumnInfo(name = "poster_path")
    var poster_path : String?,
    @ColumnInfo(name = "first_air_date")
    var first_air_date : String?,
    @ColumnInfo(name = "backdrop_path")
    var backdrop_path : String?,
    @ColumnInfo(name = "genre_ids")
    val genre_ids: Int
)
