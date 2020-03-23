package com.mrk.composeimdb.navigation

import androidx.compose.Model

/**
 * Class defining the screens we have in the app: home, article details and interests
 */
sealed class Screen {
    object SearchMovie: Screen()
    object RecentMovies : Screen()
    data class Detail(val movieId: String) : Screen()
}

@Model
object Navigation {
    var currentScreen: Screen =
        Screen.RecentMovies
}

/**
 * Temporary solution pending navigation support.
 */
fun navigateTo(destination: Screen) {
    Navigation.currentScreen = destination
}