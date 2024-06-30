package com.example.cinema.ui.data.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    override val page: Int,
    override val results: List<MoviesResult>,
    @SerializedName("total_pages")
    override val totalPages: Int,
    @SerializedName("total_results")
    override val totalResults: Int
):MediaResponse