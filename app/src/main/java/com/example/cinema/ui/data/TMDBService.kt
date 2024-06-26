package com.example.cinema.ui.data

import com.example.cinema.ui.data.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface TMDBService {

    @Headers(
        "Authorizarion:Bearer $API_KEY",
        "Accept:application/json"
    )
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page:Int = 0):MoviesResponse

    companion object {
        const val API_KEY = "641a4a3d282d836b4dc4b484d752b80c"
    }

}