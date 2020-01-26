package com.mrk.composeimdb.repositories.network

import androidx.lifecycle.LiveData
import com.mrk.composeimdb.models.TmdbListResult
import me.alfredobejarano.retrofitadapters.data.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {

    @GET("discover/movie")
    fun getRecentMovies(@Query("primary_release_date.gte") minDate: String, @Query("primary_release_date.lte") maxDate: String): LiveData<ApiResult<TmdbListResult>>

}