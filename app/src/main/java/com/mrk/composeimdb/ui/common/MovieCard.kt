package com.mrk.composeimdb.ui.common

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import com.mrk.composeimdb.models.Movie
import com.mrk.composeimdb.ui.Screen
import com.mrk.composeimdb.ui.navigateTo

@Composable
fun MovieCard(movie: Movie) {
    Clickable(onClick = { navigateTo(Screen.Detail(movie.id.toString())) }) {
        Text(movie.title)
    }
}