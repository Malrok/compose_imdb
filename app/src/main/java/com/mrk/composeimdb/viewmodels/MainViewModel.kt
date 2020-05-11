package com.mrk.composeimdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrk.composeimdb.models.Configuration
import com.mrk.composeimdb.models.Movie
import com.mrk.composeimdb.models.TmdbListResult
import com.mrk.composeimdb.repositories.TmdbClient
import me.alfredobejarano.retrofitadapters.data.ApiResult

class MainViewModel : ViewModel() {
    private val tmdb = TmdbClient().getTmdbService()
    private var recentMoviesQuery: MutableLiveData<ApiResult<TmdbListResult>>? = null
    private val configQuery: MutableLiveData<ApiResult<Configuration>> by lazy {
        return@lazy loadConfig()
    }
    private val moviesDetails = HashMap<String, Movie>()

    fun getConfig(): LiveData<ApiResult<Configuration>> {
        return configQuery
    }

    fun getRecentMovies(minDate: String, maxDate: String): LiveData<ApiResult<TmdbListResult>> {
        if (this.recentMoviesQuery == null) {
            this.recentMoviesQuery = MutableLiveData()

            this.tmdb.getRecentMovies(minDate, maxDate).observeForever {
                this.recentMoviesQuery!!.value = it
            }
        }

        return this.recentMoviesQuery!!
    }

    fun getMovieById(movieId: String): LiveData<Movie> {
        val data = MutableLiveData<Movie>()

        if (moviesDetails.containsKey(movieId)) {
            data.value = moviesDetails[movieId]
        } else {
            this.tmdb.getMovieById(movieId).observeForever {
                it.body.let {movie ->
                    moviesDetails[movieId] = movie!!
                    data.value = movie
                }
            }
        }

        return data
    }

    fun getMoviesListByTitle(title: String): LiveData<ApiResult<TmdbListResult>> {
        return this.tmdb.getMoviesListByTitle(title)
    }

    private fun loadConfig(): MutableLiveData<ApiResult<Configuration>> {
        val data = MutableLiveData<ApiResult<Configuration>>()

        this.tmdb.getConfig().observeForever {
            data.value = it
        }

        return data
    }


}