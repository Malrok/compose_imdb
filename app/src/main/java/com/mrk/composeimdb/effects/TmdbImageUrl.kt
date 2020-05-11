package com.mrk.composeimdb.effects

import androidx.compose.Composable
import com.mrk.composeimdb.ambients.ViewModelAmbient
import com.mrk.composeimdb.models.Movie

@Composable
fun tmdbImageUrl(movie: Movie): String {
    val config = ViewModelAmbient.current.getConfig().value?.body!!

    return "${config.images.imageBaseUrl}original${movie.posterPath}"
}