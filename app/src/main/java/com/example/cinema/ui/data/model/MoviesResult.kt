package com.example.cinema.ui.data.model

import com.google.gson.annotations.SerializedName

data class MoviesResult(
    @SerializedName("backdrop_path")
    override val backdropPath: String,
    @SerializedName("genre_ids")
    override val genreIds: List<Int>,
    override val id: Int,
    @SerializedName("original_language")
    override val originalLanguage: String,
    override val overview: String,
    override val popularity: Double,
    @SerializedName("poster_path")
    override val posterPath: String,
    @SerializedName("vote_average")
    override val voteAverage: Double,
    @SerializedName("vote_count")
    override val voteCount: Int,

    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val adult: Boolean
):MediaResult