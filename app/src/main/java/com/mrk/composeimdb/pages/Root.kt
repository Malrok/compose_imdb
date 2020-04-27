package com.mrk.composeimdb.pages

import androidx.compose.Composable
import androidx.compose.Providers
import androidx.ui.animation.Crossfade
import androidx.ui.material.MaterialTheme
import com.mrk.composeimdb.ambients.ViewModelAmbient
import com.mrk.composeimdb.effects.observe
import com.mrk.composeimdb.navigation.Navigation
import com.mrk.composeimdb.navigation.Screen
import com.mrk.composeimdb.pages.detail.MovieDetail
import com.mrk.composeimdb.pages.home.Home
import com.mrk.composeimdb.pages.splashscreen.Splashscreen
import com.mrk.composeimdb.theme.lightThemeColors
import com.mrk.composeimdb.viewmodels.MainViewModel

@Composable
fun Root(viewModel: MainViewModel) {
    val configurationResult =
        observe(viewModel.getConfig())

    MaterialTheme(
        colors = lightThemeColors
    ) {
        Crossfade(Navigation.currentScreen) { screen ->
            if (configurationResult != null && configurationResult.error == null && configurationResult.body != null) {
                Providers(
                    ViewModelAmbient provides viewModel
                ) {
                    when (screen) {
                        is Screen.Home -> Home()
                        is Screen.Detail -> MovieDetail(screen.movieId)
                    }
                }
            } else {
                Splashscreen()
            }
        }
    }
}