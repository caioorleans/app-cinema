package com.example.cinema.ui.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


const val BASE_URL = "https://api.themoviedb.org/3/"

private val retrofit = Retrofit
    .Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()



object TMDBApi{
    val retrofitService by lazy {
        retrofit.create(TMDBService::class.java)
    }
}