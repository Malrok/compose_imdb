package com.mrk.composeimdb.effects

import androidx.compose.Composable
import com.mrk.composeimdb.ambients.TmdbConfigurationAmbient
import com.mrk.composeimdb.models.Movie

@Composable
fun tmdbImageUrl(movie: Movie): String {
    val config = TmdbConfigurationAmbient.current

    return "${config.images.imageBaseUrl}${config.images.posterSizes[0]}${movie.posterPath}"
}