package com.mrk.composeimdb.ui.list

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.material.TopAppBar
import com.mrk.composeimdb.repositories.network.TmdbService
import com.mrk.composeimdb.ui.Screen
import com.mrk.composeimdb.ui.common.observe
import com.mrk.composeimdb.ui.navigateTo

@Composable
fun MoviesList(tmdb: TmdbService) {
    val movies = +observe(tmdb.getRecentMovies("2019-12-01", "2020-01-13"))

    Column {
        TopAppBar(
            title = { Text(text = "Imdb") }
        )
        VerticalScroller(modifier = Flexible(1f)) {
            Column {

                movies?.body?.results?.forEach { movie ->
                    Clickable(onClick = { navigateTo(Screen.Detail(movie.id.toString())) }) {
                        Text(movie.title)
                    }
                }
            }
        }
    }
}