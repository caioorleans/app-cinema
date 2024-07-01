package com.example.cinema.ui.data

import com.example.cinema.ui.data.TMDBService.Companion.API_KEY
import com.example.cinema.ui.data.model.AddFavoriteBody
import com.example.cinema.ui.data.model.MoviesResponse
import com.example.cinema.ui.data.model.MovieDetails
import com.example.cinema.ui.data.model.TvSeriesResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface TMDBService {

    @Headers(
        "Authorization:Bearer $API_KEY",
        "Accept:application/json"
    )
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page:Int = 1):MoviesResponse

    @Headers(
        "Authorization:Bearer $API_KEY",
        "Accept:application/json"
    )
    @GET("tv/popular")
    suspend fun getPopularTvSeries(@Query("page") page:Int = 1):TvSeriesResponse

    @Headers(
        "Authorization:Bearer $API_KEY",
        "Accept:application/json"
    )
    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId:Int): MovieDetails

    @Headers(
        "Authorization:Bearer $API_KEY",
        "Accept:application/json"
    )
    @POST("account/${ACCOUNT_ID}/favorite")
    suspend fun addFavorite(@Body favoriteBody: AddFavoriteBody)

    companion object {
        const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2NDFhNGEzZDI4MmQ4MzZiNGRjNGI0ODRkNzUyYjgwYyIsIm5iZiI6MTcxOTQzMDA5NS41NTYzMDgsInN1YiI6IjY2N2FhNjBmZTFiZDQ4YzA2OTU2N2QwYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.BPcFByS9V_Flyb3nsXWeTKTfSaMBCtYw7XdRMJvw4HQ"
        const val ACCOUNT_ID = "21348380"
    }

}