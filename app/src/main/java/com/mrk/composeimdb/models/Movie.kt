package com.mrk.composeimdb.models

data class Movie(
    var id: Int,
    var title: String,
    var overview: String,
    var posterPath: String,
    var release: String,
    var runtime: Int,
    var voteAverage: Double,
    var voteCount: Int,
    var adult: Boolean
)