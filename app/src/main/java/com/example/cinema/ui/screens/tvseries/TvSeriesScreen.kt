package com.example.cinema.ui.screens.TvSeries


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
fun TvSeriesScreen(
    modifier:Modifier = Modifier,
    tvSeriesUiState: TvSeriesUiState,
    navController: NavController,
    tvSeriesViewModel: TvSeriesViewModel
    ) {

    var nextPage: Int by remember { mutableIntStateOf(2) }
    val plusPage:()->Int = {
        tvSeriesViewModel.getTvSeries(nextPage)
        nextPage++
    }

    Surface(modifier = modifier.fillMaxSize()) {

        when (tvSeriesUiState) {
            is TvSeriesUiState.Success -> LazyVerticalGridMedia(
                tvSeriesViewModel.listAllTvSeries,
                navController,
                false,
                plusPage,
                tvSeriesViewModel.stateScroll
            )
            is TvSeriesUiState.Error -> {}
            is TvSeriesUiState.Loading -> LoadingIndicator()
        }

    }
}
