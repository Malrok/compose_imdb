package com.mrk.composeimdb.ui.recent

import android.text.format.DateFormat
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.AdapterList
import androidx.ui.layout.Column
import androidx.ui.material.TopAppBar
import com.mrk.composeimdb.R
import com.mrk.composeimdb.ui.Screen
import com.mrk.composeimdb.ui.common.VectorImageButton
import com.mrk.composeimdb.ui.common.MovieCard
import com.mrk.composeimdb.ui.ambients.TmdbServiceAmbient
import com.mrk.composeimdb.ui.common.observe
import com.mrk.composeimdb.ui.navigateTo
import java.util.*

@Composable
fun RecentMovies() {
    val tmdb = TmdbServiceAmbient.current
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
        if (movies != null) {
            AdapterList(data = movies.body?.results!!) {
                MovieCard(movie = it)
            }
        } else {
            Text(text = "Loading")
        }
    }
}