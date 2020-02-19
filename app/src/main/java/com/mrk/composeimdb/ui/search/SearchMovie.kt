package com.mrk.composeimdb.ui.search

import android.util.Log
import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.EditorModel
import androidx.ui.core.TextField
import androidx.ui.layout.Column
import com.mrk.composeimdb.models.Movie
import com.mrk.composeimdb.repositories.network.TmdbService
import com.mrk.composeimdb.ui.common.MovieCard
import com.mrk.composeimdb.ui.common.observe

@Composable
fun SearchMovie(tmdb: TmdbService) {
    val state = +state { EditorModel("") }
    var movies: List<Movie> = emptyList()

    Column {
        TextField(
            value = state.value,
            onValueChange = {
                state.value = it
                val result = +observe(tmdb.getMoviesListByTitle(it.toString()))
                if (result?.error != null) {
                    movies = result.body?.results!!
                } else {
                    Log.d("an error occurred", result?.error!!)
                }
            }
        )
        if (movies.isNotEmpty()) {
            movies.forEach { movie ->
                MovieCard(movie)
            }
        }
    }
}

