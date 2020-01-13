package com.mrk.composeimdb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.Column
import androidx.ui.material.MaterialTheme
import com.mrk.composeimdb.models.Movie
import com.mrk.composeimdb.repositories.network.TmdbClient
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var movies: List<Movie> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tmdb = TmdbClient()

        MainScope().launch {
            movies = tmdb.getTmdbService().getRecentMovies("2019-12-01", "2020-01-13").results
        }

        setContent {
            MaterialTheme {
                MoviesList(movies = movies)
            }
        }
    }
}

@Composable
fun MoviesList(movies: List<Movie>) {
    Column {
        movies.forEach { movie ->
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
