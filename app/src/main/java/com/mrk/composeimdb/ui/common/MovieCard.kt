package com.mrk.composeimdb.ui.common

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.unit.Dp
import com.mrk.composeimdb.models.Movie
import com.mrk.composeimdb.navigation.Screen
import com.mrk.composeimdb.navigation.navigateTo

@Composable
fun MovieCard(movie: Movie) {
    Clickable(onClick = {
        navigateTo(
            Screen.Detail(movie.id.toString())
        )
    }) {
        ImageNetwork(url = movie.posterPath, height = Dp(64f), width = Dp(64f))
        Text(movie.title)
    }
}