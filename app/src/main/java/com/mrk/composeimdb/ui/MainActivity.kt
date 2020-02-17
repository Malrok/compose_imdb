package com.mrk.composeimdb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import com.mrk.composeimdb.repositories.network.TmdbClient
import com.mrk.composeimdb.repositories.network.TmdbService
import com.mrk.composeimdb.ui.detail.MovieDetail
import com.mrk.composeimdb.ui.list.MoviesList

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
            Surface(color = (+MaterialTheme.colors()).background) {
                when (screen) {
                    is Screen.MoviesList -> MoviesList(tmdb = tmdb)
                    is Screen.Detail -> MovieDetail(tmdb, screen.movieId)
                }
            }
        }
    }
}

//@Composable
//fun Edit() {
//    val state = +state { EditorModel("yoh") }
//    TextField(
//        value = state.value,
//        onValueChange = {
//            Log.d("Edit", "yoh")
//            state.value = it
//        }
//    )
//}

//@Preview
//@Composable
//fun DefaultPreview() {
//    MaterialTheme {
//        MoviesList(movies = )
//    }
//}
