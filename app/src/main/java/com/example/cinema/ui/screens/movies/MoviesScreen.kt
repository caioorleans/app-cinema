package com.example.cinema.ui.screens.movies


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cinema.ui.components.LazyVerticalGridMedia
import com.example.cinema.ui.components.LoadingIndicator

@Composable
fun MoviesScreen(
    modifier:Modifier = Modifier,
    moviesUiState: MoviesUiState,
    navController: NavController,
    moviesViewModel: MoviesViewModel
    ) {

    var nextPage: Int by remember { mutableIntStateOf(2) }
    val plusPage:()->Int = {
        moviesViewModel.getNextPageMovies(nextPage)
        nextPage++
    }

    Surface(modifier = modifier.fillMaxSize()) {

        when (moviesUiState) {
            is MoviesUiState.Success -> LazyVerticalGridMedia(
                moviesViewModel.listAllMovies,
                navController,
                false,
                plusPage
            )
            is MoviesUiState.Error -> {}
            is MoviesUiState.Loading -> LoadingIndicator()
        }

    }
}
