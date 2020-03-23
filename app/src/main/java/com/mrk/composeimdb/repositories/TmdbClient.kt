package com.mrk.composeimdb.repositories

import com.google.gson.GsonBuilder
import com.mrk.composeimdb.BuildConfig
import me.alfredobejarano.retrofitadapters.LiveDataAdapter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org/3/"

class TmdbClient {

    fun getTmdbService(): TmdbService {
        return getRetrofit().create(TmdbService::class.java)
    }

    // cf https://github.com/Malrok/TMDB/blob/master/app/src/main/java/com/moventes/moventest/tmdb/di/RetrofitModule.kt
    private fun getRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(LiveDataAdapter.Factory())
            .client(getHttpClientBuilder().build())
            .build()
    }

    private fun getHttpClientBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }

//        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
//            this.level = HttpLoggingInterceptor.Level.BODY
//        }
//
//        builder.addInterceptor(interceptor)

        return builder
    }

}