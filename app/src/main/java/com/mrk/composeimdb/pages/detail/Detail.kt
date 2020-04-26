package com.mrk.composeimdb.pages.detail

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.res.stringResource
import androidx.ui.unit.dp
import com.mrk.composeimdb.R
import com.mrk.composeimdb.ambients.TmdbServiceAmbient
import com.mrk.composeimdb.components.IconButton
import com.mrk.composeimdb.components.ImageNetwork
import com.mrk.composeimdb.effects.observe
import com.mrk.composeimdb.effects.tmdbImageUrl
import com.mrk.composeimdb.models.Movie
import com.mrk.composeimdb.navigation.Screen
import com.mrk.composeimdb.navigation.navigateTo
import me.alfredobejarano.retrofitadapters.data.ApiResult

@Composable
fun MovieDetail(movieId: String) {
    val tmdb = TmdbServiceAmbient.current
    val queryResult: ApiResult<Movie>? = observe(tmdb.getMovieById(movieId))

    Scaffold(
        topAppBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                navigationIcon = {
                    IconButton(
                        id = R.drawable.ic_back
                    ) {
                        navigateTo(
                            Screen.Home
                        )
                    }
                }
            )
        },
        bodyContent = {
            if (queryResult?.body != null) {
                val movie = queryResult.body!!
                val url = tmdbImageUrl(movie)
                VerticalScroller {
                    Column {
                        ImageNetwork(url = url, width = 64.dp, height = 64.dp)
                        Text(movie.title)
                        Text(movie.overview)
                    }
                }
            } else {
                Text("Movie not found")
            }
        }
    )

}