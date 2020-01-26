package com.mrk.composeimdb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.Column
import androidx.ui.material.MaterialTheme
import com.mrk.composeimdb.models.Movie
import com.mrk.composeimdb.repositories.network.TmdbClient
import com.mrk.composeimdb.repositories.network.TmdbService
import com.mrk.composeimdb.ui.functions.observe

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tmdb = TmdbClient().getTmdbService()

        setContent {
            MaterialTheme {
                MoviesList(tmdb = tmdb)
            }
        }
    }
}

@Composable
fun MoviesList(tmdb: TmdbService) {
    Column {
        val movies = +observe(tmdb.getRecentMovies("2019-12-01", "2020-01-13"))
        movies?.body?.results?.forEach { movie ->
            Text(
                text = movie.title
            )
        }
    }
}

//@Preview
//@Composable
//fun DefaultPreview() {
//    MaterialTheme {
//        MoviesList(movies = )
//    }
//}
