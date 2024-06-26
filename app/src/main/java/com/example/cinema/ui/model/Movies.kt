package com.example.cinema.ui.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movies(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)