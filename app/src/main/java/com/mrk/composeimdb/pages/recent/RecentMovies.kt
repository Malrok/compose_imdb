package com.mrk.composeimdb.pages.recent

import android.text.format.DateFormat
import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.lazy.LazyColumnItems
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.wrapContentSize
import androidx.ui.livedata.observeAsState
import androidx.ui.material.CircularProgressIndicator
import com.mrk.composeimdb.ambients.ViewModelAmbient
import com.mrk.composeimdb.components.MovieCard
import java.util.*

@Composable
fun RecentMovies() {
    val viewModel = ViewModelAmbient.current
    val now = Calendar.getInstance()
    val before = Calendar.getInstance()
    before.add(Calendar.DAY_OF_MONTH, -30)
    val maxDate = DateFormat.format("yyyy-MM-dd", now).toString()
    val minDate = DateFormat.format("yyyy-MM-dd", before).toString()

    val movies = viewModel.getRecentMovies(
            minDate,
            maxDate
        ).observeAsState().value

    if (movies != null) {
        LazyColumnItems(items = movies.body?.results!!, itemContent = {
            MovieCard(movie = it)
        })
    } else {
        Box(
            modifier = Modifier.fillMaxSize() + Modifier.wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator()
        }
    }
}