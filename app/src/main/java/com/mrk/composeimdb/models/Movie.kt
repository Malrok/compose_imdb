package com.mrk.composeimdb.models

import com.google.gson.annotations.SerializedName

data class Movie(
    var id: Int,
    var title: String,
    var overview: String,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("release_date") var release: String,
    var runtime: Int,
    @SerializedName("vote_average") var voteAverage: Double,
    @SerializedName("vote_count") var voteCount: Int,
    var adult: Boolean
)