package com.mrk.composeimdb.pages.search

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
import com.mrk.composeimdb.ambients.ViewModelAmbient
import com.mrk.composeimdb.components.MovieCard
import com.mrk.composeimdb.effects.observe
import com.mrk.composeimdb.models.Movie
import com.mrk.composeimdb.viewmodels.MainViewModel
import timber.log.Timber

@Composable
fun SearchMovie() {
    val viewModel = ViewModelAmbient.current
    var state by state { TextFieldValue("") }
    val movies: List<Movie> = fetchMovies(viewModel = viewModel, search = state.text)

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
fun fetchMovies(viewModel: MainViewModel, search: String): List<Movie> {
    return if (search.isNotEmpty()) {
        val apiResult =
            observe(viewModel.getMoviesListByTitle(search))
        if (apiResult != null && apiResult.error == null) {
            apiResult.body?.results!!
        } else {
            if (apiResult != null) {
                Timber.d("an error occurred ${apiResult.error!!}")
            }
            emptyList()
        }
    } else {
        emptyList()
    }
}

