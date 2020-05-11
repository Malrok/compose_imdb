package com.mrk.composeimdb.components

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.padding
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.mrk.composeimdb.effects.tmdbImageUrl
import com.mrk.composeimdb.models.Movie
import com.mrk.composeimdb.navigation.Screen
import com.mrk.composeimdb.navigation.navigateTo
import com.mrk.composeimdb.theme.titleStyle

@Composable
fun MovieCard(movie: Movie) {
    val url = tmdbImageUrl(movie)
    Clickable(onClick = {
        navigateTo(
            Screen.Detail(movie.id.toString())
        )
    }) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            ImageNetwork(
                modifier = Modifier.padding(end = 8.dp),
                url = url,
                height = 84.dp,
                width = 57.dp
            )
            Column {
                Text(
                    text = movie.title,
                    style = titleStyle
                )
                Text(
                    text = movie.release
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    val movie = Movie(
        0,
        "Interstellar",
        "Best movie evaaa biatch",
        "https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg",
        "10/02/2014",
        169,
        8.6,
        1405589,
        false
    )
    MovieCard(movie)
}