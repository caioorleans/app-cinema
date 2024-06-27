package com.example.cinema.ui.screens.home


import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cinema.R
import com.example.cinema.ui.components.LazyVerticalGridMovies
import com.example.cinema.ui.components.LoadingIndicator

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(moviesUiState: MoviesUiState, navController: NavController, modifier:Modifier = Modifier) {

    Surface(modifier = modifier.fillMaxSize()) {

        when (moviesUiState) {
            is MoviesUiState.Success -> LazyVerticalGridMovies(moviesUiState.result.results, navController)
            is MoviesUiState.Error -> {}
            is MoviesUiState.Loading -> LoadingIndicator()
        }
    }
}
