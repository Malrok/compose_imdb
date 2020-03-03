package com.mrk.composeimdb.ui.search

import android.util.Log
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.TextField
import androidx.ui.layout.Column
import androidx.ui.text.TextFieldValue
import com.mrk.composeimdb.models.Movie
import com.mrk.composeimdb.models.TmdbListResult
import com.mrk.composeimdb.repositories.network.TmdbService
import com.mrk.composeimdb.ui.common.MovieCard
import com.mrk.composeimdb.ui.common.observe
import me.alfredobejarano.retrofitadapters.data.ApiResult

@Composable
fun SearchMovie(tmdb: TmdbService) {
    val state = state { TextFieldValue("") }
    var movies: List<Movie> = emptyList()

    Column {
        TextField(
            value = state.value,
            onValueChange = {
                state.value = it
                var apiResult: ApiResult<TmdbListResult>? = null
                observe({tmdb.getMoviesListByTitle(it.toString())}) {result ->
                    apiResult = result
                }
                if (apiResult?.error != null) {
                    movies = apiResult?.body?.results!!
                } else {
                    Log.d("an error occurred", apiResult?.error!!)
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

