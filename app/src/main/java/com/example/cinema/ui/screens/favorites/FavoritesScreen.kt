package com.example.cinema.ui.screens.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cinema.R
import com.example.cinema.ui.components.LazyVerticalGridMedia
import com.example.cinema.ui.components.LoadingIndicator
import com.example.cinema.ui.data.model.MediaResult

@Composable
fun FavoritesScreen(
    modifier:Modifier = Modifier,
    favoriteUiState: FavoriteUiState,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel
) {

    var nextPage: Int by remember { mutableIntStateOf(2) }
    val plusPage:()->Int = {
        favoriteViewModel.getNextPageFavorite(nextPage)
        nextPage++
    }

    Surface(modifier = modifier.fillMaxSize()) {

        when (favoriteUiState) {
            is FavoriteUiState.Success -> LazyVerticalGridMedia(
                favoriteViewModel.listAllFavorite,
                navController,
                true,
                plusPage
            )
            is FavoriteUiState.Error -> {}
            is FavoriteUiState.Loading -> LoadingIndicator()
        }

    }
}