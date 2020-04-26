package com.mrk.composeimdb.navigation

import androidx.compose.Model

sealed class Screen {
    object Home : Screen()
    data class Detail(val movieId: String) : Screen()
}

@Model
object Navigation {
    var currentScreen: Screen = Screen.Home
}

fun navigateTo(destination: Screen) {
    Navigation.currentScreen = destination
}