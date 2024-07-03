package com.example.cinema.ui.screens.home


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
fun HomeScreen(
    modifier:Modifier = Modifier,
    moviesUiState: MediaUiState,
    navController: NavController,
    mediaViewModel: MediaViewModel
    ) {

    var nextPage: Int by remember { mutableIntStateOf(2) }
    val plusPage:()->Int = {
        mediaViewModel.getNextPageMedia(nextPage)
        nextPage++
    }



    Surface(modifier = modifier.fillMaxSize()) {

        when (moviesUiState) {
            is MediaUiState.Success -> LazyVerticalGridMedia(
                mediaViewModel.listAllMedia,
                navController,
                false,
                plusPage,
                mediaViewModel.stateScroll
            )
            is MediaUiState.Error -> {}
            is MediaUiState.Loading -> LoadingIndicator()
        }

    }
}
