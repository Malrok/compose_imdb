package com.mrk.composeimdb.pages

import androidx.compose.Composable
import androidx.compose.Providers
import androidx.ui.animation.Crossfade
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.wrapContentSize
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.MaterialTheme
import com.mrk.composeimdb.ambients.TmdbConfigurationAmbient
import com.mrk.composeimdb.ambients.TmdbServiceAmbient
import com.mrk.composeimdb.effects.observe
import com.mrk.composeimdb.navigation.Navigation
import com.mrk.composeimdb.navigation.Screen
import com.mrk.composeimdb.pages.detail.MovieDetail
import com.mrk.composeimdb.pages.home.Home
import com.mrk.composeimdb.repositories.TmdbService
import com.mrk.composeimdb.theme.lightThemeColors

@Composable
fun Root(tmdb: TmdbService) {
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
    Box(
        modifier = Modifier.fillMaxSize() + Modifier.wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun Main() {
    Crossfade(Navigation.currentScreen) { screen ->
        when (screen) {
            is Screen.Home -> Home()
            is Screen.Detail -> MovieDetail(screen.movieId)
        }
    }
}
