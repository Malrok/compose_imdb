package com.mrk.composeimdb.ui.recent

import android.text.format.DateFormat
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.material.TopAppBar
import com.mrk.composeimdb.R
import com.mrk.composeimdb.repositories.network.TmdbService
import com.mrk.composeimdb.ui.Screen
import com.mrk.composeimdb.ui.VectorImageButton
import com.mrk.composeimdb.ui.common.MovieCard
import com.mrk.composeimdb.ui.common.observe
import com.mrk.composeimdb.ui.navigateTo
import java.util.*

@Composable
fun RecentMovies(tmdb: TmdbService) {
    val now = Calendar.getInstance()
    val before = Calendar.getInstance()
    before.add(Calendar.DAY_OF_MONTH, -30)
    val maxDate = DateFormat.format("yyyy-MM-dd", now).toString()
    val minDate = DateFormat.format("yyyy-MM-dd", before).toString()

    val movies = observe(tmdb.getRecentMovies(minDate, maxDate))

    Column {
        TopAppBar(
            title = { Text(text = "Imdb") },
            actions = {
                VectorImageButton(R.drawable.ic_search) {
                    navigateTo(Screen.SearchMovie)
                }
            }
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