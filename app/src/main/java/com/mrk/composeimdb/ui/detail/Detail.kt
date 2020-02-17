package com.mrk.composeimdb.ui.detail

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.material.TopAppBar
import com.mrk.composeimdb.R
import com.mrk.composeimdb.repositories.network.TmdbService
import com.mrk.composeimdb.ui.Screen
import com.mrk.composeimdb.ui.VectorImageButton
import com.mrk.composeimdb.ui.common.observe
import com.mrk.composeimdb.ui.navigateTo

@Composable
fun MovieDetail(tmdb: TmdbService, movieId: String) {

    val queryResult = +observe(tmdb.getMovieById(movieId))
    if (queryResult?.body != null) {
        val movie = queryResult.body!!

        Column {
            TopAppBar(
                title = { Text(text = "Imdb") },
                        navigationIcon = {
                    VectorImageButton(R.drawable.ic_back) {
                        navigateTo(Screen.MoviesList)
                    }
                }
            )
            VerticalScroller(modifier = Flexible(1f)) {
                Column {
                    Text(movie.title)
                    Text(movie.overview)
                }
            }
        }
    } else {
        Text("Movie not found")
    }
}