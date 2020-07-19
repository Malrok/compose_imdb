package com.mrk.composeimdb.pages.search

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.lazy.LazyColumnItems
import androidx.ui.input.TextFieldValue
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.livedata.observeAsState
import androidx.ui.material.FilledTextField
import androidx.ui.res.stringResource
import androidx.ui.unit.dp
import com.mrk.composeimdb.R
import com.mrk.composeimdb.ambients.ViewModelAmbient
import com.mrk.composeimdb.components.MovieCard
import com.mrk.composeimdb.models.Movie
import com.mrk.composeimdb.viewmodels.MainViewModel
import timber.log.Timber

@Composable
fun SearchMovie() {
    val viewModel = ViewModelAmbient.current
    var state by state { TextFieldValue("") }
    val movies: List<Movie> = fetchMovies(viewModel = viewModel, search = state.text)

    Column {
        FilledTextField(
            label = { Text(stringResource(id = R.string.search_hint )) },
            modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth(),
            value = state,
            onValueChange = {
                state = it
            }
        )
        if (movies.isNotEmpty()) {
            LazyColumnItems(items = movies, itemContent = { movie ->
                MovieCard(movie)
            })
        } else {
            Text(text = "No movies found")
        }
    }
}

@Composable
fun fetchMovies(viewModel: MainViewModel, search: String): List<Movie> {
    return if (search.isNotEmpty()) {
        val apiResult = viewModel.getMoviesListByTitle(search).observeAsState().value
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

