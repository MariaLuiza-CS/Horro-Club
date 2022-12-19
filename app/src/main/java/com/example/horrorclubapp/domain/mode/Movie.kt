package com.example.horrorclubapp.domain.mode

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int,
    @ColumnInfo(name = "title")
    var title : String?,
    @ColumnInfo(name = "overview")
    var overview : String?,
    @ColumnInfo(name = "poster_path")
    var poster_path : String?,
    @ColumnInfo(name = "release_date")
    var release_date : String?,
    @ColumnInfo(name = "backdrop_path")
    var backdrop_path : String?,
    @ColumnInfo(name = "genre_ids")
    val genre_ids: Int
)
