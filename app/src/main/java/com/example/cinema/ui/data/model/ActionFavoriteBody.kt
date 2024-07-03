package com.example.cinema.ui.data.model

import com.google.gson.annotations.SerializedName

data class ActionFavoriteBody(
    val favorite: Boolean,
    @SerializedName("media_id")
    val mediaId: Int,
    @SerializedName("media_type")
    val mediaType: String
)