package com.mrk.composeimdb.pages.recent

import android.text.format.DateFormat
import androidx.compose.Composable
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Text
import com.mrk.composeimdb.ambients.TmdbServiceAmbient
import com.mrk.composeimdb.components.MovieCard
import com.mrk.composeimdb.effects.observe
import java.util.*

@Composable
fun RecentMovies() {
    val tmdb = TmdbServiceAmbient.current
    val now = Calendar.getInstance()
    val before = Calendar.getInstance()
    before.add(Calendar.DAY_OF_MONTH, -30)
    val maxDate = DateFormat.format("yyyy-MM-dd", now).toString()
    val minDate = DateFormat.format("yyyy-MM-dd", before).toString()

    val movies = observe(
        tmdb.getRecentMovies(
            minDate,
            maxDate
        )
    )

    if (movies != null) {
        AdapterList(data = movies.body?.results!!) {
            MovieCard(movie = it)
        }
    } else {
        Text(text = "Loading")
    }
}