package com.example.horrorclubapp.domain.mode

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("title")
    var title: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("poster_path")
    var poster_path: String,
    @SerializedName("release_date")
    var release_date: String,
    @SerializedName("backdrop_path")
    var backdrop_path : String?,
    @SerializedName("genre_ids")
    val genre_ids: List<Int>
)
