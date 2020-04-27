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

    private val configQuery: MutableLiveData<ApiResult<Configuration>> by lazy {
        return@lazy loadConfig()
    }

    fun getConfig(): LiveData<ApiResult<Configuration>> {
        return configQuery
    }

    private fun loadConfig(): MutableLiveData<ApiResult<Configuration>> {
        val data = MutableLiveData<ApiResult<Configuration>>()

        this.tmdb.getConfig().observeForever {
            data.value = it
        }

        return data
    }

    private var recentMoviesQuery: MutableLiveData<ApiResult<TmdbListResult>>? = null

    fun getRecentMovies(minDate: String, maxDate: String): LiveData<ApiResult<TmdbListResult>> {
        if (this.recentMoviesQuery == null) {
            this.recentMoviesQuery = MutableLiveData()

            this.tmdb.getRecentMovies(minDate, maxDate).observeForever {
                this.recentMoviesQuery!!.value = it
            }
        }

        return this.recentMoviesQuery!!
    }

    fun getMovieById(movieId: String): LiveData<ApiResult<Movie>> {
        return this.tmdb.getMovieById(movieId)
    }

    fun getMoviesListByTitle(title: String): LiveData<ApiResult<TmdbListResult>> {
        return this.tmdb.getMoviesListByTitle(title)
    }

}