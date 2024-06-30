package com.example.cinema.ui.data.model

import com.google.gson.annotations.SerializedName

data class TvSeriesResult(
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

    @SerializedName("first_air_date")
    val firstAirDate: String,
    val name: String,
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("original_name")
    val originalName: String,
):MediaResult