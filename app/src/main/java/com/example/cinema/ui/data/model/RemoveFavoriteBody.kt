package com.example.cinema.ui.data.model

import com.google.gson.annotations.SerializedName

data class RemoveFavoriteBody(
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("media_id")
    val mediaId: Int,
    @SerializedName("favorite")
    val favorite: Boolean
)