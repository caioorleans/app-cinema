package com.example.cinema.ui.data.model

interface MediaResult {
    val backdropPath: String
    val genreIds: List<Int>
    val id: Int
    val originalLanguage: String
    val overview: String
    val popularity: Double
    val posterPath: String
    val voteAverage: Double
    val voteCount: Int
    var mediaType:MediaType
    val tagline:String
}