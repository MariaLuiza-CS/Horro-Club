package com.example.horrorclubapp.domain.mode

import com.google.gson.annotations.SerializedName

data class TVShowResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("original_name")
    var original_name: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("poster_path")
    var poster_path: String,
    @SerializedName("first_air_date")
    var first_air_date: String,
    @SerializedName("backdrop_path")
    var backdrop_path : String?,
    @SerializedName("genre_ids")
    val genre_ids: List<Int>
)
