package com.mrk.composeimdb.ui.detail

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.material.TopAppBar
import com.mrk.composeimdb.R
import com.mrk.composeimdb.models.Movie
import com.mrk.composeimdb.ui.Screen
import com.mrk.composeimdb.ui.ambients.TmdbServiceAmbient
import com.mrk.composeimdb.ui.common.VectorImageButton
import com.mrk.composeimdb.ui.common.observe
import com.mrk.composeimdb.ui.navigateTo
import me.alfredobejarano.retrofitadapters.data.ApiResult

@Composable
fun MovieDetail(movieId: String) {
    val tmdb = TmdbServiceAmbient.current
    val queryResult: ApiResult<Movie>? = observe(tmdb.getMovieById(movieId))

    if (queryResult?.body != null) {
        val movie = queryResult.body!!

        Column {
            TopAppBar(
                title = { Text(text = "Imdb") },
                navigationIcon = {
                    VectorImageButton(R.drawable.ic_back) {
                        navigateTo(Screen.RecentMovies)
                    }
                }
            )
            VerticalScroller {
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