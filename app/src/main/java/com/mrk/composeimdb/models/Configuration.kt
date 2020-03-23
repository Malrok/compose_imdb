package com.mrk.composeimdb.models

import com.google.gson.annotations.SerializedName

data class Configuration(
    var images: Images
)

data class Images(
    @SerializedName("secure_base_url") var imageBaseUrl: String,
    @SerializedName("poster_sizes") var posterSizes: List<String>
)