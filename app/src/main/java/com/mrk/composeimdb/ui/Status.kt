package com.mrk.composeimdb.ui

import androidx.compose.Model

/**
 * Class defining the screens we have in the app: home, article details and interests
 */
sealed class Screen {
    object MoviesList : Screen()
    data class Detail(val movieId: String) : Screen()
}

@Model
object ImdbStatus {
    var currentScreen: Screen = Screen.MoviesList
}

/**
 * Temporary solution pending navigation support.
 */
fun navigateTo(destination: Screen) {
    ImdbStatus.currentScreen = destination
}