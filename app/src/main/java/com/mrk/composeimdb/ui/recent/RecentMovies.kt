package com.mrk.composeimdb.ui.recent

import android.text.format.DateFormat
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.material.TopAppBar
import com.mrk.composeimdb.models.TmdbListResult
import com.mrk.composeimdb.repositories.network.TmdbService
import com.mrk.composeimdb.ui.common.MovieCard
import com.mrk.composeimdb.ui.common.observe
import me.alfredobejarano.retrofitadapters.data.ApiResult
import java.util.*

@Composable
fun RecentMovies(tmdb: TmdbService) {
    val now = Calendar.getInstance()
    val before = Calendar.getInstance()
    before.add(Calendar.DAY_OF_MONTH, -30)
    val maxDate = DateFormat.format("yyyy-MM-dd", now).toString()
    val minDate = DateFormat.format("yyyy-MM-dd", before).toString()

    var movies: ApiResult<TmdbListResult>? = null

    observe({tmdb.getRecentMovies(minDate, maxDate)}) {
        movies = it
    }

    Column {
        TopAppBar(
            title = { Text(text = "Imdb") }
//            actionData = listOf(
//                R.drawable.ic_search
//            )
//            action = {
//                navigateTo(Screen.SearchMovie)
//            }
//            action = {
//                AppBarIcon(icon = imageResource(id = it)) {
//                    // Handle action click
//                }
//            }
        )
        VerticalScroller {
            Column {

                movies?.body?.results?.forEach { movie ->
                    MovieCard(movie)
                }
            }
        }
    }
}