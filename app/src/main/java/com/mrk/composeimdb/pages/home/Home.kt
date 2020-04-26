package com.mrk.composeimdb.pages.home

import androidx.compose.*
import androidx.ui.animation.Crossfade
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.wrapContentSize
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Favorite
import androidx.ui.material.icons.filled.Home
import androidx.ui.material.icons.filled.Search
import com.mrk.composeimdb.ambients.TmdbConfigurationAmbient
import com.mrk.composeimdb.ambients.TmdbServiceAmbient
import com.mrk.composeimdb.effects.observe
import com.mrk.composeimdb.navigation.Navigation
import com.mrk.composeimdb.navigation.Screen
import com.mrk.composeimdb.pages.detail.MovieDetail
import com.mrk.composeimdb.pages.recent.RecentMovies
import com.mrk.composeimdb.pages.search.SearchMovie
import com.mrk.composeimdb.repositories.TmdbService
import com.mrk.composeimdb.theme.lightThemeColors

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

@Composable
fun Home() {
    var selectedIndex by state { 0 }

    Scaffold(
        topAppBar = {
            TopAppBar(
                title = { Text(text = "Search movie") }
            )
        },
        bodyContent = {
            when (selectedIndex) {
                0 -> RecentMovies()
                1 -> SearchMovie()
                2 -> {
                    // TODO
                    Box()
                }
            }
        },
        bottomAppBar = {
            BottomAppBar {
                BottomNavigation {
                    BottomNavigationItem(
                        icon = {
                            Icon(asset = Icons.Filled.Home)
                        },
                        selected = selectedIndex == 0,
                        onSelected = {
                            selectedIndex = 0
                        }
                    )
                    BottomNavigationItem(
                        icon = {
                            Icon(asset = Icons.Filled.Search)
                        },
                        selected = selectedIndex == 1,
                        onSelected = {
                            selectedIndex = 1
                        }
                    )
                    BottomNavigationItem(
                        icon = {
                            Icon(asset = Icons.Filled.Favorite)
                        },
                        selected = selectedIndex == 2,
                        onSelected = {
                            selectedIndex = 2
                        }
                    )
                }
            }
        }
    )
}