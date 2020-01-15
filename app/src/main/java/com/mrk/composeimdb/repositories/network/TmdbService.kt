package com.mrk.composeimdb.repositories.network

import com.mrk.composeimdb.models.TmdbListResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {

    @GET("discover/movie")
    fun getRecentMovies(@Query("primary_release_date.gte") minDate: String, @Query("primary_release_date.lte") maxDate: String): Call<TmdbListResult>

}