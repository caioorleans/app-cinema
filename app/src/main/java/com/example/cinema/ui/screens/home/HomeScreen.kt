package com.example.cinema.ui.screens.home


import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cinema.R
import com.example.cinema.ui.components.LazyVerticalGridMovies

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(moviesUiState: MoviesUiState, modifier:Modifier = Modifier) {

    Surface(modifier = modifier.fillMaxSize()) {

        when (moviesUiState) {
            is MoviesUiState.Success -> LazyVerticalGridMovies(moviesUiState.result.results)
            is MoviesUiState.Error -> {}
            is MoviesUiState.Loading -> {}
        }
    }
}
