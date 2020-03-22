package com.mrk.composeimdb.ui.search

import android.util.Log
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Text
import androidx.ui.core.TextField
import androidx.ui.layout.Column
import androidx.ui.text.TextFieldValue
import com.mrk.composeimdb.models.Movie
import com.mrk.composeimdb.repositories.network.TmdbService
import com.mrk.composeimdb.ui.common.MovieCard
import com.mrk.composeimdb.ui.common.observe

@Composable
fun SearchMovie(tmdb: TmdbService) {
    val state = state { TextFieldValue("") }
    val movies: List<Movie> = fetchMovies(tmdb = tmdb, search = state.value.text)

    Column {
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
}

@Composable
fun fetchMovies(tmdb: TmdbService, search: String): List<Movie> {
    val apiResult = observe(tmdb.getMoviesListByTitle(search))
    return if (apiResult != null && apiResult.error == null) {
        apiResult.body?.results!!
    } else {
        if (apiResult != null) {
            Log.d("an error occurred", apiResult.error!!)
        }
        emptyList()
    }
}

