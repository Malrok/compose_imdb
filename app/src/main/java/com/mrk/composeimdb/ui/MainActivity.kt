package com.mrk.composeimdb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Providers
import androidx.ui.animation.Crossfade
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.Center
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import com.mrk.composeimdb.repositories.TmdbClient
import com.mrk.composeimdb.repositories.TmdbService
import com.mrk.composeimdb.theme.lightThemeColors
import com.mrk.composeimdb.ambients.TmdbConfigurationAmbient
import com.mrk.composeimdb.ambients.TmdbServiceAmbient
import com.mrk.composeimdb.effects.observe
import com.mrk.composeimdb.ui.detail.MovieDetail
import com.mrk.composeimdb.navigation.Navigation
import com.mrk.composeimdb.navigation.Screen
import com.mrk.composeimdb.ui.recent.RecentMovies
import com.mrk.composeimdb.ui.search.SearchMovie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tmdb = TmdbClient().getTmdbService()

        setContent {
            Content(tmdb = tmdb)
        }
    }
}

@Composable
fun Content(tmdb: TmdbService) {
    val configurationResult =
        observe(tmdb.getConfig())

    MaterialTheme(
        colors = lightThemeColors
    ) {
        if (configurationResult != null && configurationResult.error == null && configurationResult.body != null) {
            Providers(
                TmdbServiceAmbient provides tmdb,
                TmdbConfigurationAmbient provides configurationResult.body!!
            ) {
                Main()
            }
        } else {
            Splashscreen()
        }
    }
}

@Composable
fun Splashscreen() {
    Center {
        Text(text = "loading")
    }
}

@Composable
fun Main() {
    Crossfade(Navigation.currentScreen) { screen ->
        Surface(color = (MaterialTheme.colors()).background) {
            when (screen) {
                is Screen.RecentMovies -> RecentMovies()
                is Screen.SearchMovie -> SearchMovie()
                is Screen.Detail -> MovieDetail(screen.movieId)
            }
        }
    }
}