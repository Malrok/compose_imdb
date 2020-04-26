package com.mrk.composeimdb.pages.search

import android.util.Log
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Surface
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import com.mrk.composeimdb.ambients.TmdbServiceAmbient
import com.mrk.composeimdb.components.MovieCard
import com.mrk.composeimdb.effects.observe
import com.mrk.composeimdb.models.Movie
import com.mrk.composeimdb.repositories.TmdbService

@Composable
fun SearchMovie() {
    val tmdb = TmdbServiceAmbient.current
    var state by state { TextFieldValue("") }
    val movies: List<Movie> = fetchMovies(tmdb = tmdb, search = state.text)

    Column {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            border = Border(size = Dp.Hairline, color = Color.LightGray)
        ) {
            TextField(
                modifier = Modifier.padding(16.dp),
                value = state,
                onValueChange = {
                    state = it
                }
            )
        }
        if (movies.isNotEmpty()) {
            AdapterList(data = movies) { movie ->
                MovieCard(movie)
            }
        } else {
            Text(text = "No movies found")
        }
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

