package com.mrk.composeimdb.pages.search

import android.util.Log
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.foundation.Text
import androidx.ui.foundation.TextField
import androidx.ui.foundation.TextFieldValue
import com.mrk.composeimdb.ambients.TmdbServiceAmbient
import com.mrk.composeimdb.components.MovieCard
import com.mrk.composeimdb.effects.observe
import com.mrk.composeimdb.models.Movie
import com.mrk.composeimdb.repositories.TmdbService

@Composable
fun SearchMovie() {
    val tmdb = TmdbServiceAmbient.current
    val state = state { TextFieldValue("") }
    val movies: List<Movie> = fetchMovies(tmdb = tmdb, search = state.value.text)

    TextField(
        value = state.value,
        onValueChange = {
            state.value = it
        }
    )
    if (movies.isNotEmpty()) {
        movies.forEach { movie ->
            MovieCard(movie)
        }
    } else {
        Text(text = "No movies found")
    }
}

@Composable
fun fetchMovies(tmdb: TmdbService, search: String): List<Movie> {
    val apiResult =
        observe(tmdb.getMoviesListByTitle(search))
    return if (apiResult != null && apiResult.error == null) {
        apiResult.body?.results!!
    } else {
        if (apiResult != null) {
            Log.d("an error occurred", apiResult.error!!)
        }
        emptyList()
    }
}

