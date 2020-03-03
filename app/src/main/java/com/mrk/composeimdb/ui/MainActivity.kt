package com.mrk.composeimdb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import com.mrk.composeimdb.repositories.network.TmdbClient
import com.mrk.composeimdb.repositories.network.TmdbService
import com.mrk.composeimdb.ui.detail.MovieDetail
import com.mrk.composeimdb.ui.recent.RecentMovies
import com.mrk.composeimdb.ui.search.SearchMovie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tmdb = TmdbClient().getTmdbService()

        setContent {
            MaterialTheme {
                Content(tmdb = tmdb)
            }
        }
    }
}

@Composable
fun Content(tmdb: TmdbService) {
    MaterialTheme(
        colors = lightThemeColors
    ) {
        Crossfade(ImdbStatus.currentScreen) { screen ->
            Surface(color = (MaterialTheme.colors()).background) {
                when (screen) {
                    is Screen.RecentMovies -> RecentMovies(tmdb)
                    is Screen.SearchMovie -> SearchMovie(tmdb)
                    is Screen.Detail -> MovieDetail(tmdb, screen.movieId)
                }
            }
        }
    }
}
