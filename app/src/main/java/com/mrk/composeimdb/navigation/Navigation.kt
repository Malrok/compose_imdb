package com.mrk.composeimdb.navigation

sealed class Screen {
    object Home : Screen()
    data class Detail(val movieId: String) : Screen()
}

object Navigation {
    var currentScreen: Screen = Screen.Home
}

fun navigateTo(destination: Screen) {
    Navigation.currentScreen = destination
}