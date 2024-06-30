package com.example.cinema.ui.data.model

interface MediaResponse {
    val page: Int
    val results: List<MediaResult>
    val totalPages: Int
    val totalResults: Int
}