package com.example.cinema.ui.screens.home


import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cinema.R
import com.example.cinema.ui.components.LazyVerticalGridMovies
import com.example.cinema.ui.components.LoadingIndicator
import com.example.cinema.ui.data.model.Result

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
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
            is MoviesUiState.Success -> LazyVerticalGridMovies(
                //moviesUiState.result.results,
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
