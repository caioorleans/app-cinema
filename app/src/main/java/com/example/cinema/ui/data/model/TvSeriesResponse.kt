package com.example.cinema.ui.data.model

import com.google.gson.annotations.SerializedName

data class TvSeriesResponse(
   override val page: Int,
   override val results: List<TvSeriesResult>,
   @SerializedName("total_pages")
   override val totalPages: Int,
   @SerializedName("total_results")
   override val totalResults: Int
): MediaResponse