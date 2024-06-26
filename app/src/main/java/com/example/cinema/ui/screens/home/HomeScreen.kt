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
fun HomeScreen(modifier:Modifier = Modifier, moviesUiState: MoviesUiState) {

    val listMovies = listOf(
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background
    )

    Surface(modifier = modifier.fillMaxSize().padding( horizontal = 8.dp).padding(top = 16.dp)) {
        LazyVerticalGridMovies(listMovies)
    }
}
