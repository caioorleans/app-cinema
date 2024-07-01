package com.example.cinema.ui.data.model

data class AddFavoriteBody(
    val favorite: Boolean,
    val media_id: Int,
    val media_type: String
)