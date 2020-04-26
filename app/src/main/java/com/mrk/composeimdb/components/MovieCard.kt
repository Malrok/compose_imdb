package com.mrk.composeimdb.components

import androidx.compose.Composable
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Text
import androidx.ui.material.ListItem
import androidx.ui.unit.dp
import com.mrk.composeimdb.effects.tmdbImageUrl
import com.mrk.composeimdb.models.Movie
import com.mrk.composeimdb.navigation.Screen
import com.mrk.composeimdb.navigation.navigateTo

@Composable
fun MovieCard(movie: Movie) {
    val url = tmdbImageUrl(movie)
    Clickable(onClick = {
        navigateTo(
            Screen.Detail(movie.id.toString())
        )
    }) {
        ListItem(
            icon = {
                ImageNetwork(url = url, height = 64.dp, width = 64.dp)
            },
            text = {
                Text(movie.title)
            }
        )
    }
}